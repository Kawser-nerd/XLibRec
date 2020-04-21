package com.srlab.so.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class test implements Runnable{
	public static String dataLocalPath = "/home/sr-p2irc-big14/Data/APILearn";

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		/*
		int n = 8; // Number of threads 
        for (int i=0; i<8; i++) 
        { 
            Thread object = new Thread(new test());
            System.out.println("Thread Started");
            object.start();
            //object.sleep(1000);
            //object.stop();
            System.out.println("Thread Stopped");
            
            
        } 
		String tester = "Thread object = new Thread(new test());\n" + 
				"            System.out.println(\"Thread Started\");\n" + 
				"            object.start();\n" + 
				"            //object.sleep(1000);\n" + 
				"            //object.stop();\n" + 
				"            System.out.println(\"Thread Stopped\");"
				+ "I love your fucking library.";
		System.out.println(tester.contains("system"));
		*/
		
		//collectLibraryTags();
		
		String a = "we were. working for a long time";
		String termOne = " " + "re" + " ";
		String termTwo = " " + "re" + ".";
		
		if (a.toLowerCase().contains(termOne) || a.toLowerCase().contains(termTwo))
		{
			System.out.println("Not Working ...");
		}
	}
		

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
	
	private static HashSet<String> collectLibraryTags() {
		HashSet<String> libraryTags = new HashSet<String>();
		BufferedReader br1;
		try
		{
			br1 = FileReadWrite.FileRead(dataLocalPath + File.separator + "libraryLanguage.txt");
			String tempTag;
			while((tempTag = br1.readLine())!=null)
			{
				String[] tempCategory = tempTag.split(" ---> ");
				if (tempCategory.length > 2 && tempCategory[2].toLowerCase().equals("python"))
				{
					System.out.println(tempTag);
					libraryTags.add(tempCategory[0]);
				}
				else
				{
					//System.out.println(tempTag);
				}
			}
			br1.close();
			System.out.println("Total Library: " + libraryTags.size());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return libraryTags;
	}

}
