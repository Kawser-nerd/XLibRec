package com.srlab.so.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileReadWrite {
	
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
