package client.viewmodel.view_member_profile;

import client.model.member.MemberModel;
import client.model.rental.RentalModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.InfoOverlay;
import shared.transferobjects.Member;
import shared.transferobjects.Rental;

import java.util.ArrayList;

/**
 * A class that holds and manages data from the SendWarning view.
 */
public class ViewMemberProfileViewModel
{
  private final SimpleStringProperty searchField;
  private final SimpleStringProperty usernameLabel;
  private final SimpleStringProperty locationLabel;
  private final SimpleStringProperty ratingLabel;
  private final SimpleStringProperty addressLabel;
  private final SimpleStringProperty contactLabel;
  private final SimpleStringProperty otherInformationLabel;
  private final RentalModel rentalModel;
  private final MemberModel memberModel;
  /**
   * Instantiates a new ViewMemberProfileViewModel.
   *
   * @param model The model that this ViewModel uses
   */
  public ViewMemberProfileViewModel(RentalModel rentalModel,
      MemberModel memberModel)
  {
    this.rentalModel = rentalModel;
    this.memberModel = memberModel;

    searchField = new SimpleStringProperty();
    usernameLabel = new SimpleStringProperty();
    locationLabel = new SimpleStringProperty();
    ratingLabel = new SimpleStringProperty();
    addressLabel = new SimpleStringProperty();
    contactLabel = new SimpleStringProperty();
    otherInformationLabel = new SimpleStringProperty();
  }

  /**
   * Gets searchField.
   *
   * @return returns searchField input
   */
  public StringProperty getSearchField()
  {
    return searchField;
  }

  /**
   * Gets usernameLabel.
   *
   * @return returns usernameLabel
   */
  public StringProperty getUsernameLabel()
  {
    return usernameLabel;
  }

  /**
   * Gets locationLabel.
   *
   * @return returns locationLabel
   */
  public StringProperty getLocationLabel()
  {
    return locationLabel;
  }

  /**
   * Gets ratingLabel.
   *
   * @return returns ratingLabel
   */
  public StringProperty getRatingLabel()
  {
    return ratingLabel;
  }

  /**
   * Gets addressLabel.
   *
   * @return returns addressLabel
   */
  public StringProperty getAddressLabel()
  {
    return addressLabel;
  }

  /**
   * Gets contactLabel.
   *
   * @return returns contactLabel
   */
  public StringProperty getContactLabel()
  {
    return contactLabel;
  }

  /**
   * Gets otherInformationLabel.
   *
   * @return returns otherInformationLabel
   */
  public StringProperty getOtherInformationLabel()
  {
    return otherInformationLabel;
  }
  /**
   * Checks user type.
   *
   * @return returns which type of account is viewing profile
   */
  public String checkUserType()
  {
    return memberModel.checkUserType();
  }
  /**
   * Gets a list of member's rentals
   *
   * @param username username that will be checked for the list
   * @return returns a list of viewed member's rentals
   */
  public ArrayList<Rental> getRentalsOfMemberList()
  {
    return rentalModel.getRentalsOfMemberList();
  }
  /**
   * Gets rental.
   *
   * @param object the object
   */
  public void getRental(Object object)
  {
    if (object instanceof StackPane)
    {
      StackPane stackPane = (StackPane) object;
      if (stackPane.getChildren().get(0) instanceof InfoOverlay)
      {
        InfoOverlay infoOverlay = (InfoOverlay) stackPane.getChildren().get(0);
        if (infoOverlay.getContent() instanceof ImageView)
        {
          ImageView imageView = (ImageView) infoOverlay.getContent();
          for (int i = 0; i < getRentalsOfMemberList().size(); i++)
          {
            if (imageView.getId().equals(
                String.valueOf(getRentalsOfMemberList().get(i).getId())))
            {
              rentalModel.sendSelectedRental(getRentalsOfMemberList().get(i));
            }
          }
        }
      }
    }
  }
  /**
   * Loads member's information that profile is viewed.
   *
   * @return returns all Member's data
   */
  public void loadMemberInformation()
  {
    Member member = memberModel
        .getMemberByUsername(memberModel.getMemberUsername());
    usernameLabel.setValue("Username: " + memberModel.getMemberUsername());
    locationLabel.setValue("Location: " + member.getAddressCity());
    ratingLabel.setValue("Rating: " + (member.getAverageReview()));
    addressLabel.setValue(
        "Address: " + member.getAddressStreet() + ", " + member.getAddressNo());
    contactLabel.setValue(
        "Contact: " + member.getPhoneNo() + "\n" + member.getEmailAddress());
    otherInformationLabel
        .setValue("Other Information: " + member.getOtherInformation());
  }
  /**
   * Sets member username.
   */
  public void setMemberUsername()
  {
    memberModel.setMemberUsername(usernameLabel.getValue().substring(10));
  }
  /**
   * Checks members data before deleting.
   *
   * @return  deletes if process was successful
   */
  public boolean deleteAccount()
  {
    Member member = memberModel
        .getMemberByUsername(memberModel.getMemberUsername());
    return memberModel.deleteMember(member);
  }
}
