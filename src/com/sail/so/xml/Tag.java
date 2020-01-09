package com.sail.so.xml;

import java.io.Serializable;

public class Tag implements Serializable{
	
	public static String SITE = "Site";
	public static String ID = "Id";
	public static String TAG_NAME = "TagName";
	public static String COUNT = "Count";
	public static String EXCERPT_POST_ID = "ExcerptPostId";
	public static String WIKI_POST_ID = "WikiPostId";
	
	private String site;
	private long id;
	private String tagName;
	private int count;
	private long excerptPostId;
	private long wikiPostId;
	
	public Tag() {
		this.site = null;
		this.id = -999;
		this.tagName = null;
		this.count = 0;
		this.excerptPostId = -999;
		this.wikiPostId = -999;
	}
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public long getExcerptPostId() {
		return excerptPostId;
	}
	public void setExcerptPostId(long excerptPostId) {
		this.excerptPostId = excerptPostId;
	}
	public long getWikiPostId() {
		return wikiPostId;
	}
	public void setWikiPostId(long wikiPostId) {
		this.wikiPostId = wikiPostId;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}


	@Override
	public String toString() {
		return "Tag [site=" + site + ", id=" + id + ", tagName=" + tagName + ", count=" + count + ", excerptPostId="
				+ excerptPostId + ", wikiPostId=" + wikiPostId + "]";
	}

	
}
