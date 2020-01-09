package com.srlab.so.main;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.helpers.DefaultHandler;

import com.sail.so.xml.Post;
import com.sail.xml.handler.DataConverter;
import com.srlab.database.mysql.MySQlOperation;

public class XmlParserSo {
	
	public static String SOPath = "/home/sr-p2irc-big14/Desktop/stackexchange/";
	public static String SOPath_Posts = "/home/sr-p2irc-big14/Desktop/stackexchange/stackoverflow_com-Posts_7z";
	public static String dataLocalPath = "/home/sr-p2irc-big14/Data/APILearn";
	
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
		}
		getTitles(SOPath_Posts);
		*/
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
									MySQlOperation.insertDB(post, FileNamePath);
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
						//if (posttypeID == 5 && post.getTags() != null)
						if(posttypeID == 5)
						{
							System.out.println(post.toString());
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
		
	}
}
