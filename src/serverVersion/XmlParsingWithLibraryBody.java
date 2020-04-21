package serverVersion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.opencsv.CSVWriter;
import com.sail.so.xml.Post;
import com.sail.xml.handler.DataConverter;


public class XmlParsingWithLibraryBody {
	
	/*
	public static String SOPath_Posts = "/home/sr-p2irc-big14/Desktop/stackexchange/stackoverflow_com-Posts_7z";
	public static String dataLocalPath = "/home/sr-p2irc-big14/Data/APILearn";
	*/
	
	public static String SOPath_Posts = "/u1/users/kwn550/Data/";
    public static String dataLocalPath = "/student/kwn550/StackOverflowDataCollection/APILearn";
    
	static HashSet<String> JavaLibraries = collectLibraryTags("Java");
	static HashSet<String> PythonLibraries = collectLibraryTags("Python");
	static int javaRowNumber = 0;
	static int pythonRowNumber = 0;
	static boolean libraryFound = false;
 	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		SAXParserFactory saxparserfactory = SAXParserFactory.newInstance();
				
		try {		
				saxparserfactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
				SAXParser saxparser = saxparserfactory.newSAXParser();
				
				File javaFile = new File(dataLocalPath + File.separator + "javaPost.csv");
				File pythonFile = new File(dataLocalPath + File.separator + "pythonPost.csv");
				
				FileWriter javaFileWriter = new FileWriter(javaFile);
				FileWriter pythonFileWriter = new FileWriter(pythonFile);
				
				CSVWriter javaCSVWriter = new CSVWriter(javaFileWriter);
				CSVWriter pythonCSVWriter = new CSVWriter(pythonFileWriter);
				
				String[] dataToWrite = new String[5]; 
				
				saxparser.parse(SOPath_Posts + File.separator + "Posts.xml", new DefaultHandler() {
				@Override
						
					public void startElement(String uri, String localName, String qName, Attributes attributes) {
						if (qName.equalsIgnoreCase("row")) {
							Post post = DataConverter.getPost(uri, localName, qName, attributes);
							String postBody = post.getBody();
							
							Iterator<String> itrJava = JavaLibraries.iterator();
							boolean libraryFound = false;
							while(itrJava.hasNext())
							{
								String tempLibrary = itrJava.next();
								String tempLibrary1 = " " + tempLibrary + " ";
								String tempLibrary2 = " " + tempLibrary + ".";
								if(postBody.toLowerCase().contains(tempLibrary1.toLowerCase()) || postBody.toLowerCase().contains(tempLibrary2.toLowerCase()))
								{
									System.out.println("Java Match Found");
									
									dataToWrite[0] = String.valueOf(post.getId());
									dataToWrite[1] = tempLibrary;
									System.out.println(tempLibrary);
									dataToWrite[2] = post.getTitle();
									if(post.getBody().length()<32767)
										dataToWrite[3] = post.getBody();
									else
										dataToWrite[3] = post.getBody().substring(0, 32766);
									System.out.println(dataToWrite[3]);
									dataToWrite[4] = String.valueOf(post.getParentId());
									
									javaCSVWriter.writeNext(dataToWrite);
									libraryFound = true;
									break;
								}
							}
							
							if(libraryFound)
							{
								libraryFound = false;
							}
							else
							{
								Iterator<String> itrPython = PythonLibraries.iterator();
								while(itrPython.hasNext())
								{
									String tempPython = itrPython.next();
									String tempPython1 = " " + tempPython + " ";
									String tempPython2 = " " + tempPython + ".";
									if(postBody.toLowerCase().contains(tempPython1.toLowerCase()) || postBody.toLowerCase().contains(tempPython2.toLowerCase()))
									{
										System.out.println("Python Match Found");
										
										dataToWrite[0] = String.valueOf(post.getId());
										dataToWrite[1] = tempPython;
										System.out.println(dataToWrite[1]);
										dataToWrite[2] = post.getTitle();
										if(post.getBody().length()<32767)
											dataToWrite[3] = post.getBody();
										else
											dataToWrite[3] = post.getBody().substring(0, 32766);
										System.out.println(dataToWrite[3]);
										dataToWrite[4] = String.valueOf(post.getParentId());
										pythonCSVWriter.writeNext(dataToWrite);
										break;
									}
								}
							}
						}
					}
			});	
				javaCSVWriter.close();
				pythonCSVWriter.close();
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
	
	
	private static BufferedReader FileRead(String FileName) throws FileNotFoundException, IOException
    {
            File file = new File(FileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            return br;
    }

	
	private static HashSet<String> collectLibraryTags(String Language) {
		HashSet<String> libraryTags = new HashSet<String>();
		BufferedReader br1;
		try
		{
			br1 = FileRead(dataLocalPath + File.separator + "libraryLanguage.txt");
			String tempTag;
			while((tempTag = br1.readLine())!=null)
			{
				String[] tempCategory = tempTag.split(" ---> ");
				if (tempCategory.length > 2 && tempCategory[2].toLowerCase().equals(Language.toLowerCase().toString()))
				{
					libraryTags.add(tempCategory[0]);
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
