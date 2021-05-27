package client.viewmodel.view_rating;


import client.model.member.MemberModel;
import client.model.message.MessageModel;
import client.model.rental.RentalModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Member;
import shared.transferobjects.Rating;

import java.util.ArrayList;

public class ViewRatingViewModel {
    private RentalModel rentalModel;
    private MemberModel memberModel;
    private MessageModel messageModel;


    private StringProperty username;
    private StringProperty location;
    private StringProperty rating;
    private StringProperty address;
    private StringProperty contact;
    private StringProperty otherInformation;

    public ViewRatingViewModel(RentalModel rentalModel, MemberModel memberModel, MessageModel messageModel){
        this.rentalModel = rentalModel;
        this.memberModel = memberModel;
        this.messageModel = messageModel;

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
        username.setValue(memberModel.getMemberUsername());
        Member member = memberModel.getMemberByUsername(memberModel.getMemberUsername());
        location.setValue(member.getAddressCity());
        rating.setValue(String.valueOf(member.getAverageReview()));
        address.setValue(member.getAddressStreet() + ", " + member.getAddressNo());
        contact.setValue(member.getPhoneNo() + "\n" + member.getEmailAddress());
        otherInformation.setValue(member.getOtherInformation());
        return memberModel.getMemberUsername();
    }

    public ArrayList<Rating> getAllRatingsOnMember(String memberUsername) {
        return messageModel.getAllRatingsOnMember(memberUsername);
    }

    public String getUserType(){
        return memberModel.getLoggedInUsername();
    }
}
