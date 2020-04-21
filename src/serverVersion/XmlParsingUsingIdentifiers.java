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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import com.sail.so.xml.Post;
import com.sail.xml.handler.DataConverter;


public class XmlParsingUsingIdentifiers {
	public static String DataPath = "/u1/users/kwn550/Data";
	public static String ResultPath = "/student/kwn550/StackOverflowDataCollection/APILearn";
	public static String[] postBodyIdentifiers = {"use", "check out", "try", "using", "good solution", "good alternative"};

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			BufferedReader br = FileRead(ResultPath + File.separator + "JavaLibraryWith5Answers.txt");
			//ArrayList<Integer> postList = new ArrayList<Integer>();
			String tempLine;
			while((tempLine = br.readLine()) != null)
			{
				String[] temps = tempLine.split(";");
				//postList.add(Integer.parseInt(temps[0]));
				System.out.println("Working with : " + temps[0]);
				new PostSearchWithIdentifiers(Integer.parseInt(temps[0]));
			}
	
			/*** Thread Implementation in Java 8 ***/	
			/*
			ExecutorService service = Executors.newFixedThreadPool(20);
			IntStream.range(0, postList.size()).forEach(i -> {
					service.submit(new PostSearchWithIdentifiers(postList.get(i)));
			});*/
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	public static BufferedReader FileRead(String FileName) throws FileNotFoundException, IOException
	{
		File file = new File(FileName); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		return br;		
	}
}

	//final class PostSearchWithIdentifiers implements Runnable {
	final class PostSearchWithIdentifiers{
		static int count = 0;
		public PostSearchWithIdentifiers(int PostID)
		{
			SAXParserFactory saxparserfactory = SAXParserFactory.newInstance();
			
			try {		
					saxparserfactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
					//saxparserfactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
					SAXParser saxparser = saxparserfactory.newSAXParser();
				
					/// Reading the files
					saxparser.parse(XmlParsingUsingIdentifiers.DataPath + File.separator + "Posts.xml", new DefaultHandler() {
					@Override
							
						public void startElement(String uri, String localName, String qName, Attributes attributes) {
							if (qName.equalsIgnoreCase("row")) {
								Post post = DataConverter.getPost(uri, localName, qName, attributes);
								
								count ++;
								if(count % 1000000 == 0)
								{
									System.out.println(count + " Post Visited");
								}
								
								if(post.getId() == PostID)
								{
									int length = XmlParsingUsingIdentifiers.postBodyIdentifiers.length;
									for(int i = 0; i < length; i++)
									{
										String PostText = post.getBody();
										if(PostText.toLowerCase().contains(XmlParsingUsingIdentifiers.postBodyIdentifiers[i].toLowerCase()))
										{
											String TexttoWrite = "Question;"+ String.valueOf(post.getId()) + ";" + post.getBody();
											FileWrite(TexttoWrite, XmlParsingUsingIdentifiers.ResultPath + File.separator + "postBody.txt");
											break;
										}
									}
								}
								
								else if (post.getParentId() == PostID)
								{
									int length = XmlParsingUsingIdentifiers.postBodyIdentifiers.length;
									for(int i = 0; i < length; i++)
									{
										String PostText = post.getBody();
										if(PostText.toLowerCase().contains(XmlParsingUsingIdentifiers.postBodyIdentifiers[i].toLowerCase()))
										{
											String TexttoWrite = "Answer;"+ String.valueOf(post.getId()) + ";" + post.getBody();
											FileWrite(TexttoWrite, XmlParsingUsingIdentifiers.ResultPath + File.separator + "postBody.txt");
											break;
										}
									}
								}	
							}
						}
				});
			}catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		/*
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
			}*/
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
	}

