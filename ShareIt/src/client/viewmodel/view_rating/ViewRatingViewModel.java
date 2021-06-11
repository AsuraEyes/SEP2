package client.viewmodel.view_rating;

import client.model.member.MemberModel;
import client.model.message.MessageModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Member;
import shared.transferobjects.Rating;

import java.util.ArrayList;
/**
 * A class that holds and manages data from the ViewRating view.
 */
public class ViewRatingViewModel
{
  private MemberModel memberModel;
  private MessageModel messageModel;

  private StringProperty username;
  private StringProperty location;
  private StringProperty rating;
  private StringProperty address;
  private StringProperty contact;
  private StringProperty otherInformation;
  /**
   * Instantiates StringProperties used for binding with the fields in the controller
   *
   * @param memberModel The model that this ViewModel uses
   * @param messageModel The model that this ViewModel uses
   */
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
    /**
     * Get member username that rating is viewed and sets the data in the
     * controller.
     *
     * @return returns all Member's data
     */
  public String getMemberUsername()
  {
    Member member = memberModel
        .getMemberByUsername(memberModel.getMemberUsername());
    username.setValue("Username: " + memberModel.getMemberUsername());
    location.setValue("Location: " + member.getAddressCity());
    rating.setValue("Rating: " + (member.getAverageReview()));
    address.setValue(
        "Address: " + member.getAddressStreet() + ", " + member.getAddressNo());
    if (member.getPhoneNo().isEmpty()){
      contact.setValue(
              "Contact: " + member.getEmailAddress());
    }
    else{
      contact.setValue(
              "Contact: " + member.getPhoneNo() + "\n" + member.getEmailAddress());
    }
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
