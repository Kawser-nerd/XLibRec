package com.srlab.so.main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.helpers.DefaultHandler;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mysql.cj.util.StringUtils;
import com.sail.so.xml.Post;
import com.sail.xml.handler.DataConverter;
import com.srlab.database.mysql.MySQlOperation;

import au.com.bytecode.opencsv.CSVWriter;

public class XmlParserSo {
	
	public static String SOPath = "/home/sr-p2irc-big14/Desktop/stackexchange/";
	public static String SOPath_Posts = "/home/sr-p2irc-big14/Desktop/stackexchange/stackoverflow_com-Posts_7z";
	public static String dataLocalPath = "/home/sr-p2irc-big14/Data/APILearn";
	public static String[] identifiersJava = {"related", "How to", "another", "similar", "any other", "substitute", "in java"};
	public static String[] identifiersPython = {"related", "How to", "another", "similar", "any other", "substitute", "in python"};
	public static String[] postBodyIdentifiers = {"use", "check out", "try", "using", "good solution", "good alternative"};
	public static String[] libraryToSearchJava = {"junit", "json", "sl4j"};
	public static String[] libraryToSearchPython = {"pytest", "simplejson", "pygogo"};
	static int javaRowNumber = 0;
	static int pythonRowNumber = 0;
 	public static int noOfAnswers = 0;
	public static String quesTitle = "";
	private static boolean writing;
	
