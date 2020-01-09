package com.sail.so.xml;

import java.io.Serializable;

public class PostLink implements Serializable{

	public static String SITE = "Site";
	public static String ID = "Id";
	public static String CREATION_DATE = "CreationDate";
	public static String POST_ID = "PostId";
	public static String RELATED_POST_ID = "RelatedPostId";
	public static String LINK_TYPE_ID = 	"LinkTypeId";
	
	private String site;
	private int id;
	private String CreationDate;
	private int postId;
	private int relatedPostId;
	private int linkTypeId;
	
	public PostLink() {
		this.site = null;
		this.id = -999;
		this.CreationDate = null;
		this.postId = -999;
		this.relatedPostId = -999;
		this.linkTypeId = -999;
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

	public String getCreationDate() {
		return CreationDate;
	}

	public void setCreationDate(String creationDate) {
		CreationDate = creationDate;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getRelatedPostId() {
		return relatedPostId;
	}

	public void setRelatedPostId(int relatedPostId) {
		this.relatedPostId = relatedPostId;
	}

	public int getLinkTypeId() {
		return linkTypeId;
	}

	public void setLinkTypeId(int linkTypeId) {
		this.linkTypeId = linkTypeId;
	}

	@Override
	public String toString() {
		return "PostLink [site=" + site + ", id=" + id + ", CreationDate=" + CreationDate + ", postId=" + postId
				+ ", relatedPostId=" + relatedPostId + ", linkTypeId=" + linkTypeId + "]";
	}
}
