

import java.util.Date;

public class ProfessionalReviews {
	protected int ProfessionalReviewId;
	protected Date Created;
	protected String Content;
	protected Taster Taster;
	protected Wine Wine;
	
	/**
	 * @param professionalReviewId
	 * @param created
	 * @param content
	 * @param tasterName
	 * @param wineTitle
	 */
	public ProfessionalReviews(int professionalReviewId, Date created, String content, Taster taster,
			Wine wine) {
		this.ProfessionalReviewId = professionalReviewId;
		this.Created = created;
		this.Content = content;
		this.Taster = taster;
		this.Wine = wine;
	}
	
	public ProfessionalReviews(Date created, String content, Taster taster,
			Wine wine) {
		this.Created = created;
		this.Content = content;
		this.Taster = taster;
		this.Wine = wine;
	}
	
	public ProfessionalReviews(int professionalReviewId) {
		this.ProfessionalReviewId = professionalReviewId;
	}
	
	/**
	 * @return the professionalReviewId
	 */
	public int getProfessionalReviewId() {
		return this.ProfessionalReviewId;
	}

	/**
	 * @param professionalReviewId the professionalReviewId to set
	 */
	public void setProfessionalReviewId(int professionalReviewId) {
		this.ProfessionalReviewId = professionalReviewId;
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
	 * @return the taster
	 */
	public Taster getTaster() {
		return this.Taster;
	}

	/**
	 * @param taster the taster to set
	 */
	public void setTaster(Taster taster) {
		this.Taster = taster;
	}

	/**
	 * @return the wine
	 */
	public Wine getWine() {
		return this.Wine;
	}

	/**
	 * @param wine the wine to set
	 */
	public void setWine(Wine wine) {
		this.Wine = wine;
	}
}
