package com.sail.so.xml;

import java.io.Serializable;

public class LightUser implements Serializable {

	public static String ID = "Id";
	public static String REPUTATION = "Reputation";
	public static String CREATION_DATE = "CreationDate";
	public static String DISPLAY_NAME = "DisplayName";
	public static String VIEWS = "Views";
	public static String UP_VOTES = "UpVotes";
	public static String DOWN_VOTES = "DownVotes";
	public static String ACCOUNT_ID = "AccountId";

	private int id;
	private int reputation;
	private String creationDate;
	private String displayName;
	private String site;
	private int views;
	private int upVotes;
	private int downVotes;
	private int accountId;

	public LightUser() {
		this.id = -999;
		this.reputation = -999;
		this.creationDate = null;
		this.displayName = null;
		this.views = 0;
		this.upVotes = 0;
		this.downVotes = 0;
		this.accountId = -999;
		this.site = null;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
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

	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getUpVotes() {
		return upVotes;
	}

	public void setUpVotes(int upVotes) {
		this.upVotes = upVotes;
	}

	public int getDownVotes() {
		return downVotes;
	}

	public void setDownVotes(int downVotes) {
		this.downVotes = downVotes;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "LightUser [id=" + id + ", reputation=" + reputation + ", creationDate=" + creationDate
				+ ", displayName=" + displayName + ", site=" + site + ", views=" + views + ", upVotes=" + upVotes
				+ ", downVotes=" + downVotes + ", accountId=" + accountId + "]";
	}

	public static void main(String args[]) {
	
	}

}