package client.viewmodel.view_rating;

import client.model.member.MemberModel;
import client.model.message.MessageModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Member;
import shared.transferobjects.Rating;

import java.util.ArrayList;

public class ViewRatingViewModel
{
  private final MemberModel memberModel;
  private final MessageModel messageModel;

  private final StringProperty username;
  private final StringProperty location;
  private final StringProperty rating;
  private final StringProperty address;
  private final StringProperty contact;
  private final StringProperty otherInformation;

  public ViewRatingViewModel(MemberModel memberModel, MessageModel messageModel)
  {
    this.memberModel = memberModel;
    this.messageModel = messageModel;

    username = new SimpleStringProperty();
    location = new SimpleStringProperty();
    rating = new SimpleStringProperty();
    address = new SimpleStringProperty();
    contact = new SimpleStringProperty();
    otherInformation = new SimpleStringProperty();
  }

  public StringProperty getUsername()
  {
    return username;
  }

  public StringProperty getLocation()
  {
    return location;
  }

  public StringProperty getRating()
  {
    return rating;
  }

  public StringProperty getAddress()
  {
    return address;
  }

  public StringProperty getContact()
  {
    return contact;
  }

  public StringProperty getOtherInformation()
  {
    return otherInformation;
  }

  public String getMemberUsername()
  {
    Member member = memberModel
        .getMemberByUsername(memberModel.getMemberUsername());
    username.setValue("Username: " + memberModel.getMemberUsername());
    location.setValue("Location: " + member.getAddressCity());
    rating.setValue("Rating: " + (member.getAverageReview()));
    address.setValue(
        "Address: " + member.getAddressStreet() + ", " + member.getAddressNo());
    contact.setValue(
        "Contact: " + member.getPhoneNo() + "\n" + member.getEmailAddress());
    otherInformation
        .setValue("Other Information: " + member.getOtherInformation());
    return memberModel.getMemberUsername();
  }

  public ArrayList<Rating> getAllRatingsOnMember(String memberUsername)
  {
    return messageModel.getAllRatingsOnMember(memberUsername);
  }

  public String getUserType()
  {
    return memberModel.getLoggedInUsername();
  }
}
