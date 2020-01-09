package com.srlab.so.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.LineSentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentencePreProcessor;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TagLearning {

	private static Logger log = LoggerFactory.getLogger(TagLearning.class);
    public static String dataLocalPath = "/home/sr-p2irc-big14/Data/APILearn";
    
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//fileRead();
		//word2vecTagLearning();
		word2vecLoadModel();
	}
	
	public static void word2vecTagLearning() throws IOException
	{	
		log.info("Load data....");
        SentenceIterator iter = new LineSentenceIterator(new File(dataLocalPath + File.separator + "taglearn.txt"));
        iter.setPreProcessor(new SentencePreProcessor() {
			
			@Override
			public String preProcess(String sentence) {
				// TODO Auto-generated method stub
				return sentence.toLowerCase();
			}
		});
        
        TokenizerFactory t = new DefaultTokenizerFactory();
        t.setTokenPreProcessor(new CommonPreprocessor());
		
        log.info("Building model....");
        Word2Vec vec = new Word2Vec.Builder()
                .minWordFrequency(5)
                .layerSize(100)
                .seed(42)
                .windowSize(5)
                .iterate(iter)
                .tokenizerFactory(t)
                .build();

        log.info("Fitting Word2Vec model....");
        vec.fit();
        
        WordVectorSerializer.writeWord2VecModel(vec, dataLocalPath + File.separator + "tagVector.txt");
        
        log.info("Closest Words:");
        Collection<String> lst = vec.wordsNearest("Guava", 10);

        System.out.println(lst);
        
	}
	public static void word2vecLoadModel()
	{
		Word2Vec vec = WordVectorSerializer.readWord2VecModel(dataLocalPath + File.separator + "tagVector.txt");
		log.info("Closest Words:");
        Collection<String> lst = vec.wordsNearest("Python", 10);
        System.out.println(lst);
	}
	
	public static void fileRead() throws FileNotFoundException, IOException
	{ 	  
		BufferedReader br = FileReadWrite.FileRead(dataLocalPath + File.separator + "tags.txt"); 
		  
		String tags; 
		while ((tags = br.readLine()) != null)
		{
		   if (tags.equals("NULL"))
			   continue;
		   else
		   {
			   tags = tags.replace("><", " ");
			   tags = tags.replace("<", "");
			   tags = tags.replace(">", "");
			   String []tag = tags.split(" ");
			   int length = tag.length;
			   if(length >= 3) /// at least three tags
			   {
				   /* Not sure this is required or not 
				   for (int i = 0; i < length; i++)
				   {
					   if(tag[i].equals("r") || tag[i].equals("python") || tag[i].equals("java"))
				   }*/
				   
				   FileReadWrite.FileWrite(tags, dataLocalPath + File.separator + "taglearn.txt");
			   }
		   }
		} 
		
	}
}
