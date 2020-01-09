package com.sail.xml.handler;

import org.xml.sax.Attributes;

import com.sail.so.xml.Badge;
import com.sail.so.xml.Comment;
import com.sail.so.xml.LightPost;
import com.sail.so.xml.LightPostHistory;
import com.sail.so.xml.LightUser;
import com.sail.so.xml.Post;
import com.sail.so.xml.PostHistory;
import com.sail.so.xml.PostLink;
import com.sail.so.xml.Tag;
import com.sail.so.xml.User;
import com.sail.so.xml.Vote;

public class DataConverter {

	public static String getMigrateSite(String input) {
		if(input.startsWith("from http://")||input.startsWith("from https://")) {
			int index1 = input.indexOf("://") + "://".length();
			int index2 = input.indexOf("/", index1);
			String site = input.substring(index1, index2);		
			return site;
		}
		else if(input.startsWith("to http://")||input.startsWith("to https://")) {
			int index1 = input.indexOf("://") + "://".length();
			int index2 = input.indexOf("/", index1);
			String site = input.substring(index1, index2);	
			return site;
		}
		else return null;
	}
	
	public static int getMigrateId(String input) {
		if(input.startsWith("from http://")||input.startsWith("from https://")) {
			int index1 = input.indexOf("/questions/") + "/questions/".length();
			int index2 = input.indexOf("/", index1);
			String id = input.substring(index1, index2);		
			return Integer.parseInt(id);
		}
		else if(input.startsWith("to https://")||input.startsWith("to https://")) {
			int index1 = input.indexOf("/questions/") + "/questions/".length();
			int index2 = input.indexOf("/", index1);
			String id = input.substring(index1, index2);
			return Integer.parseInt(id);
		}
		else return -999;
	}
	
	
	
	public static Tag getTag(String uri, String localName, String qName, Attributes attributes) {
		
		String site = attributes.getValue(PostHistory.SITE);
		int id = Integer.parseInt(attributes.getValue(Tag.ID));
		String tagName = attributes.getValue(Tag.TAG_NAME);
		int count = attributes.getValue(Tag.COUNT)!=null? Integer.parseInt(attributes.getValue(Tag.COUNT)):0;
		int excerptPostId = attributes.getValue(Tag.EXCERPT_POST_ID)!=null? Integer.parseInt(attributes.getValue(Tag.EXCERPT_POST_ID)):-999;
		int wikiPostId = attributes.getValue(Tag.WIKI_POST_ID)!=null? Integer.parseInt(attributes.getValue(Tag.WIKI_POST_ID)):-999;
	
		Tag tag = new Tag();
		tag.setSite(site);
		tag.setId(id);
		tag.setTagName(tagName);
		tag.setCount(count);
		tag.setExcerptPostId(excerptPostId);
		tag.setWikiPostId(wikiPostId);
		return tag;
	}
	
	public static Comment getComment(String uri, String localName, String qName, Attributes attributes) {
		
		String site = attributes.getValue(PostHistory.SITE);
		int id = Integer.parseInt(attributes.getValue(Comment.ID));
		int postId = Integer.parseInt(attributes.getValue(Comment.POST_ID));
		int score = attributes.getValue(Comment.SCORE)!=null?Integer.parseInt(attributes.getValue(Comment.SCORE)):0;
		String text = attributes.getValue(Comment.TEXT);
		String creationDate = attributes.getValue(Comment.CREATION_DATE);
		String userDisplayName = attributes.getValue(Comment.USER_DISPLAY_NAME);
		int userId = attributes.getValue(Comment.USER_ID)!=null? Integer.parseInt(attributes.getValue(Comment.USER_ID)):-999;
		
		Comment comment = new Comment();
		comment.setSite(site);
		comment.setId(id);
		comment.setPostId(postId);
		comment.setScore(score);
		comment.setText(text);
		comment.setCreationDate(creationDate);
		comment.setUserDisplayName(userDisplayName);
		comment.setUserId(userId);
		return comment;
	}
	
