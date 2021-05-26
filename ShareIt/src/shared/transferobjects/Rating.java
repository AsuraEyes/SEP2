package shared.transferobjects;

import java.io.Serializable;

public class Rating implements Serializable {
    private double rating;
    private String commentary;
    private int memberFrom;
    private int memberTo;

    public Rating(double rating, String commentary, int memberFrom, int memberTo) {
        this.rating = rating;
        this.commentary = commentary;
        this.memberFrom = memberFrom;
        this.memberTo = memberTo;
    }

    public double getRating()
    {
        return rating;
    }

    public void setRating(double rating)
    {
        this.rating = rating;
    }

    public String getCommentary() {
        return commentary;
    }

    public int getMemberFrom() {
        return memberFrom;
    }

    public int getMemberTo() {
        return memberTo;
    }
}
