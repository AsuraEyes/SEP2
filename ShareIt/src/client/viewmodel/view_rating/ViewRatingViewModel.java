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
     * Instantiates a new ViewRatingViewModel.
     *
     * @param model The model that this ViewModel uses
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
    /**
     * Gets username.
     *
     * @return returns username
     */
  public StringProperty getUsername()
  {
    return username;
  }
    /**
     * Gets location.
     *
     * @return returns location
     */
  public StringProperty getLocation()
  {
    return location;
  }
    /**
     * Gets rating.
     *
     * @return returns rating
     */
  public StringProperty getRating()
  {
    return rating;
  }
    /**
     * Gets address.
     *
     * @return returns address
     */
  public StringProperty getAddress()
  {
    return address;
  }
    /**
     * Gets contact.
     *
     * @return returns contact
     */
  public StringProperty getContact()
  {
    return contact;
  }
    /**
     * Gets otherInformation.
     *
     * @return returns otherInformation
     */
  public StringProperty getOtherInformation()
  {
    return otherInformation;
  }
    /**
     * Get member username that rating is viewed.
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
    contact.setValue(
        "Contact: " + member.getPhoneNo() + "\n" + member.getEmailAddress());
    otherInformation
        .setValue("Other Information: " + member.getOtherInformation());
    return memberModel.getMemberUsername();
  }
    /**
     * Gets all ratings on member.
     *
     * @param memberUsername Member's username
     * @return returns all ratings that are on member
     */
  public ArrayList<Rating> getAllRatingsOnMember(String memberUsername)
  {
    return messageModel.getAllRatingsOnMember(memberUsername);
  }
    /**
     * Checks user type.
     *
     * @return returns which type of account is viewing profile
     */
  public String getUserType()
  {
    return memberModel.getLoggedInUsername();
  }
}