	public static void main(String[] args) {
		// All zips are unzipping //
		// Only Once is enough //
		/*
		Unzip unzip = new Unzip();
		unzip.decompressSevenZInDirTo(Paths.get(SOPath), Paths.get(SOPath));
		System.out.println("Unzip Completed");
		*/
		/// Database Connection Establishment ///
		//MySQlOperation.connectionEstablish(); 
		
		/// Posts Analysis begin
		/*
		File[] directories = new File(SOPath.toString()).listFiles(File::isDirectory); // List all files
		int noofDirectories = directories.length;
		for (int i = 0; i < noofDirectories; i++)
		{
			System.out.println("Working on posts:  " + directories[i].toString());
			getTagsWiki(directories[i].toString());
		}*/
		//getTitles(SOPath_Posts);
		//getTagsWiki(SOPath_Posts);
		//getSimilarTagsQuestions(SOPath_Posts);
		//simHash();
		//MiningRelationalKnowledge();
		/*
		HashSet<String> libraryTags = collectLibraryTags();
		File[] directories = new File(SOPath.toString()).listFiles(File::isDirectory); // List all files
		int noofDirectories = directories.length;
		for (int i = 0; i < noofDirectories; i++)
		{
			System.out.println("Working on posts:  " + directories[i].toString());
			libraryPostRetrieve(directories[i].toString(), libraryTags);
		}
		
		try {
			BufferedReader br = FileReadWrite.FileRead(dataLocalPath + File.separator + "PostIDofAllJavaLibrary.txt");
			String tempRead;
			while((tempRead = br.readLine())!=null)
			{
				noOfAnswers = 0;
				String[] temps = tempRead.split(";");
				if(temps.length>1)
				{
					for (int i = 0; i < noofDirectories; i++)
					{
						System.out.println("Working on posts:  " + directories[i].toString());
						libraryPostWithAnswer(directories[i].toString(), temps[0]);
						if(noOfAnswers>4)
						{
							String TexttoWrite = tempRead + ";" + quesTitle;
							FileReadWrite.FileWrite(TexttoWrite, dataLocalPath + File.separator + "PostIDofMultiAnsJava.txt");
							break;
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//libraryPostRetrieve(libraryTags);
		//libraryTitlePostIDRetrieve();
		
		//HashSet<String> libraryTags = collectLibraryTags();
		//libraryPostRetrieve(SOPath_Posts, libraryTags);
		
		//collateLibraryPostWithAnswers(dataLocalPath + File.separator + "PostIDofMultiAnsJava.txt", dataLocalPath + File.separator + "PostIDofAllJavaLibrary.txt" );
		
		
		
		
	/*
		ArrayList<String> postID = new ArrayList<String>();
		
		BufferedReader br;
		try {
			br = FileReadWrite.FileRead(dataLocalPath + File.separator + "PostIDofAllJavaLibrary.txt");
			String tempRead;
			while((tempRead = br.readLine())!=null)
			{
				String[] temps = tempRead.split(";");
				if(temps.length>1)
					postID.add(temps[0]);
			}
			
			libraryPostWithAnswer(SOPath_Posts, postID);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		
		libraryInPostBody(SOPath_Posts);
		
	}
	
	public static void getPostTags(String FileNamePath)
	{
		SAXParserFactory saxparserfactory = SAXParserFactory.newInstance();
		try {
			saxparserfactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
			//saxparserfactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
			SAXParser saxparser = saxparserfactory.newSAXParser();
			
			/// Reading the files
			saxparser.parse(FileNamePath + File.separator + "Posts.xml", new DefaultHandler() {
				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes) {
					if (qName.equalsIgnoreCase("row")) {
						Post post = DataConverter.getPost(uri, localName, qName, attributes);
						/// Getting the tags only
						String tags = post.getTags();
						if (tags != null)
						{
							tags = tags.replace("><", " ");
							tags = tags.replace("<", "");
							tags = tags.replace(">", "");
							String []tag = tags.split(" ");
							for (int i = 0; i < tag.length; i++)
							{
								if(tag[i].equals("python"))  /// need to define which tag is looking for
								{
									System.out.println(tags);
									//MySQlOperation.insertDB(post, FileNamePath); // Line to perform Mysql Operation
								}
							}
							
							//System.out.println(tags);
							//// Tag Checking ///
							
						}
					}
				}
			});
			 
		} catch (SAXNotRecognizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getSimilarTagsQuestions(String FileNamePath)
	{
		/* List Declared just to collect codes from stackoverflow dataset as a temporary basis*/
		
		ArrayList<ArrayList<String>> PythonQuestions = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> JavaQuestions = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> RQuestions = new ArrayList<ArrayList<String>>();
		
		
		SAXParserFactory saxparserfactory = SAXParserFactory.newInstance();
		try {
			saxparserfactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
			//saxparserfactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
			SAXParser saxparser = saxparserfactory.newSAXParser();
			
			/// Reading the files
			saxparser.parse(FileNamePath + File.separator + "Posts.xml", new DefaultHandler() {
				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes) {
					if (qName.equalsIgnoreCase("row")) {
						Post post = DataConverter.getPost(uri, localName, qName, attributes);
						/// Getting the tags only
						String tags = post.getTags();
						if (tags != null)
						{
							tags = tags.replace("><", " ");
							tags = tags.replace("<", "");
							tags = tags.replace(">", "");
							String []tag = tags.split(" ");
							for (int i = 0; i < tag.length; i++)
							{
								if(tag[i].equals("python"))  /// need to define which tag is looking for
								{
									ArrayList<String> temp = new ArrayList<String>();
									temp.add(String.valueOf(post.getId()));
									temp.add(post.getTitle());
									temp.add(tags);
									//MySQlOperation.insertDB(post, FileNamePath); // Line to perform Mysql Operation
									PythonQuestions.add(temp);
								}
								else if(tag[i].equals("java"))  /// need to define which tag is looking for
								{
									ArrayList<String> temp = new ArrayList<String>();
									temp.add(String.valueOf(post.getId()));
									temp.add(post.getTitle());
									temp.add(tags);
									//MySQlOperation.insertDB(post, FileNamePath); // Line to perform Mysql Operation
									JavaQuestions.add(temp);
								}
								else if(tag[i].equals("r"))  /// need to define which tag is looking for
								{
									ArrayList<String> temp = new ArrayList<String>();
									temp.add(String.valueOf(post.getId()));
									temp.add(post.getTitle());
									temp.add(tags);
									//MySQlOperation.insertDB(post, FileNamePath); // Line to perform Mysql Operation
									RQuestions.add(temp);
								}
							}
						}
					}
				}
			});
			
			String tempValue=null;
			
			for(int i = 0; i < PythonQuestions.size(); i++)
			{
				for(int j = 0; j < PythonQuestions.get(i).size(); j++)
				{
					tempValue = tempValue + "		" + PythonQuestions.get(i).get(j); 
				}
				FileReadWrite.FileWrite(tempValue, dataLocalPath + File.separator + "titlesPythonTagsId.txt");
				tempValue = null;
			}
			 
			for(int i = 0; i < JavaQuestions.size(); i++)
			{
				for(int j = 0; j < JavaQuestions.get(i).size(); j++)
				{
					tempValue = tempValue + "		" + JavaQuestions.get(i).get(j); 
				}
				FileReadWrite.FileWrite(tempValue, dataLocalPath + File.separator + "titlesJavaTagsId.txt");
				tempValue = null;
			}
			
			for(int i = 0; i < RQuestions.size(); i++)
			{
				for(int j = 0; j < RQuestions.get(i).size(); j++)
				{
					tempValue = tempValue + "		" + RQuestions.get(i).get(j); 
				}
				FileReadWrite.FileWrite(tempValue, dataLocalPath + File.separator + "titlesRTagsId.txt");
				tempValue = null;
			}
			
		} catch (SAXNotRecognizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getTagsWiki(String FileNamePath)
	{
		SAXParserFactory saxparserfactory = SAXParserFactory.newInstance();
		
		try {
			SAXParser saxparser = saxparserfactory.newSAXParser();
			/// Reading the files
			saxparser.parse(FileNamePath + File.separator + "Posts.xml", new DefaultHandler() {
				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes) {
					if (qName.equalsIgnoreCase("row")) {
						Post post = DataConverter.getPost(uri, localName, qName, attributes);
						/// Getting the tags only
						int posttypeID = post.getPostTypeId();
						String postBody = post.getBody();
						//if (posttypeID == 5 && post.getTags() != null)
						if(posttypeID == 5 && postBody.contains("library"))
						{
							MySQlOperation.insertDB(post, FileNamePath);
						}				
					}
				}
			});
			 
		} catch (ParserConfigurationException e) {
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
	
	public static void getTitles(String FileNamePath)
	{
		SAXParserFactory saxparserfactory = SAXParserFactory.newInstance();
		
		try {
			SAXParser saxparser = saxparserfactory.newSAXParser();
			/// Reading the files
			saxparser.parse(FileNamePath + File.separator + "Posts.xml", new DefaultHandler() {
				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes) {
					if (qName.equalsIgnoreCase("row")) {
						Post post = DataConverter.getPost(uri, localName, qName, attributes);
						/// Getting the tags only
						String tags = post.getTags();
						if (tags != null)
						{
							tags = tags.replace("><", " ");
							tags = tags.replace("<", "");
							tags = tags.replace(">", "");
							String []tag = tags.split(" ");
							for (int i = 0; i < tag.length; i++)
							{
								if(tag[i].equals("python"))  /// need to define which tag is looking for
								{
									System.out.println(tags);
									String textToWrite = post.getId() + " " + post.getTitle();
									FileReadWrite.FileWrite(textToWrite, dataLocalPath + File.separator + "titlesPython.txt");
								}
								else if(tag[i].equals("r"))  /// need to define which tag is looking for
								{
									System.out.println(tags);
									String textToWrite = post.getId() + " " + post.getTitle();
									FileReadWrite.FileWrite(textToWrite, dataLocalPath + File.separator + "titlesR.txt");
								}
								
								else if(tag[i].equals("java"))  /// need to define which tag is looking for
								{
									System.out.println(tags);
									String textToWrite = post.getId() + " " + post.getTitle();
									FileReadWrite.FileWrite(textToWrite, dataLocalPath + File.separator + "titlesJava.txt");
								}
								else if(tag[i].equals("matlab"))  /// need to define which tag is looking for
								{
									System.out.println(tags);
									String textToWrite = post.getId() + " " + post.getTitle();
									FileReadWrite.FileWrite(textToWrite, dataLocalPath + File.separator + "titlesMatlab.txt");
								}
							}
							
							//System.out.println(tags);
							//// Tag Checking ///
							
						}
					}
				}
			});
			 
		} catch (ParserConfigurationException e) {
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
	
	public static void simHash()
	{
		try {
			BufferedReader br1 = FileReadWrite.FileRead(dataLocalPath + File.separator + "titlesPythonTagsId.txt");
			BufferedReader br2 = FileReadWrite.FileRead(dataLocalPath + File.separator + "titlesJavaTagsId.txt");
			List<String> DocOne = new ArrayList<String>();
			List<String> DocTwo = new ArrayList<String>();
			
			System.out.println("Doc One Loading");
			String doc;
			/*
			
			while((doc = br1.readLine()) != null)	
			{
				doc =  doc.split(" ", 2)[1];
				if(doc == null)
				{
					System.out.println("Null Value");
				}
				else
				{
					DocOne.add(doc);
				}
			}	
			System.out.println("Doc One Loaded");
			br1.close();
			System.out.println("Doc Two Loading");
			while((doc = br2.readLine()) != null)
			{
				doc =  doc.split(" ", 2)[1];
				if(doc == null)
				{
					System.out.println("Null Value");
				}
				else
				{
					DocTwo.add(doc);
				}
			}
			*/
			
			while((doc = br1.readLine()) != null)
				DocOne.add(doc);
			br1.close();
			System.out.println("Doc Two loading");
			while((doc = br2.readLine()) != null)
				DocTwo.add(doc);
			System.out.println("Doc Two Loaded");
			br2.close();
			System.out.println("Starting Similarity Check");
			
			String Tagging = null;
			
			/*** Thread Implementation in Java 8 ***/
			
			ExecutorService service = Executors.newFixedThreadPool(20);
			IntStream.range(0, DocOne.size()).forEach(i -> {
				IntStream.range(0, DocTwo.size()).forEach(j -> {
					service.submit(new GroupingSimilarDocs(DocOne.get(i), DocTwo.get(j), dataLocalPath + File.separator + "TitlesJavaPython", Tagging));
				});
			});
			
			
			/*
			for (int i = 0; i < DocOne.size(); i++)
			{
				for(int j = 0; j < DocTwo.size(); j++)
				{
					Thread thobj = new Thread(new GroupingSimilarDocs(DocOne.get(i), DocTwo.get(j), dataLocalPath + File.separator + "TitlesJavaPython", Tagging));
					thobj.start();
					
					/*if(j%40 == 0)
					{
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				System.out.println("One Title Similarity Checked");
			}*/
			
			//GroupingSimilarDocs.similarDocs(DocOne, DocTwo, dataLocalPath + File.separator + "TitlesJavaPython");
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static void MiningRelationalKnowledge()
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
		
		/*
		for(int i = 0; i < TagsSet.length; i++ )
		{
			for(int j = i + 1; j < TagsSet.length; j++ )
			{
				Thread thobj = new Thread(new SupportAndConfidence(TagsSet[i], TagsSet[j], TagsList));
				thobj.start();
				//SupportAndConfidence sc = new SupportAndConfidence(TagsSet[i], TagsSet[j], TagsList);
			}
		}
		*/
	}
	
	public static void libraryPostRetrieve(HashSet<String> libraryTags)
	{
		
		Map tagsPostID = new HashMap();
		
		BufferedReader br2;
		try
		{
			/////-------- Select the file for Java PostID search --------/////
			String tempTag;
			
			br2 = FileReadWrite.FileRead(dataLocalPath + File.separator + "titlesJavaTagsId.txt");
			while((tempTag = br2.readLine())!=null)
			{
				String[] tempCategory = tempTag.split("		");
				if (tempCategory.length < 3)
					continue;
				else
					tagsPostID.put(tempCategory[0], tempCategory[2]);
			}
			br2.close();
			
			String textToWrite = "PostID" + ";" + "LibraryName" +"\n";
			Iterator<String> itr=libraryTags.iterator();  
			while(itr.hasNext())
			{
				String tempLibrary = itr.next();
				for(Object key : tagsPostID.keySet())
				{
					tempTag = tagsPostID.get(key).toString();
					String[] tempCategory = tempTag.split(" ");
					for (int j = 0; j < tempCategory.length; j++)
					{
						if(tempCategory[j].equals(tempLibrary))
						{
								textToWrite = key.toString() + ";" + tempCategory[j] + "\n";
								FileReadWrite.FileWrite(textToWrite, dataLocalPath + File.separator + "PostIDofJavaLibrary.txt");
						}
					}
				}
			}
			
			/*
			 * for(Object key : mapA.keySet()) {
    				Object value = mapA.get(key);
				}
				
				OR
				
				Iterator iterator = mapA.keySet().iterator();
				while(iterator.hasNext(){
					Object key   = iterator.next();
					Object value = mapA.get(key);
				}
			 *
			 */
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void libraryPostRetrieve(String FileNamePath, HashSet<String> libraryTags)
	{
		
		SAXParserFactory saxparserfactory = SAXParserFactory.newInstance();
		try {
			saxparserfactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
			//saxparserfactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
			SAXParser saxparser = saxparserfactory.newSAXParser();
			
			/// Reading the files
			saxparser.parse(FileNamePath + File.separator + "Posts.xml", new DefaultHandler() {
				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes) {
					if (qName.equalsIgnoreCase("row")) {
						Post post = DataConverter.getPost(uri, localName, qName, attributes);
						/// Getting the tags only
						String tags = post.getTags();
						if (tags != null)
						{
							tags = tags.replace("><", " ");
							tags = tags.replace("<", "");
							tags = tags.replace(">", "");
							String []tag = tags.split(" ");
							for (int i = 0; i < tag.length; i++)
							{
								Iterator<String> itr=libraryTags.iterator();  
								while(itr.hasNext())
								{
									String tempLibrary = itr.next();
									if(tag[i].toLowerCase().equals(tempLibrary.toLowerCase()))
									{
										String textToWrite = String.valueOf(post.getId()) + ";" + tempLibrary + ";" + post.getTitle();
										FileReadWrite.FileWrite(textToWrite, dataLocalPath + File.separator + "PostIDofAllJavaLibrary.txt");
									}
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
	
	public static void libraryPostRetrieve(String FileNamePath, HashSet<String> libraryTags, String []identifiersList)
	{
		
		SAXParserFactory saxparserfactory = SAXParserFactory.newInstance();
		try {
			saxparserfactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
			//saxparserfactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
			SAXParser saxparser = saxparserfactory.newSAXParser();
			
			/// Reading the files
			saxparser.parse(FileNamePath + File.separator + "Posts.xml", new DefaultHandler() {
				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes) {
					if (qName.equalsIgnoreCase("row")) {
						Post post = DataConverter.getPost(uri, localName, qName, attributes);
						/// Getting the tags only
						String tags = post.getTags();
						if (tags != null)
						{
							tags = tags.replace("><", " ");
							tags = tags.replace("<", "");
							tags = tags.replace(">", "");
							String []tag = tags.split(" ");
							for (int i = 0; i < tag.length; i++)
							{
								Iterator<String> itr=libraryTags.iterator();  
								while(itr.hasNext())
								{
									String tempLibrary = itr.next();
									if(tag[i].toLowerCase().equals(tempLibrary.toLowerCase()))
									{
										for(int j = 0; j< identifiersList.length; j++)
										{
											if(post.getTitle().toLowerCase().contains(identifiersList[j].toLowerCase()))
											{
												String textToWrite = String.valueOf(post.getId()) + " ; " + post.getTitle() + " ; " + tempLibrary + "\n";
												FileReadWrite.FileWrite(textToWrite, dataLocalPath + File.separator + "PostIDofJavaLibrary.txt");
											}
										}
									}
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
	
	@SuppressWarnings("unused")
	public static void libraryPostWithAnswer(String FileNamePath, ArrayList<String> PostID)
	{	
		int [][] PostIDAppreance = new int [PostID.size()][2];
		for(int i = 0; i< PostIDAppreance.length; i++)
		{
			PostIDAppreance[i][0] = Integer.parseInt(PostID.get(i));
			PostIDAppreance[i][1] = 0;
		}
		
		SAXParserFactory saxparserfactory = SAXParserFactory.newInstance();
				
		try {		
				saxparserfactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
				//saxparserfactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
				SAXParser saxparser = saxparserfactory.newSAXParser();
			
				/// Reading the files
				saxparser.parse(FileNamePath + File.separator + "Posts.xml", new DefaultHandler() {
				@Override
						
					public void startElement(String uri, String localName, String qName, Attributes attributes) {
						if (qName.equalsIgnoreCase("row")) {
							Post post = DataConverter.getPost(uri, localName, qName, attributes);
							for(int i = 0; i < PostIDAppreance.length; i++)
							{
								if(post.getParentId() == PostIDAppreance[i][0])
								{
									PostIDAppreance[i][1]++;
								}
								if(PostIDAppreance[i][1]>4)
								{
									String TextToWrite = PostIDAppreance[i][0] + ";" + post.getTitle();
									FileReadWrite.FileWrite(TextToWrite, dataLocalPath + File.separator + "PostIDofMultiAnsJava.txt");
									for(int j = i; j < PostIDAppreance.length - 1; j++)
									{
										PostIDAppreance[j][0] = PostIDAppreance[j + 1][0];
										PostIDAppreance[j][1] = PostIDAppreance[j + 1][1];
									}
									break;
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
	
	@SuppressWarnings({"unused", "deprecation"})
	public static void libraryInPostBody(String FileNamePath) 
	{	
		
		SAXParserFactory saxparserfactory = SAXParserFactory.newInstance();
				
		try {		
				saxparserfactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
				//saxparserfactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
				SAXParser saxparser = saxparserfactory.newSAXParser();
				
				//***************** Creating XLXS Files *******************//
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet javasheet = workbook.createSheet("javaLibraryPost");
				XSSFSheet pythonsheet = workbook.createSheet("pythonLibraryPost");
			
				/// Reading the files
				saxparser.parse(FileNamePath + File.separator + "Posts.xml", new DefaultHandler() {
				@Override
						
					public void startElement(String uri, String localName, String qName, Attributes attributes) {
						if (qName.equalsIgnoreCase("row")) {
							Post post = DataConverter.getPost(uri, localName, qName, attributes);
							String postBody = post.getBody();
							for(int i = 0; i < libraryToSearchJava.length; i++)
							{
								if(postBody.toLowerCase().contains(libraryToSearchJava[i].toLowerCase()))
								{
									String textToWrite = String.valueOf(post.getId()) + "$;$" + libraryToSearchJava[i] + "$;$"+ post.getTitle() + "$;$" + post.getBody() + "$;$" + String.valueOf(post.getParentId());
									FileReadWrite.FileWrite(textToWrite, dataLocalPath + File.separator + "javaLibraryPost.txt");
									break;
								}
								else if(postBody.toLowerCase().contains(libraryToSearchPython[i].toLowerCase()))
								{
									String textToWrite = String.valueOf(post.getId()) + "$;$" + libraryToSearchPython[i] + "$;$"+ post.getTitle() + "$;$" + post.getBody() + "$;$" + String.valueOf(post.getParentId());
									FileReadWrite.FileWrite(textToWrite, dataLocalPath + File.separator + "pythonLibraryPost.txt");
									break;
								}
							}
							
							/*
							for(int i = 0; i < libraryToSearchJava.length; i++)
							{
								if(postBody.toLowerCase().contains(libraryToSearchJava[i].toLowerCase()))
								{
									System.out.println("Match Found");
									Row row = javasheet.createRow(javaRowNumber++);
									int colNum = 0;
									
									Cell postID = row.createCell(colNum++);
									postID.setCellValue((Integer) post.getId());
									Cell libraryName= row.createCell(colNum++);
									libraryName.setCellValue((String)libraryToSearchJava[i]);
									Cell title = row.createCell(colNum++);
									title.setCellValue((String) post.getTitle());
									Cell body= row.createCell(colNum++);
									
									if(post.getBody().length()<32767)
										body.setCellValue((String) post.getBody());
									else
										body.setCellValue((String) post.getBody().substring(0, 32766));
									
									Cell parentID= row.createCell(colNum++);
									parentID.setCellValue((Integer) post.getParentId());
								}
							}
							for(int i = 0; i < libraryToSearchPython.length; i++)
							{
								if(postBody.toLowerCase().contains(libraryToSearchPython[i].toLowerCase()))
								{
									System.out.println("Match Found");
									Row row = pythonsheet.createRow(pythonRowNumber++);
									int colNum = 0;
									
									Cell postID = row.createCell(colNum++);
									postID.setCellValue((Integer) post.getId());
									Cell libraryName= row.createCell(colNum++);
									libraryName.setCellValue((String)libraryToSearchPython[i]);
									Cell title = row.createCell(colNum++);
									title.setCellValue((String) post.getTitle());
									Cell body= row.createCell(colNum++);
									
									if(post.getBody().length()<32767)
										body.setCellValue((String) post.getBody());
									else
										body.setCellValue((String) post.getBody().substring(0, 32766));
									
									Cell parentID= row.createCell(colNum++);
									parentID.setCellValue((Integer) post.getParentId());
								}
							}*/
						}
					}
			});	
				
				FileOutputStream outputStream = new FileOutputStream(dataLocalPath + File.separator + "LibraryPost.xlsx");
				workbook.write(outputStream);
				workbook.close();	
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

	
	public static void libraryTitlePostIDRetrieve()
	{
		HashSet<String> tempLibraryTags = new HashSet<String>();
		String TempLine;
		BufferedReader br;
		try {
			br = FileReadWrite.FileRead(dataLocalPath + File.separator + "PostIDofJavaLibrary.txt");
			while((TempLine = br.readLine())!= null)
			{
				String[] temps = TempLine.split(" ; ");
				if(temps.length>2)
					tempLibraryTags.add(temps[2]);
			}
			ArrayList<String> randomLibraries = librarySelection(tempLibraryTags);
			for(int i = 0; i < randomLibraries.size(); i++)
			{
				br = FileReadWrite.FileRead(dataLocalPath + File.separator + "PostIDofJavaLibrary.txt");
				while((TempLine = br.readLine())!= null)
				{
					String[] temps = TempLine.split(" ; ");
					if(temps.length > 2)
					{
						if(temps[2].equals(randomLibraries.get(i)))
						{
							FileReadWrite.FileWrite(TempLine, dataLocalPath + File.separator + "JavaPostIDofRandomLibrary.txt");
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				if (tempCategory.length > 2 && tempCategory[2].toLowerCase().equals("java"))
				{
					libraryTags.add(tempCategory[0]);
				}
				else
				{
					System.out.println(tempTag);
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
	
	public static ArrayList<String> librarySelection(HashSet<String> libraryTags)
	{
		HashSet<String> tempLibraryTags = new HashSet<String>();
		int size = libraryTags.size();
		int libraryConsidered = 25;
		for(int i = 0; i < libraryConsidered; i++)
		{
			int item = new Random().nextInt(size);
			int j = 0;
			for(Object obj: libraryTags)
			{
				if(j == item)
				{
					System.out.println(String.valueOf(obj));
					tempLibraryTags.add(String.valueOf(obj));
				}
				j++;
			}
		}
		
		ArrayList<String> libraries = new ArrayList<String>();
		for(Object obj:tempLibraryTags)
		{
			libraries.add(String.valueOf(obj));
		}
		return libraries;
	}
	
	public static void collateLibraryPostWithAnswers(String fileName1, String fileName2)
	{
		try {
			
			BufferedReader br1 = FileReadWrite.FileRead(fileName1);
			
			String tempRead1;
			String tempRead2;
			while((tempRead1 = br1.readLine()) != null)
			{
				String[] temps1 = tempRead1.split(";");
				if(temps1.length > 1)
				{
					BufferedReader br2 = FileReadWrite.FileRead(fileName2);
					while((tempRead2 = br2.readLine()) != null)
					{
						String[] temps2 = tempRead2.split(";");
						if(temps2.length > 1)
						{
							if(temps1[0].equals(temps2[0]))
							{
								FileReadWrite.FileWrite(tempRead2, dataLocalPath + File.separator + "JavaLibraryWith5Answers.txt" );
								break;
							}
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void collate_Question_Answers(String FileNamePath) {
		HashMap <Integer, ArrayList<Integer>> QuesAns = new HashMap<Integer, ArrayList<Integer>>(); 
		SAXParserFactory saxparserfactory = SAXParserFactory.newInstance();
		
		try {	
			FileWriter javaOutputFile = new FileWriter(new File("javaPosts.csv"));
			FileWriter javaScriptOutputFile = new FileWriter(new File("javaScriptPosts.csv"));
			FileWriter csharpOutputFile = new FileWriter(new File("javaPosts.csv"));
			FileWriter rScriptOutputFile = new FileWriter(new File("javaScriptPosts.csv"));
			FileWriter pythonOutputFile = new FileWriter(new File("javaPosts.csv"));
			
			CSVWriter javaWriter = new CSVWriter(javaOutputFile);
			CSVWriter javaScriptWriter = new CSVWriter(javaScriptOutputFile);
			CSVWriter csharpWriter = new CSVWriter(csharpOutputFile);
			CSVWriter rScirptWriter = new CSVWriter(rScriptOutputFile);
			CSVWriter pythonWriter = new CSVWriter(pythonOutputFile);
			ArrayList<String> temp = new ArrayList<String>();
			
				saxparserfactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
				//saxparserfactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
				SAXParser saxparser = saxparserfactory.newSAXParser();
				
				/// Reading the files
				saxparser.parse(FileNamePath + File.separator + "Posts.xml", new DefaultHandler() {
				@Override
						
					public void startElement(String uri, String localName, String qName, Attributes attributes) {
						if (qName.equalsIgnoreCase("row")) {
							Post post = DataConverter.getPost(uri, localName, qName, attributes);
							
							//// Getting Tagged Questions ////
							/* Only Lanugage Tagged Questions for Now */
							/*
							 * post.getBody().replaceAll("'", "")+"','"+post.getOwnerUserId()+"','"+post.getLastActivityDate()+"','"
		+post.getTitle()+"','"+post.getTags()+"','"+post.getAnswerCount()+"','"+post.getCommentCount()+"','"
		+post.getFavoriteCount()+"','"+FileNamepath+"','"+post.getAcceptedAnswerId()+"')";
							 */
							
							String tags = post.getTags();
							if (tags != null)
							{
								tags = tags.replace("><", " ");
								tags = tags.replace("<", "");
								tags = tags.replace(">", "");
								String []tag = tags.split(" ");
								for (int i = 0; i < tag.length; i++)
								{
									if(tag[i].toLowerCase().equals("javascript"))
									{
										ArrayList<String> temp = new ArrayList<String>();
										temp.add(Integer.toString(post.getId()));
										temp.add(Integer.toString(post.getPostTypeId()));
										temp.add(post.getBody());
										temp.add(post.getTitle());
										temp.add(Integer.toString(post.getParentId()));
										temp.add(Integer.toString(post.getAcceptedAnswerId()));
										javaScriptWriter.writeNext(temp.toArray(new String[temp.size()]));
									}
									else if(tag[i].toLowerCase().equals("java"))
									{
										ArrayList<String> temp = new ArrayList<String>();
										temp.add(Integer.toString(post.getId()));
										temp.add(Integer.toString(post.getPostTypeId()));
										temp.add(post.getBody());
										temp.add(post.getTitle());
										temp.add(Integer.toString(post.getParentId()));
										temp.add(Integer.toString(post.getAcceptedAnswerId()));
										javaWriter.writeNext(temp.toArray(new String[temp.size()]));
										
									}
									
									else if(tag[i].toLowerCase().equals("python"))
									{
										ArrayList<String> temp = new ArrayList<String>();
										temp.add(Integer.toString(post.getId()));
										temp.add(Integer.toString(post.getPostTypeId()));
										temp.add(post.getBody());
										temp.add(post.getTitle());
										temp.add(Integer.toString(post.getParentId()));
										temp.add(Integer.toString(post.getAcceptedAnswerId()));
										pythonWriter.writeNext(temp.toArray(new String[temp.size()]));
									}
									
									else if(tag[i].toLowerCase().equals("r"))
									{
										ArrayList<String> temp = new ArrayList<String>();
										temp.add(Integer.toString(post.getId()));
										temp.add(Integer.toString(post.getPostTypeId()));
										temp.add(post.getBody());
										temp.add(post.getTitle());
										temp.add(Integer.toString(post.getParentId()));
										temp.add(Integer.toString(post.getAcceptedAnswerId()));
										rScirptWriter.writeNext(temp.toArray(new String[temp.size()]));
									}
									
									else if(tag[i].toLowerCase().contains("c#") || tag[i].toLowerCase().equals("csharp"))
									{
										ArrayList<String> temp = new ArrayList<String>();
										temp.add(Integer.toString(post.getId()));
										temp.add(Integer.toString(post.getPostTypeId()));
										temp.add(post.getBody());
										temp.add(post.getTitle());
										temp.add(Integer.toString(post.getParentId()));
										temp.add(Integer.toString(post.getAcceptedAnswerId()));
										csharpWriter.writeNext(temp.toArray(new String[temp.size()]));
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
	
}
