

import java.util.Date;

public class UserReviews {
	protected int UserReviewId;
	protected Date Created;
	protected String Content;
	protected User User;
	protected Wine Wine;
	
	
	/**
	 * @param userReviewId
	 * @param created
	 * @param content
	 * @param user
	 * @param wine
	 */
	public UserReviews(int userReviewId, Date created, String content, User user, Wine wine) {
		this.UserReviewId = userReviewId;
		this.Created = created;
		this.Content = content;
		this.User = user;
		this.Wine = wine;
	}
	
	public UserReviews(Date created, String content, User user, Wine wine) {
		this.Created = created;
		this.Content = content;
		this.User = user;
		this.Wine = wine;
	}
	
	public UserReviews(int userReviewId) {
		this.UserReviewId = userReviewId;
	}
	
	/**
	 * @return the userReviewId
	 */
	public int getUserReviewId() {
		return this.UserReviewId;
	}

	/**
	 * @param userReviewId the userReviewId to set
	 */
	public void setUserReviewId(int userReviewId) {
		this.UserReviewId = userReviewId;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return this.Created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.Created = created;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return this.Content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.Content = content;
	}

	/**
	 * @return the userName
	 */
	public String getUser() {
		return this.User;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUser(String user) {
		this.User = user;
	}

	/**
	 * @return the wineTitle
	 */
	public Wine getWine() {
		return this.Wine;
	}

	/**
	 * @param wineTitle the wineTitle to set
	 */
	public void setWine(Wine wine) {
		this.Wine = wine;
	}
}
