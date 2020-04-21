package com.srlab.so.main;

import java.util.ArrayList;
import java.util.List;

import com.srlab.hashalgo.*;

public class GroupingSimilarDocs  implements Runnable{
	
	public GroupingSimilarDocs(String DocOne, String DocTwo, String ComparisonFile)
	{
		double temp = similarTitles(DocOne, DocTwo);
		temp = temp/64;
		double hashingsimilarity = 1.0 - temp;
		double stringsimilarity = compareStrings(DocOne, DocTwo);
		double similarityScore = (hashingsimilarity + stringsimilarity)/2;
		System.out.println("Similarity Score: " + similarityScore );
		if(similarityScore > 0.5)
		{
			String TitleJoin = DocOne + ", " + DocTwo + ", " + hashingsimilarity + ", " + stringsimilarity + ", " + similarityScore;
			FileReadWrite.FileWrite(TitleJoin, ComparisonFile);
		}
	}
	
	public GroupingSimilarDocs(String DocOne, String DocTwo, String ComparisonFile, String Tagging)
	{
		String[] tempDocsOne = DocOne.split("		");
		String[] tempDocsTwo = DocTwo.split("		");
		if (tempDocsOne.length <4 || tempDocsTwo.length <4)
		{
			System.out.println("Not Counted");
			System.out.println(DocOne);
			System.out.println(DocTwo);
		}
		else
		{
			String[] tagsOne = tempDocsOne[3].split(" ");
			String[] tagsTwo = tempDocsTwo[3].split(" ");
			int tagOneLength = tagsOne.length;
			int tagTwoLength = tagsTwo.length;
			
			OUTER_LOOP:
			for(int i = 0; i < tagOneLength; i++)
			{
				for(int j = 0; j < tagTwoLength; j++)
				{
					if(tagsOne[i].equals(tagsTwo[j]))
					{
						double temp = similarTitles(tempDocsOne[2], tempDocsTwo[2]);
						temp = temp/64;
						double hashingsimilarity = 1.0 - temp;
						double stringsimilarity = compareStrings(tempDocsOne[2], tempDocsTwo[2]);
						double similarityScore = (hashingsimilarity + stringsimilarity)/2;
						if(hashingsimilarity > 0.7 && stringsimilarity > 0.7 )
						{
							DocOne = DocOne.replaceAll("null		", "");
							DocTwo = DocTwo.replaceAll("null		", "");
							String TitleJoin = DocOne + "		" + DocTwo + "		" + hashingsimilarity + "		" + stringsimilarity + "		" + similarityScore;
							FileReadWrite.FileWrite(TitleJoin, ComparisonFile);
							
						}
						break OUTER_LOOP;
					}
				}
			}
		
		}
	}
	
	public static int similarTitles(String DocOne, String DocTwo)
	{
		Simhash simhash = new Simhash(new BinaryWordSeg());
		
		long docOneHash = simhash.simhash64(DocOne);
		long docTwoHash = simhash.simhash64(DocTwo);
		
		int dist = simhash.hammingDistance(docOneHash, docTwoHash);
		return dist;
	}
	
	public static void similarDocs(List<String> DocOne, List<String> DocTwo, String ComparisonFile)
	{
		int docOneLength = DocOne.size();
		int docTwoLength = DocTwo.size();
		
		for(int i = 0; i < docOneLength; i++)
		{
			for(int j = 0; j < docTwoLength; j++)
			{
				int dist = similarTitles(DocOne.get(i), DocTwo.get(j));
				
				//System.out.println(DocOne.get(i) + "  " + DocTwo.get(j) + "  " + dist);
				
				if(dist <= 10)
				{
					String TitlesJoin = DocOne.get(i) + "		" + DocTwo.get(j);
					FileReadWrite.FileWrite(TitlesJoin, ComparisonFile);
				}
			}
		}
	}
	
	public static void stringBasedSimilarDocs(List<String> DocOne, List<String> DocTwo, String ComparisonFile)
	{
		int docOneLength = DocOne.size();
		int docTwoLength = DocTwo.size();
		
		for(int i = 0; i < docOneLength; i++)
		{
			for(int j = 0; j < docTwoLength; j++)
			{
				double similarityScore = compareStrings(DocOne.get(i), DocTwo.get(j));
				
				if(similarityScore > 0.5)
				{
					String TitlesJoin = DocOne.get(i) + "		" + DocTwo.get(j);
					FileReadWrite.FileWrite(TitlesJoin, ComparisonFile);
				}
			}
		}
	}
	
	
	 private static String[] letterPairs(String str) {
	       int numPairs = str.length()-1;
	       if(numPairs < 0)
	       {
	    	   numPairs = 0;
	       }
	       String[] pairs = new String[numPairs];
	       for (int i=0; i<numPairs; i++) {
	           pairs[i] = str.substring(i,i+2);
	       }
	       return pairs;
	   }
	   
	/** @return an ArrayList of 2-character Strings. */

	   private static ArrayList wordLetterPairs(String str) {
	       ArrayList allPairs = new ArrayList();
	       // Tokenize the string and put the tokens/words into an array
	       String[] words = str.split("\\s");
	       // For each word
	       for (int w=0; w < words.length; w++) {
	           // Find the pairs of characters
	           String[] pairsInWord = letterPairs(words[w]);
	           for (int p=0; p < pairsInWord.length; p++) {
	               allPairs.add(pairsInWord[p]);
	           }
	       }
	       return allPairs;
	   }
	   