	public static Badge getBadge(String uri, String localName, String qName, Attributes attributes) {
		
		String site = attributes.getValue(PostHistory.SITE);
		int id = Integer.parseInt(attributes.getValue(Badge.ID));
		int userId = attributes.getValue(Badge.USER_ID)!=null?Integer.parseInt(attributes.getValue(Badge.USER_ID)):-999;
		String name = attributes.getValue(Badge.NAME);
		String date = attributes.getValue(Badge.DATE);
		int badgeClass = attributes.getValue(Badge.BADGE_CLASS)!=null? Integer.parseInt(attributes.getValue(Badge.BADGE_CLASS)):-999;
		boolean tagBased = attributes.getValue(Badge.TAG_BASED)!=null?Boolean.parseBoolean(attributes.getValue(Badge.TAG_BASED)):false;
		
		Badge badge = new Badge();
		badge.setSite(site);
		badge.setId(id);
		badge.setUserId(userId);
		badge.setName(name);
		badge.setDate(date);
		badge.setBadgeClass(badgeClass);
		badge.setTagBased(tagBased);
		return badge;
	}

	public static Vote getVote(String uri, String localName, String qName, Attributes attributes) {
		
		String site = attributes.getValue(PostHistory.SITE);
		int id = Integer.parseInt(attributes.getValue(Vote.ID));
		int postId = attributes.getValue(Vote.POST_ID)!=null?Integer.parseInt(attributes.getValue(Vote.POST_ID)):-999;
		int voteTypeId = attributes.getValue(Vote.VOTE_TYPE_ID)!=null?Integer.parseInt(attributes.getValue(Vote.VOTE_TYPE_ID)):-999;
		int userId = attributes.getValue(Vote.USER_ID)!=null?Integer.parseInt(attributes.getValue(Vote.USER_ID)):-999;
		String creationDate = attributes.getValue(Vote.CREATION_DATE);
		int bountyAmount = attributes.getValue(Vote.BOUNTY_AMOUNT)!=null?Integer.parseInt(attributes.getValue(Vote.BOUNTY_AMOUNT)):0;
		
		Vote vote = new Vote();
		vote.setSite(site);
		vote.setId(id);
		vote.setPostId(postId);
		vote.setVoteTypeId(voteTypeId);
		vote.setUserId(userId);
		vote.setCreationDate(creationDate);
		vote.setBountyAmount(bountyAmount);
		return vote;
	}
	
	public static PostLink getPostLink(String uri, String localName, String qName, Attributes attributes) {
		
		String site = attributes.getValue(PostHistory.SITE);
		int id = Integer.parseInt(attributes.getValue(PostLink.ID));
		String creationDate = attributes.getValue(PostLink.CREATION_DATE);
		int postId = attributes.getValue(PostLink.POST_ID)!=null?Integer.parseInt(attributes.getValue(PostLink.POST_ID)):-999;
		int relatedPostId = attributes.getValue(PostLink.RELATED_POST_ID)!=null?Integer.parseInt(attributes.getValue(PostLink.RELATED_POST_ID)):-999;
		int linkTypeId = attributes.getValue(PostLink.LINK_TYPE_ID)!=null?  Integer.parseInt(attributes.getValue(PostLink.LINK_TYPE_ID)):-999;
			
		PostLink postLink = new PostLink();
		postLink.setSite(site);
		postLink.setId(id);
		postLink.setCreationDate(creationDate);
		postLink.setPostId(postId);
		postLink.setRelatedPostId(relatedPostId);
		postLink.setLinkTypeId(linkTypeId);
		return postLink;
	}
	
