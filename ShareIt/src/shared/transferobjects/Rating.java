package shared.transferobjects;

import java.io.Serializable;

public class Rating implements Serializable {
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
