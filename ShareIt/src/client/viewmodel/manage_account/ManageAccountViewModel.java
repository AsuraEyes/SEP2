package client.viewmodel.manage_account;

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
 * A class that holds and manages data from the ManageAccount view.
 */
public class ManageAccountViewModel
{
  private SimpleStringProperty usernameLabel;
  private SimpleStringProperty locationLabel;
  private SimpleStringProperty ratingLabel;
  private SimpleStringProperty addressLabel;
  private SimpleStringProperty contactLabel;
  private SimpleStringProperty otherInformationLabel;
  private RentalModel rentalModel;
  private MemberModel memberModel;
    /**
     * Instantiates a new Manage account view model.
     *
     * @param shareItModel The model that this ViewModel uses
     */
  public ManageAccountViewModel(RentalModel rentalModel,
      MemberModel memberModel)
  {
    this.rentalModel = rentalModel;
    this.memberModel = memberModel;

    usernameLabel = new SimpleStringProperty();
    locationLabel = new SimpleStringProperty();
    ratingLabel = new SimpleStringProperty();
    addressLabel = new SimpleStringProperty();
    contactLabel = new SimpleStringProperty();
    otherInformationLabel = new SimpleStringProperty();
  }
    /**
     * Sets profile.
     */
  public void setProfile()
  {
    Member member = memberModel
        .getMemberByUsername(memberModel.getLoggedInUsername());
    usernameLabel.setValue("Username: " + member.getUsername());
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
     * Gets rentals of member list.
     *
     * @return returns Member's rentals
     * @throws RemoteException
     */
  public ArrayList<Rental> getRentalsOfMemberList()
  {
    return rentalModel.getRentalsOfMemberList();
  }
    /**
     * Gets selected rental.
     *
     * @param object the object
     * @throws RemoteException
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
              rentalModel.setSelectedRental(getRentalsOfMemberList().get(i));
            }
          }
        }
      }
    }
  }
    /**
     * Gets usernameLabel.
     *
     * @return returns usernameLabel input
     */
  public StringProperty getUsernameLabel()
  {
    return usernameLabel;
  }
    /**
     * Gets locationLabel.
     *
     * @return returns locationLabel input
     */
  public StringProperty getLocationLabel()
  {
    return locationLabel;
  }
    /**
     * Gets ratingLabel.
     *
     * @return returns ratingLabel input
     */
  public StringProperty getRatingLabel()
  {
    return ratingLabel;
  }
    /**
     * Gets addressLabel.
     *
     * @return returns addressLabel input
     */
  public StringProperty getAddressLabel()
  {
    return addressLabel;
  }
    /**
     * Gets contactLabel.
     *
     * @return returns contactLabel input
     */
  public StringProperty getContactLabel()
  {
    return contactLabel;
  }
    /**
     * Gets otherInformationLabel.
     *
     * @return returns otherInformationLabel input
     */
  public StringProperty getOtherInformationLabel()
  {
    return otherInformationLabel;
  }
    /**
     * Sets member.
     */
  public void setMember()
  {
    memberModel.setMemberUsername(usernameLabel.getValue().substring(10));
  }
}
