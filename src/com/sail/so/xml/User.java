package com.sail.so.xml;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.sail.xml.handler.DataConverter;

public class User implements Serializable{

	public static String SITE = "Site";
	public static String ID = "Id";
	public static String REPUTATION = "Reputation";
	public static String CREATION_DATE = "CreationDate";
	public static String DISPLAY_NAME = "DisplayName";
	public static String LAST_ACCESS_DATE = "LastAccessDate";
	public static String WEBSITE_URL = "WebsiteUrl";
	public static String LOCATION = "Location";
	public static String ABOUT_ME = "AboutMe";
	public static String VIEWS = "Views";
	public static String UP_VOTES = "UpVotes";
	public static String DOWN_VOTES = "DownVotes";
	public static String PROFILE_IMAGE_URL = "ProfileImageUrl";
	public static String ACCOUNT_ID = "AccountId";

	private String site;
	private int id;
	private int reputation;
	private String creationDate;
	private String displayName;
	private String lastAccessDate;
	private String websiteUrl;
	private String location;
	private String aboutMe;
	private int views;
	private int upVotes;
	private int downVotes;
	private String profileImageUrl;
	private int accountId;

	public User() {
		this.site = null;
		this.id = -999;
		this.reputation = -999;
		this.creationDate = null;
		this.displayName = null;
		this.lastAccessDate = null;
		this.websiteUrl = null;
		this.location = null;
		this.aboutMe = null;
		this.views = 0;
		this.upVotes = 0;
		this.downVotes = 0;
		this.profileImageUrl = null;
		this.accountId = -999;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String _site) {
		site = _site;
	}

	public int getId() {
		return id;
	}

	public void setId(int _id) {
		this.id = _id;
	}

	public int getReputation() {
		return reputation;
	}

	public void setReputation(int _reputation) {
		this.reputation = _reputation;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String _creationDate) {
		this.creationDate = _creationDate;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String _displayName) {
		this.displayName = _displayName;
	}

	public String getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(String _lastAccessDate) {
		this.lastAccessDate = _lastAccessDate;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String _websiteUrl) {
		this.websiteUrl = _websiteUrl;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String _location) {
		this.location = _location;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String _aboutMe) {
		this.aboutMe = _aboutMe;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int _views) {
		this.views = _views;
	}

	public int getUpVotes() {
		return upVotes;
	}

	public void setUpVotes(int _upVotes) {
		this.upVotes = _upVotes;
	}

	public int getDownVotes() {
		return downVotes;
	}

	public void setDownVotes(int _downVotes) {
		this.downVotes = _downVotes;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String _profileImageUrl) {
		this.profileImageUrl = _profileImageUrl;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int _accountId) {
		this.accountId = _accountId;
	}

	@Override
	public String toString() {
		return "User [site=" + site + ", id=" + id + ", reputation=" + reputation + ", creationDate=" + creationDate
				+ ", displayName=" + displayName + ", lastAccessDate=" + lastAccessDate + ", websiteUrl=" + websiteUrl
				+ ", location=" + location + ", aboutMe=" + aboutMe + ", views=" + views + ", upVotes=" + upVotes
				+ ", downVotes=" + downVotes + ", profileImageUrl=" + profileImageUrl + ", accountId=" + accountId
				+ "]";
	}
	
	public static void main(String args[]) {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		saxParserFactory.setValidating(true);
		
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			saxParser.parse("/archive/parvezku01/StackExchange/stackexchange/3dprinting.meta.stackexchange.com/Posts.xml", new DefaultHandler() {

				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					if (qName.equalsIgnoreCase("row")) {
						Post post = DataConverter.getPost(uri, localName, qName, attributes);
						System.out.println(post.toString());
					}
				}
				
			});
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}