	public static PostHistory getPostHistory(String uri, String localName, String qName, Attributes attributes) {
		String site = attributes.getValue(PostHistory.SITE);
		int id = Integer.parseInt(attributes.getValue(PostHistory.ID));
		int postHistoryTypeId = Integer.parseInt(attributes.getValue(PostHistory.POST_HISTORY_TYPE_ID));
		int postId = Integer.parseInt(attributes.getValue(PostHistory.POST_ID));
		String revisionGUID = (attributes.getValue(PostHistory.REVISION_GUID));	
		String creationDate = attributes.getValue(PostHistory.CREATION_DATE);
		int userId = attributes.getValue(PostHistory.USER_ID)!=null? Integer.parseInt(attributes.getValue(PostHistory.USER_ID)):-999;
		String userDisplayName = attributes.getValue(PostHistory.USER_DISPLAY_NAME);
		String text = attributes.getValue(PostHistory.TEXT);
		String comment = attributes.getValue(PostHistory.COMMENT);
		
		//This time we collect all posts history event for the migrated posts
		PostHistory postHistory = new PostHistory();
		postHistory = new PostHistory();
		postHistory.setSite(site);
		postHistory.setId(id);
		postHistory.setPostHistoryTypeId(postHistoryTypeId);
		postHistory.setPostId(postId);
		postHistory.setRevisionGUID(revisionGUID);
		postHistory.setCreationDate(creationDate);
		postHistory.setUserId(userId);
		postHistory.setUserDisplayName(userDisplayName);
		postHistory.setText(text);
		postHistory.setComment(comment);
		
		//set the from and to part
		try {
			if(postHistory.getComment()!=null && (postHistory.getComment().startsWith("from https://")||postHistory.getComment().startsWith("from http://"))) {
				postHistory.setOrigin(getMigrateSite(postHistory.getComment()));
				postHistory.setOriginId(getMigrateId(postHistory.getComment()));
			}
			else if(postHistory.getComment()!=null && (postHistory.getComment().startsWith("to https://")||postHistory.getComment().startsWith("to http://"))) {
				postHistory.setDestination(getMigrateSite(postHistory.getComment()));
				postHistory.setDestinationId(getMigrateId(postHistory.getComment()));	
			}
		}
		catch(Exception e) {
			//https://rus.stackexchange.com/q/437026
			//e.printStackTrace();
			System.out.println("Deep Error occur:"+postHistory.getComment());
		}
		return postHistory;
	}
	
	public static LightPostHistory getLightPostHistory(String uri, String localName, String qName, Attributes attributes) {
		int id = Integer.parseInt(attributes.getValue(PostHistory.ID));
		int postHistoryTypeId = Integer.parseInt(attributes.getValue(PostHistory.POST_HISTORY_TYPE_ID));
		int postId = Integer.parseInt(attributes.getValue(PostHistory.POST_ID));
		String creationDate = attributes.getValue(PostHistory.CREATION_DATE);
		int userId = attributes.getValue(PostHistory.USER_ID)!=null? Integer.parseInt(attributes.getValue(PostHistory.USER_ID)):-999;
		
		//This time we collect all posts history event for the migrated posts
		LightPostHistory lightPostHistory = new LightPostHistory();
		lightPostHistory.setId(id);
		lightPostHistory.setPostHistoryTypeId(postHistoryTypeId);
		lightPostHistory.setPostId(postId);
		lightPostHistory.setCreationDate(creationDate);
		lightPostHistory.setUserId(userId);

		return lightPostHistory;
	}
	
	public static User getUser(String uri, String localName, String qName, Attributes attributes) {
		String site = attributes.getValue(User.SITE);
		int id = Integer.parseInt(attributes.getValue(User.ID));
		int reputation = Integer.parseInt(attributes.getValue(User.REPUTATION));
		String creationDate = attributes.getValue(User.CREATION_DATE);
		String displayName = attributes.getValue(User.DISPLAY_NAME);
		String lastAccessDate = attributes.getValue(User.LAST_ACCESS_DATE);
		String websiteUrl = attributes.getValue(User.WEBSITE_URL);
		String location = attributes.getValue(User.LOCATION);
		String aboutMe = attributes.getValue(User.ABOUT_ME);
		int views = attributes.getValue(User.VIEWS) != null
				? Integer.parseInt(attributes.getValue(User.VIEWS))
				: -999;
		int upVotes = attributes.getValue(User.UP_VOTES) != null
				? Integer.parseInt(attributes.getValue(User.UP_VOTES))
				: 0;
		int downVotes = attributes.getValue(User.DOWN_VOTES) != null
				? Integer.parseInt(attributes.getValue(User.DOWN_VOTES))
				: 0;
		String profileImageUrl = attributes.getValue(User.PROFILE_IMAGE_URL);
		int accountId = attributes.getValue(User.ACCOUNT_ID) != null
				? Integer.parseInt(attributes.getValue(User.ACCOUNT_ID))
				: -999;

		User user = new User();
		user.setSite(site);
		user.setId(id);
		user.setReputation(reputation);
		user.setCreationDate(creationDate);
		user.setDisplayName(displayName);
		user.setLastAccessDate(lastAccessDate);
		user.setWebsiteUrl(websiteUrl);
		user.setLocation(location);
		user.setAboutMe(aboutMe);
		user.setViews(views);
		user.setUpVotes(upVotes);
		user.setDownVotes(downVotes);
		user.setProfileImageUrl(profileImageUrl);
		user.setAccountId(accountId);
		return user;
	}
	