	 /** @return lexical similarity value in the range [0,1] */

	   public static double compareStrings(String str1, String str2) {
	       ArrayList pairs1 = wordLetterPairs(str1.toUpperCase());
	       ArrayList pairs2 = wordLetterPairs(str2.toUpperCase());
	       int intersection = 0;
	       int union = pairs1.size() + pairs2.size();
	       for (int i=0; i<pairs1.size(); i++) {
	           Object pair1=pairs1.get(i);
	           for(int j=0; j<pairs2.size(); j++) {
	               Object pair2=pairs2.get(j);
	               if (pair1.equals(pair2)) {
	                   intersection++;
	                   pairs2.remove(j);
	                   break;
	               }
	           }
	       }
	       return (2.0*intersection)/union;
	   }

	   @Override
	public void run() {
		// TODO Auto-generated method stub
		try
        { 
            // Displaying the thread that is running 
            System.out.println ("Thread " + 
                  Thread.currentThread().getId() + 
                  " is running"); 
  
        } 
        catch (Exception e) 
        { 
            // Throwing an exception 
            System.out.println ("Exception is caught"); 
        } 
		
		
	}
	
	
	
	/**
	public static List<String> similarDocs(String[] DocOne, String[] DocTwo)
	{
		Simhash simHash = new Simhash(new BinaryWordSeg());

		// DocHashes is a list that will contain all of the calculated hashes.
		ArrayList<Long> docHashesOne = Lists.newArrayList();
		ArrayList<Long> docHashesTwo = Lists.newArrayList();
		
		// Maps 12-bit key with the documents matching the partial hash
		Map<BitSet, HashSet<Integer>> hashIndexOne = Maps.newHashMap();
		Map<BitSet, HashSet<Integer>> hashIndexTwo = Maps.newHashMap();

		// Read the documents. (Each line represents a document).

		int idx = 0;
		int step = 0;

		System.out.println("Start to build index for First Titles...");
		for (String doc : DocOne) {
			// Calculate the document hash.
			long docHashOne = simHash.simhash64(doc);
			System.out.println("Document=[" + doc + "] Hash=[" + docHashOne + "]");

			// Store the document hash in a list.
			docHashesOne.add(docHashOne);

			// StringBuilder keyBuilder = new StringBuilder(12);
			BitSet key = new BitSet(12);

			step = 0;

			for (int i = 0; i < 64; ++i) {
				key.set(step, ((docHashOne >> i) & 1) == 1);
				if (step++ == 12) {
					/*
					 * a) Separates the hash in 12-bit keys. b) This value will
					 * be a key in hashIndex. c) hashIndex will contain sets of
					 * documents matching each key (12-bits).
					 
					if (hashIndexOne.containsKey(key)) {
						hashIndexOne.get(key).add(idx);
					} else {
						HashSet<Integer> vectorOne = new HashSet<Integer>();
						vectorOne.add(idx);
						hashIndexOne.put(key, vectorOne);
					}
					step = 0;
					key = new BitSet(12); // reset key holder.
				}
			}
			++idx;
		}
		System.out.println("Index has been built for First Titles.");
		
		idx = 0;
		step = 0;

		System.out.println("Start to build index for First Titles...");
		for (String doc : DocTwo) {
			// Calculate the document hash.
			long docHashTwo = simHash.simhash64(doc);
			System.out.println("Document=[" + doc + "] Hash=[" + docHashTwo + "]");

			// Store the document hash in a list.
			docHashesTwo.add(docHashTwo);

			// StringBuilder keyBuilder = new StringBuilder(12);
			BitSet key = new BitSet(12);

			step = 0;

			for (int i = 0; i < 64; ++i) {
				key.set(step, ((docHashTwo >> i) & 1) == 1);
				if (step++ == 12) {
					/*
					 * a) Separates the hash in 12-bit keys. b) This value will
					 * be a key in hashIndex. c) hashIndex will contain sets of
					 * documents matching each key (12-bits).
					 
					if (hashIndexTwo.containsKey(key)) {
						hashIndexTwo.get(key).add(idx);
					} else {
						HashSet<Integer> vectorTwo = new HashSet<Integer>();
						vectorTwo.add(idx);
						hashIndexTwo.put(key, vectorTwo);
					}
					step = 0;
					key = new BitSet(12); // reset key holder.
				}
			}
			++idx;
		}
		System.out.println("Index has been built for First Titles.");

		step = 0;
		HashSet<Integer> docSimilarCandidates = Sets.newHashSet();
		
		
		return null;
	}*/
	
	
	/* Threading Example
	 // Java code for thread creation by implementing 
	// the Runnable Interface 
	class MultithreadingDemo implements Runnable 
	{ 
    	public void run() 
    	{ 
        	try
        	{ 
            // Displaying the thread that is running 
            System.out.println ("Thread " + 
                                Thread.currentThread().getId() + 
                                " is running"); 
  
        } 
        catch (Exception e) 
        { 
            // Throwing an exception 
            System.out.println ("Exception is caught"); 
        } 
    } 
} 
  
// Main Class 
class Multithread 
{ 
    public static void main(String[] args) 
    { 
        int n = 8; // Number of threads 
        for (int i=0; i<n; i++) 
        { 
            Thread object = new Thread(new MultithreadingDemo()); 
            object.start(); 
        } 
    } 
} 
	 */

}
