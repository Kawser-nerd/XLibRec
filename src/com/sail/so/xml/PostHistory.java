package com.sail.so.xml;

import java.io.Serializable;

public class PostHistory implements Serializable {

	public static String SITE = "Site";
	public static String ID = "Id";
	public static String POST_HISTORY_TYPE_ID = "PostHistoryTypeId";
	public static String POST_ID = "PostId";
	public static String REVISION_GUID = "RevisionGUID";
	public static String CREATION_DATE = "CreationDate";
	public static String USER_ID = "UserId";
	public static String USER_DISPLAY_NAME = "UserDisplayName";
	public static String COMMENT = "Comment";
	public static String TEXT = "Text";
	public static String ORIGIN = "Origin";
	public static String ORIGIN_ID = "OriginId";
	public static String DESTINATION = "Destination";
	public static String DESTINATION_ID = "DestinationId";

	private String site;
	private int id;
	private int postHistoryTypeId;
	private int postId;
	private String revisionGUID;
	private String creationDate;
	private int userId;
	private String userDisplayName;
	private String comment;
	private String text;
	private String origin;
	private int originId;
	private String destination;
	private int destinationId;

	public PostHistory() {

		this.site = null;
		this.id = -999;
		this.postHistoryTypeId = -999;
		this.postId = -999;
		this.revisionGUID = null;
		this.creationDate = null;
		this.userId = -999;
		this.userDisplayName = null;
		this.comment = null;
		this.text = null;
		this.origin = null;
		this.originId = -999;
		this.destination = null;
		this.destinationId = -999;
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

	public String getRevisionGUID() {
		return revisionGUID;
	}

	public void setRevisionGUID(String revisionGUID) {
		this.revisionGUID = revisionGUID;
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

	public String getUserDisplayName() {
		return userDisplayName;
	}

	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public int getOriginId() {
		return originId;
	}

	public void setOriginId(int originId) {
		this.originId = originId;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}

	@Override
	public String toString() {
		return "PostHistory [site=" + site + ", id=" + id + ", postHistoryTypeId=" + postHistoryTypeId + ", postId="
				+ postId + ", revisionGUID=" + revisionGUID + ", creationDate=" + creationDate + ", userId=" + userId
				+ ", userDisplayName=" + userDisplayName + ", comment=" + comment + ", text=" + text + ", origin="
				+ origin + ", originId=" + originId + ", destination=" + destination + ", destinationId="
				+ destinationId + "]";
	}

}