	public static LightUser getLightUser(String uri, String localName, String qName, Attributes attributes) {
		int id = Integer.parseInt(attributes.getValue(User.ID));
		int reputation = Integer.parseInt(attributes.getValue(User.REPUTATION));
		String creationDate = attributes.getValue(User.CREATION_DATE);
		String userDisplayName = attributes.getValue(User.DISPLAY_NAME);
		
		int views = attributes.getValue(User.VIEWS) != null
				? Integer.parseInt(attributes.getValue(User.VIEWS))
				: -999;
		int upVotes = attributes.getValue(User.UP_VOTES) != null
				? Integer.parseInt(attributes.getValue(User.UP_VOTES))
				: 0;
		int downVotes = attributes.getValue(User.DOWN_VOTES) != null
				? Integer.parseInt(attributes.getValue(User.DOWN_VOTES))
				: 0;
		String profileImageUrl = attributes.getValue(User.PROFILE_IMAGE_URL);
		int accountId = attributes.getValue(User.ACCOUNT_ID) != null
				? Integer.parseInt(attributes.getValue(User.ACCOUNT_ID))
				: -999;

		LightUser user = new LightUser();
		user.setId(id);
		user.setReputation(reputation);
		user.setCreationDate(creationDate);
		user.setDisplayName(userDisplayName);
		user.setViews(views);
		user.setUpVotes(upVotes);
		user.setDownVotes(downVotes);
		user.setAccountId(accountId);
		return user;
	}
	
	public static Post getPost(String uri, String localName, String qName, Attributes attributes) {
		
		String site = attributes.getValue(Post.SITE);
		int id = Integer.parseInt(attributes.getValue(Post.ID));
		int postTypeId = Integer.parseInt(attributes.getValue(Post.POST_TYPE_ID));
		int acceptedAnswerId = attributes.getValue(Post.ACCEPTED_ANSWER_ID)!=null? Integer.parseInt(attributes.getValue(Post.ACCEPTED_ANSWER_ID)):-999;
		int parentId = attributes.getValue(Post.PARENT_ID)!=null? Integer.parseInt(attributes.getValue(Post.PARENT_ID)):-999;
		String creationDate = attributes.getValue(Post.CREATION_DATE);
		String deletionDate = attributes.getValue(Post.DELETION_DATE);
		int score = attributes.getValue(Post.SCORE)!=null? Integer.parseInt(attributes.getValue(Post.SCORE)):0;
		int viewCount = attributes.getValue(Post.VIEW_COUNT)!=null?Integer.parseInt(attributes.getValue(Post.VIEW_COUNT)):0;
		String body = attributes.getValue(Post.BODY);
		int ownerUserId = attributes.getValue(Post.OWNER_USER_ID)!=null? Integer.parseInt(attributes.getValue(Post.OWNER_USER_ID)):-999;
		String ownerDisplayName = attributes.getValue(Post.OWNER_DISPLAY_NAME);
		int lastEditorUserId = attributes.getValue(Post.LAST_EDITOR_USER_ID)!=null? Integer.parseInt(attributes.getValue(Post.LAST_EDITOR_USER_ID)):-999;
		String lastEditorDisplayName = attributes.getValue(Post.LAST_EDITOR_DISPLAY_NAME);
		String lastEditDate = attributes.getValue(Post.LAST_EDIT_DATE);
		String lastActivityDate = attributes.getValue(Post.LAST_ACTIVITY_DATE);
		String title = attributes.getValue(Post.TITLE);
		String tags = attributes.getValue(Post.TAGS);
		int answerCount = attributes.getValue(Post.ANSWER_COUNT)!=null?Integer.parseInt(attributes.getValue(Post.ANSWER_COUNT)):0;
		int commentCount = attributes.getValue(Post.COMMENT_COUNT)!=null?Integer.parseInt(attributes.getValue(Post.COMMENT_COUNT)):0;
		int favoriteCount = attributes.getValue(Post.FAVORITE_COUNT)!=null?Integer.parseInt(attributes.getValue(Post.FAVORITE_COUNT)):0;
		String closedDate = attributes.getValue(Post.CLOSED_DATE);
		String communityOwnedDate = attributes.getValue(Post.COMMUNITY_OWNED_DATE);

		Post post = new Post();
		post.setSite(site);
		post.setId(id);
		post.setPostTypeId(postTypeId);
		post.setAcceptedAnswerId(acceptedAnswerId);
		post.setParentId(parentId);
		post.setCreationDate(creationDate);
		post.setDeletionDate(deletionDate);
		post.setScore(score);
		post.setViewCount(viewCount);
		post.setBody(body);
		post.setOwnerUserId(ownerUserId);
		post.setOwnerDisplayName(ownerDisplayName);
		post.setLastEditorUserId(lastEditorUserId);
		post.setLastEditorDisplayName(lastEditorDisplayName);
		post.setLastEditDate(lastEditDate);
		post.setLastActivityDate(lastActivityDate);
		post.setTitle(title);
		post.setTags(tags);
		post.setAnswerCount(answerCount);
		post.setCommentCount(commentCount);
		post.setFavoriteCount(favoriteCount);
		post.setClosedDate(closedDate);
		post.setCommunityOwnedDate(communityOwnedDate);
		
		return post;
	}
	
