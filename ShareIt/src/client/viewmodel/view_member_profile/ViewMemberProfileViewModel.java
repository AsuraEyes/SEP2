package client.viewmodel.view_member_profile;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.InfoOverlay;
import shared.transferobjects.Member;
import shared.transferobjects.Rental;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * A class that holds and manages data from the SendWarning view.
 */
public class ViewMemberProfileViewModel
{
  private ShareItModel model;
  private final SimpleStringProperty searchField;
  private final SimpleStringProperty usernameLabel;
  private final SimpleStringProperty locationLabel;
  private final SimpleStringProperty ratingLabel;
  private final SimpleStringProperty addressLabel;
  private final SimpleStringProperty contactLabel;
  private final SimpleStringProperty otherInformationLabel;

  /**
   * Instantiates a new ViewMemberProfileViewModel.
   *
   * @param model The model that this ViewModel uses
   */
  public ViewMemberProfileViewModel(ShareItModel model)
  {
    this.model = model;
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
  public String checkUserType(){
    return model.checkUserType();
  }

  /**
   * Gets a list of member's rentals
   *
   * @param username username that will be checked for the list
   * @return returns a list of viewed member's rentals
   */
  public ArrayList<Rental> getRentalsOfMemberList(String username)  {
    return model.getRentalsOfMemberList(username);
  }

  /**
   * Gets rental.
   *
   * @param object the object
   * @throws RemoteException the remote exception
   */
  public void getRental(Object object) throws RemoteException
  {
    if(object instanceof StackPane){
      StackPane stackPane = (StackPane) object;
      if(stackPane.getChildren().get(0) instanceof InfoOverlay)
      {
        InfoOverlay infoOverlay = (InfoOverlay) stackPane.getChildren().get(0);
        if(infoOverlay.getContent() instanceof ImageView)
        {
          ImageView imageView = (ImageView) infoOverlay.getContent();
          for (int i = 0; i < getRentalsOfMemberList(usernameLabel.getValue()).size(); i++)
          {
            if(imageView.getId().equals(String.valueOf(getRentalsOfMemberList(usernameLabel.getValue()).get(i).getId())))
            {
              model.sendSelectedRental(getRentalsOfMemberList(usernameLabel.getValue()).get(i));
              break;
            }
          }
        }
      }
    }
  }

  /**
   * Get member username that profile is viewed.
   *
   * @return returns all Member's data
   */
  public String getMemberUsername(){
    usernameLabel.setValue(model.getMemberUsername());
    Member member = model.getMemberByUsername(model.getMemberUsername());
    locationLabel.setValue(member.getAddressCity());
    ratingLabel.setValue(String.valueOf(member.getAverageReview()));
    addressLabel.setValue(member.getAddressStreet() + ", " + member.getAddressNo());
    contactLabel.setValue(member.getPhoneNo() + "\n" + member.getEmailAddress());
    otherInformationLabel.setValue(member.getOtherInformation());
    return model.getMemberUsername();
  }

  /**
   * Sets member username.
   */
  public void setMemberUsername() {
    model.setMemberUsername(usernameLabel.getValue());
  }

  /**
   * Checks members data before deleting.
   *
   * @return  deletes if process was successful
   */
  public boolean deleteAccount(){
    Member member = model.getMemberByUsername(model.getMemberUsername());
    return model.deleteMember(member);
  }
}
