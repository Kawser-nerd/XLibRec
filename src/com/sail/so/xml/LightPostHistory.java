package com.sail.so.xml;

import java.io.Serializable;

public class LightPostHistory implements Serializable {
	
	public static String ID = "Id";
	public static String POST_HISTORY_TYPE_ID = "PostHistoryTypeId";
	public static String POST_ID = "PostId";
	public static String CREATION_DATE = "CreationDate";
	public static String USER_ID = "UserId";
	
	private int id;
	private int postHistoryTypeId;
	private int postId;
	private String creationDate;
	private int userId;
	
	public LightPostHistory() {

		this.id = -999;
		this.postHistoryTypeId = -999;
		this.postId = -999;
		this.creationDate = null;
		this.userId = -999;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPostHistoryTypeId() {
		return postHistoryTypeId;
	}

	public void setPostHistoryTypeId(int postHistoryTypeId) {
		this.postHistoryTypeId = postHistoryTypeId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	
	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "LightPostHistory [id=" + id + ", postHistoryTypeId=" + postHistoryTypeId + ", postId=" + postId
				+ ", creationDate=" + creationDate + ", userId=" + userId + "]";
	}

}
