package client.viewmodel.view_rating;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Member;
import shared.transferobjects.Rating;

import java.util.ArrayList;

/**
 * A class that holds and manages data from the ViewRating view.
 */
public class ViewRatingViewModel {
    private ShareItModel model;
    private StringProperty username;
    private StringProperty location;
    private StringProperty rating;
    private StringProperty address;
    private StringProperty contact;
    private StringProperty otherInformation;

    /**
     * Instantiates a new ViewRatingViewModel.
     *
     * @param model The model that this ViewModel uses
     */
    public ViewRatingViewModel(ShareItModel model){
        this.model = model;
        username = new SimpleStringProperty();
        location = new SimpleStringProperty();
        rating = new SimpleStringProperty();
        address = new SimpleStringProperty();
        contact = new SimpleStringProperty();
        otherInformation = new SimpleStringProperty();
    }

    /**
     * Gets username.
     *
     * @return returns username
     */
    public StringProperty getUsername() {
        return username;
    }

    /**
     * Gets location.
     *
     * @return returns location
     */
    public StringProperty getLocation() {
        return location;
    }

    /**
     * Gets rating.
     *
     * @return returns rating
     */
    public StringProperty getRating() {
        return rating;
    }

    /**
     * Gets address.
     *
     * @return returns address
     */
    public StringProperty getAddress() {
        return address;
    }

    /**
     * Gets contact.
     *
     * @return returns contact
     */
    public StringProperty getContact() {
        return contact;
    }

    /**
     * Gets otherInformation.
     *
     * @return returns otherInformation
     */
    public StringProperty getOtherInformation() {
        return otherInformation;
    }

    /**
     * Get member username that rating is viewed.
     *
     * @return returns all Member's data
     */
    public String getMemberUsername(){
        username.setValue(model.getMemberUsername());
        Member member = model.getMemberByUsername(model.getMemberUsername());
        location.setValue(member.getAddressCity());
        rating.setValue(String.valueOf(member.getAverageReview()));
        address.setValue(member.getAddressStreet() + ", " + member.getAddressNo());
        contact.setValue(member.getPhoneNo() + "\n" + member.getEmailAddress());
        otherInformation.setValue(member.getOtherInformation());
        return model.getMemberUsername();
    }

    /**
     * Gets all ratings on member.
     *
     * @param memberUsername Member's username
     * @return returns all ratings that are on member
     */
    public ArrayList<Rating> getAllRatingsOnMember(String memberUsername) {
        return model.getAllRatingsOnMember(memberUsername);
    }

    /**
     * Checks user type.
     *
     * @return returns which type of account is viewing profile
     */
    public String getUserType(){
        return model.getLoggedInUsername();
    }
}
