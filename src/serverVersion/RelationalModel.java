package serverVersion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class RelationalModel {
	
	public static String dataLocalPath = "/home/sr-p2irc-big14/Data/APILearn";
	
	public static void main(String args[])
	{
		String[] TagsSet = SupportAndConfidence.tagCollection(dataLocalPath + File.separator + "taglearn.txt");
		System.out.println("Total Tags: " + TagsSet.length);
		List<String> TagsList = new ArrayList<String>();
		
		BufferedReader br;
		try {
			br = FileReadWrite.FileRead(dataLocalPath + File.separator + "taglearn.txt");
			String tempTag;
			while((tempTag = br.readLine())!=null) // getting values in respect to
			{
				/*
				 * if(tempTag.contains("java") || tempTag.contains(" r ") || tempTag.contains("python"))
				At first performing with Java, after that with R and Python
				*/
				if(tempTag.contains("java"))
				{
					TagsList.add(tempTag);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*** Thread Implementation in Java 8 ***/
		
		ExecutorService service = Executors.newFixedThreadPool(20);
		IntStream.range(0, TagsSet.length).forEach(i -> {
			IntStream.range(i + 1, TagsSet.length).forEach(j -> {
				service.submit(new SupportAndConfidence(TagsSet[i], TagsSet[j], TagsList));
			});
		});
	}

}

final class SupportAndConfidence implements Runnable{
	public static double support(String tag1, String tag2, List<String> TagsList)
	{
		int numberOfTagSentences = 0;
		int numberOfSupportTags = 0;
		int tagsLength = TagsList.size();
		for(int i = 0; i < tagsLength; i++)
		{
			++numberOfTagSentences;
			if(TagsList.get(i).contains(tag1) && TagsList.get(i).contains(tag2))
			{
				++numberOfSupportTags;
			}
		}
		
		double supportScore = (double)numberOfSupportTags / numberOfTagSentences;
		return supportScore;
	}
	
	public static double confidence(String tag1, String tag2, List<String> TagsList)
	{
		int numberOfTag1Sentences = 0;
		int numberOfSupportTags = 0;
		int tagsLength = TagsList.size();
		for(int i = 0; i < tagsLength; i++)
		{
			if(TagsList.get(i).contains(tag1) && TagsList.get(i).contains(tag2))
			{
				++numberOfSupportTags;
			}
			else if(TagsList.get(i).contains(tag1))
			{
				++numberOfTag1Sentences;
			}
		}
		double confidenceScore = 0.0;
		if(numberOfTag1Sentences != 0)
		{
			confidenceScore = (double) numberOfSupportTags / numberOfTag1Sentences;
		}
		return confidenceScore;
	}
	
	public SupportAndConfidence(String tag1, String tag2, List<String> TagsList)
	{
		double supportScore = support(tag1, tag2, TagsList);
		double confidenceScore = confidence(tag1, tag2, TagsList);
		System.out.println(tag1 + " " + tag2 + " " + supportScore + " " + confidenceScore);
		if(supportScore > 0.1 && confidenceScore > 0.5) /* Threshhold Value Set */
		{
			String textToWrite = tag1 + " " + tag2 + " " + supportScore + " " + confidenceScore;
			FileReadWrite.FileWrite(textToWrite, "TagAssociationJava.txt");
		}
	}
	
	public static String[] tagCollection(String TagPath)
	{
		String tags;
		HashSet<String> TagsSet=new HashSet<String>();
		try {
			BufferedReader br = FileReadWrite.FileRead(TagPath);
			while((tags = br.readLine()) != null)
			{
				/*if(tempTag.contains("java") || tempTag.contains(" r ") || tempTag.contains("python"))
				At first performing with Java, after that with R and Python*/ 
				if(tags.contains("java"))
				{
					String[] tag = tags.split(" ");
					for (int i = 0; i < tag.length; i++)
					{
						TagsSet.add(tag[i].toString());
					}
				}
			}
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] TagsList = new String[TagsSet.size()];
		int i = 0;
		Iterator<String> itr=TagsSet.iterator();  
		while(itr.hasNext()){  
			 TagsList[i] = itr.next();
			 i++;
			}
		return TagsList;
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
}

final class FileReadWrite {
	
	public static void FileWrite(String textToWrite, String FileName)
	{
		FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        try {
            
        	fw = new FileWriter(FileName, true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);

            pw.println(textToWrite.toString());
            System.out.println("Data Successfully appended into file");
            pw.flush();
            pw.close();
            bw.close();
            fw.close();

        } catch (IOException io) {// can't do anything }
            io.printStackTrace();
        } 
    }
	
	public static BufferedReader FileRead(String FileName) throws FileNotFoundException, IOException
	{
		File file = new File(FileName); 
		  
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		
		return br;
		
	}

}
