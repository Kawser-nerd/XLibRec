package com.sail.so.xml;

import java.io.Serializable;

public class Badge implements Serializable{
	
	public static String SITE = "Site";
	public static String ID = "Id";
	public static String USER_ID = "UserId";
	public static String NAME = "Name";
	public static String DATE = "Date";
	public static String BADGE_CLASS = "Class";
	public static String TAG_BASED = "TagBased";

	private String site;
	private long id;
	private long userId;
	private String name;
	private String date;
	private int badgeClass;
	private boolean tagBased;
	
	public Badge() {
		this.site = null;
		this.id = -999;
		this.userId = -999;
		this.name = null;
		this.date = null;
		this.badgeClass = -999;
		this.tagBased = false;
	}
	
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getBadgeClass() {
		return badgeClass;
	}
	public void setBadgeClass(int badgeClass) {
		this.badgeClass = badgeClass;
	}
	public boolean isTagBased() {
		return tagBased;
	}
	public void setTagBased(boolean tagBased) {
		this.tagBased = tagBased;
	}

	@Override
	public String toString() {
		return "Badge [site=" + site + ", id=" + id + ", userId=" + userId + ", name=" + name + ", date=" + date
				+ ", badgeClass=" + badgeClass + ", tagBased=" + tagBased + "]";
	}
	
}
