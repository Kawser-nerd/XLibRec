package com.sail.so.xml;

import java.io.File;
import java.io.Serializable;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.sail.xml.handler.DataConverter;

public class Comment implements Serializable{
	public static String SITE = "Site";
	public static String ID = "Id";
	public static String POST_ID = "PostId";
	public static String SCORE = "Score";
	public static String TEXT = "Text";
	public static String CREATION_DATE = "CreationDate";
	public static String USER_DISPLAY_NAME = "UserDisplayName";
	public static String USER_ID = "UserId";

	private String site;
	private int id;
	private int postId;
	private int score;
	private String text;
	private String creationDate;
	private String userDisplayName;
	private int userId;

	public Comment() {
		this.site = null;
		this.id = -999;
		this.postId = -999;
		this.score = 0;
		this.text = null;
		this.creationDate = null;
		this.userDisplayName = null;
		this.userId = -999;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getUserDisplayName() {
		return userDisplayName;
	}

	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Comment [site=" + site + ", id=" + id + ", postId=" + postId + ", score=" + score + ", text=" + text
				+ ", creationDate=" + creationDate + ", userDisplayName=" + userDisplayName + ", userId=" + userId
				+ "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		saxParserFactory.setValidating(true);
		
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			saxParser.parse("/media/parvez/IntelSSD/stackexchange/3dprinting.meta.stackexchange.com/PostLinks.xml", new DefaultHandler() {
				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					// TODO Auto-generated method stub
					if (qName.equalsIgnoreCase("row")) {
						Tag tag = DataConverter.getTag(uri, localName, qName, attributes);
						System.out.println(tag.toString());
					}
				}
			});
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
