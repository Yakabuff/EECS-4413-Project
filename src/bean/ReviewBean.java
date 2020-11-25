package bean;

public class ReviewBean {
	private int rating;
	private String name;
	
	public ReviewBean(int rating, String name) {
		this.rating=rating;
		this.name=name;
	}
	
	public ReviewBean() {
		
	}
	
	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