	public static LightPost getLightPost(String uri, String localName, String qName, Attributes attributes) {
		
		int id = Integer.parseInt(attributes.getValue(Post.ID));
		int postTypeId = Integer.parseInt(attributes.getValue(Post.POST_TYPE_ID));
		int acceptedAnswerId = attributes.getValue(Post.ACCEPTED_ANSWER_ID)!=null? Integer.parseInt(attributes.getValue(Post.ACCEPTED_ANSWER_ID)):-999;
		int parentId = attributes.getValue(Post.PARENT_ID)!=null? Integer.parseInt(attributes.getValue(Post.PARENT_ID)):-999;
		String creationDate = attributes.getValue(Post.CREATION_DATE);
		int score = attributes.getValue(Post.SCORE)!=null? Integer.parseInt(attributes.getValue(Post.SCORE)):0;
		int viewCount = attributes.getValue(Post.VIEW_COUNT)!=null?Integer.parseInt(attributes.getValue(Post.VIEW_COUNT)):0;
		int ownerUserId = attributes.getValue(Post.OWNER_USER_ID)!=null? Integer.parseInt(attributes.getValue(Post.OWNER_USER_ID)):-999;
		String tags = attributes.getValue(Post.TAGS);
		int answerCount = attributes.getValue(Post.ANSWER_COUNT)!=null?Integer.parseInt(attributes.getValue(Post.ANSWER_COUNT)):0;
		int commentCount = attributes.getValue(Post.COMMENT_COUNT)!=null?Integer.parseInt(attributes.getValue(Post.COMMENT_COUNT)):0;
		int favoriteCount = attributes.getValue(Post.FAVORITE_COUNT)!=null?Integer.parseInt(attributes.getValue(Post.FAVORITE_COUNT)):0;
		String closedDate = attributes.getValue(Post.CLOSED_DATE);
		
		LightPost lightPost = new LightPost();
		lightPost.setId(id);
		lightPost.setPostTypeId(postTypeId);
		lightPost.setAcceptedAnswerId(acceptedAnswerId);
		lightPost.setParentId(parentId);
		lightPost.setCreationDate(creationDate);
		lightPost.setScore(score);
		lightPost.setViewCount(viewCount);
		lightPost.setOwnerUserId(ownerUserId);
		lightPost.setTags(tags);
		lightPost.setAnswerCount(answerCount);
		lightPost.setCommentCount(commentCount);
		lightPost.setFavoriteCount(favoriteCount);
		lightPost.setClosedDate(closedDate);
		
		return lightPost;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getMigrateId("from http://rus.stackexchange.com/questions/437026/"));
	}

}
