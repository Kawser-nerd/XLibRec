package com.sail.so.xml;

import java.io.Serializable;

public class Vote implements Serializable{
	public static String SITE = "Site";
	public static String ID = "Id";
	public static String POST_ID = "PostId";
	public static String VOTE_TYPE_ID = "VoteTypeId";
	public static String USER_ID = "UserId";
	public static String CREATION_DATE = "CreationDate";
	public static String BOUNTY_AMOUNT = "BountyAmount";

	private String site;
	private int id;
	private int postId;
	private int voteTypeId;
	private int userId;
	private String CreationDate;
	private int bountyAmount;

	public Vote() {
		this.site = null;
		this.id = -999;
		this.postId = -999;
		this.voteTypeId = -999;
		this.userId = -999;
		this.CreationDate = null;
		this.bountyAmount = 0;
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

	public int getVoteTypeId() {
		return voteTypeId;
	}

	public void setVoteTypeId(int voteTypeId) {
		this.voteTypeId = voteTypeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCreationDate() {
		return CreationDate;
	}

	public void setCreationDate(String creationDate) {
		CreationDate = creationDate;
	}

	public int getBountyAmount() {
		return bountyAmount;
	}

	public void setBountyAmount(int bountyAmount) {
		this.bountyAmount = bountyAmount;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	@Override
	public String toString() {
		return "Vote [site=" + site + ", id=" + id + ", postId=" + postId + ", voteTypeId=" + voteTypeId + ", userId="
				+ userId + ", CreationDate=" + CreationDate + ", bountyAmount=" + bountyAmount + "]";
	}

}
