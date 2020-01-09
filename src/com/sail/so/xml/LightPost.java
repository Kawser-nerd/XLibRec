package com.sail.so.xml;

import java.io.Serializable;


public class LightPost implements Serializable {

	public static String ID = "Id";
	public static String POST_TYPE_ID = "PostTypeId";
	public static String ACCEPTED_ANSWER_ID = "AcceptedAnswerId";
	public static String PARENT_ID = "ParentId";
	public static String CREATION_DATE = "CreationDate";
	public static String SCORE = "Score";
	public static String VIEW_COUNT = "ViewCount";
	
	public static String OWNER_USER_ID = "OwnerUserId";
	public static String TAGS = "Tags";
	public static String ANSWER_COUNT = "AnswerCount";
	public static String COMMENT_COUNT = "CommentCount";
	public static String FAVORITE_COUNT = "FavoriteCount";
	public static String CLOSED_DATE = "ClosedDate";

	private int id;
	private int postTypeId;
	private int acceptedAnswerId;
	private int parentId;
	private String creationDate;
	private int score;
	private int viewCount;
	private int ownerUserId;
	private String tags;
	private int answerCount;
	private int commentCount;
	private int favoriteCount;
	private String closedDate;

	public LightPost() {
		this.id = -999;
		this.postTypeId = -999;
		this.acceptedAnswerId = -999;
		this.parentId = -999;
		this.creationDate = null;
		this.score = 0;
		this.viewCount = 0;
		this.ownerUserId = -999;
		this.tags = null;
		this.answerCount = 0;
		this.commentCount = 0;
		this.favoriteCount = 0;
		this.closedDate = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPostTypeId() {
		return postTypeId;
	}

	public void setPostTypeId(int postTypeId) {
		this.postTypeId = postTypeId;
	}

	public int getAcceptedAnswerId() {
		return acceptedAnswerId;
	}

	public void setAcceptedAnswerId(int acceptedAnswerId) {
		this.acceptedAnswerId = acceptedAnswerId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public int getOwnerUserId() {
		return ownerUserId;
	}

	public void setOwnerUserId(int ownerUserId) {
		this.ownerUserId = ownerUserId;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(String closedDate) {
		this.closedDate = closedDate;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	@Override
	public String toString() {
		return "LightPost [id=" + id + ", postTypeId=" + postTypeId + ", acceptedAnswerId=" + acceptedAnswerId
				+ ", parentId=" + parentId + ", creationDate=" + creationDate + ", score=" + score + ", viewCount="
				+ viewCount + ", ownerUserId=" + ownerUserId + ", tags=" + tags + ", answerCount=" + answerCount
				+ ", commentCount=" + commentCount + ", favoriteCount=" + favoriteCount + ", closedDate=" + closedDate
				+ "]";
	}
}
