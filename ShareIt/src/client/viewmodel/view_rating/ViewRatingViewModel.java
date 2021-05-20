package client.viewmodel.view_rating;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import shared.transferobjects.Member;
import shared.transferobjects.Rating;

import java.util.ArrayList;

public class ViewRatingViewModel {
    private ShareItModel model;
    private StringProperty username;
    private StringProperty location;
    private StringProperty rating;
    private StringProperty address;
    private StringProperty contact;
    private StringProperty otherInformation;

    public ViewRatingViewModel(ShareItModel model){
        this.model = model;
        username = new SimpleStringProperty();
        location = new SimpleStringProperty();
        rating = new SimpleStringProperty();
        address = new SimpleStringProperty();
        contact = new SimpleStringProperty();
        otherInformation = new SimpleStringProperty();
    }


    public StringProperty getUsername() {
        return username;
    }

    public StringProperty getLocation() {
        return location;
    }

    public StringProperty getRating() {
        return rating;
    }

    public StringProperty getAddress() {
        return address;
    }

    public StringProperty getContact() {
        return contact;
    }

    public StringProperty getOtherInformation() {
        return otherInformation;
    }

    public String getMemberUsername(){
        username.setValue(model.getMemberUsername());
        System.out.println("Get member username view rating VM : "+model.getMemberUsername());
        Member member = model.getMemberByUsername(model.getMemberUsername());
        location.setValue(member.getAddressCity());
        rating.setValue(String.valueOf(member.getAverageReview()));
        address.setValue(member.getAddressStreet() + ", " + member.getAddressNo());
        contact.setValue(member.getPhoneNo() + "\n" + member.getEmailAddress());
        otherInformation.setValue(member.getOtherInformation());
        return model.getMemberUsername();
    }

    public ArrayList<Rating> getAllRatingsOnMember(String memberUsername) {
        return model.getAllRatingsOnMember(memberUsername);
    }
}
