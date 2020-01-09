package com.sail.so.xml;

import java.io.Serializable;

public class Post implements Serializable{
	
	public static String SITE = "Site";
	public static String ID = "Id";
	public static String POST_TYPE_ID = "PostTypeId";
	public static String ACCEPTED_ANSWER_ID = "AcceptedAnswerId";
	public static String PARENT_ID = "ParentId";
	public static String CREATION_DATE = "CreationDate";
	public static String DELETION_DATE = "DeletionDate";
	public static String SCORE = "Score";
	public static String VIEW_COUNT = "ViewCount";
	public static String BODY = "Body";
	public static String OWNER_USER_ID = "OwnerUserId";
	public static String OWNER_DISPLAY_NAME = "OwnerDisplayName";
	public static String LAST_EDITOR_USER_ID = "LastEditorUserId";
	public static String LAST_EDITOR_DISPLAY_NAME = "LastEditorDisplayName";
	public static String LAST_EDIT_DATE = "LastEditDate";
	public static String LAST_ACTIVITY_DATE = "LastActivityDate";
	public static String TITLE = "Title";
	public static String TAGS = "Tags";
	public static String ANSWER_COUNT = "AnswerCount";
	public static String COMMENT_COUNT = "CommentCount";
	public static String FAVORITE_COUNT = "FavoriteCount";
	public static String CLOSED_DATE = "ClosedDate";
	public static String COMMUNITY_OWNED_DATE = "CommunityOwnedDate";
	
	private String site;
	private int id;
	private int postTypeId;
	private int acceptedAnswerId;
	private int parentId;
	private String creationDate;
	private String deletionDate;
	private int score;
	private int viewCount;
	private String body;
	private int ownerUserId;
	private String ownerDisplayName;
	private int lastEditorUserId;
	private String lastEditorDisplayName;
	private String lastEditDate;
	private String lastActivityDate;
	private String title;
	private String tags;
	private int answerCount;
	private int commentCount;
	private int favoriteCount;
	private String closedDate;
	private String communityOwnedDate;
	
	public Post() {
		
		this.site = null;
		this.id = -999;
		this.postTypeId = -999;
		this.acceptedAnswerId = -999;
		this.parentId = -999;
		this.creationDate = null;
		this.deletionDate = null;
		this.score = 0;
		this.viewCount = 0;
		this.body = null;
		this.ownerUserId = -999;
		this.ownerDisplayName = null;
		this.lastEditorUserId = -999;
		this.lastEditorDisplayName = null;
		this.lastEditDate = null;
		this.lastActivityDate = null;
		this.title = null;
		this.tags = null;
		this.answerCount = 0;
		this.commentCount = 0;
		this.favoriteCount = 0;
		this.closedDate = null;
		this.communityOwnedDate = null;
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
	public int getPostTypeId() {
		return postTypeId;
	}
	public void setPostTypeId(int _postTypeId) {
		this.postTypeId = _postTypeId;
	}
	public int getAcceptedAnswerId() {
		return acceptedAnswerId;
	}
	public void setAcceptedAnswerId(int _acceptedAnswerId) {
		this.acceptedAnswerId = _acceptedAnswerId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int _parentId) {
		this.parentId = _parentId;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String _creationDate) {
		this.creationDate = _creationDate;
	}
	public String getDeletionDate() {
		return deletionDate;
	}
	public void setDeletionDate(String _deletionDate) {
		deletionDate = _deletionDate;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int _score) {
		this.score = _score;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int _viewCount) {
		this.viewCount = _viewCount;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String _body) {
		this.body = _body;
	}
	public int getOwnerUserId() {
		return ownerUserId;
	}
	public void setOwnerUserId(int _ownerUserId) {
		this.ownerUserId = _ownerUserId;
	}
	public String getOwnerDisplayName() {
		return ownerDisplayName;
	}
	public void setOwnerDisplayName(String _ownerDisplayName) {
		this.ownerDisplayName = _ownerDisplayName;
	}
	public int getLastEditorUserId() {
		return lastEditorUserId;
	}
	public void setLastEditorUserId(int _lastEditorUserId) {
		this.lastEditorUserId = _lastEditorUserId;
	}
	public String getLastEditorDisplayName() {
		return lastEditorDisplayName;
	}
	public void setLastEditorDisplayName(String _lastEditorDisplayName) {
		this.lastEditorDisplayName = _lastEditorDisplayName;
	}
	public String getLastEditDate() {
		return lastEditDate;
	}
	public void setLastEditDate(String _lastEditDate) {
		this.lastEditDate = _lastEditDate;
	}
	public String getLastActivityDate() {
		return lastActivityDate;
	}
	public void setLastActivityDate(String _lastActivityDate) {
		this.lastActivityDate = _lastActivityDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String _title) {
		this.title = _title;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String _tags) {
		this.tags = _tags;
	}
	public int getAnswerCount() {
		return answerCount;
	}
	public void setAnswerCount(int _answerCount) {
		this.answerCount = _answerCount;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int _commentCount) {
		this.commentCount = _commentCount;
	}
	public int getFavoriteCount() {
		return favoriteCount;
	}
	public void setFavoriteCount(int _favoriteCount) {
		this.favoriteCount = _favoriteCount;
	}
	public String getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(String _closedDate) {
		this.closedDate = _closedDate;
	}
	public String getCommunityOwnedDate() {
		return communityOwnedDate;
	}
	public void setCommunityOwnedDate(String _communityOwnedDate) {
		this.communityOwnedDate = _communityOwnedDate;
	}

	@Override
	public String toString() {
		return "Post [site=" + site + ", id=" + id + ", postTypeId=" + postTypeId + ", acceptedAnswerId="
				+ acceptedAnswerId + ", parentId=" + parentId + ", creationDate=" + creationDate + ", deletionDate="
				+ deletionDate + ", score=" + score + ", viewCount=" + viewCount + ", body=" + body + ", ownerUserId="
				+ ownerUserId + ", ownerDisplayName=" + ownerDisplayName + ", lastEditorUserId=" + lastEditorUserId
				+ ", lastEditorDisplayName=" + lastEditorDisplayName + ", lastEditDate=" + lastEditDate
				+ ", lastActivityDate=" + lastActivityDate + ", title=" + title + ", tags=" + tags + ", answerCount="
				+ answerCount + ", commentCount=" + commentCount + ", favoriteCount=" + favoriteCount + ", closedDate="
				+ closedDate + ", communityOwnedDate=" + communityOwnedDate + "]";
	}

}
