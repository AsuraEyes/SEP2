package ground_classes;

public class Rating {
    private double rating;
    private String commentary;

    public Rating(double rating, String commentary) {
        this.rating = rating;
        this.commentary = commentary;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
}
