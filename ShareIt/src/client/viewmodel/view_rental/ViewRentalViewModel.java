package client.viewmodel.view_rental;

import client.model.member.MemberModel;
import client.model.rental.RentalModel;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import shared.transferobjects.Member;
import shared.transferobjects.Rental;

import java.beans.PropertyChangeEvent;

/**
 * A class that holds and manages data from the ViewRental view.
 */
public class ViewRentalViewModel
{
  private RentalModel rentalModel;
  private MemberModel memberModel;

  private StringProperty nameOfRental;
  private StringProperty descriptionOfRental;
  private StringProperty stateOfRental;
  private StringProperty priceOfRental;
  private StringProperty otherInformationOfRental;
  private StringProperty categoryOfRental;
  private StringProperty usernameOfRental;
  private StringProperty locationOfRental;
  private StringProperty ratingOfUserOfRental;
  private ObjectProperty<Image> imageProperty;
  /**
   * Instantiates a new ViewRentalViewModel.
   *
   * @param shareItModel The model that this ViewModel uses
   */
  public ViewRentalViewModel(RentalModel rentalModel, MemberModel memberModel)
  {
    this.rentalModel = rentalModel;
    this.memberModel = memberModel;

    imageProperty = new SimpleObjectProperty<>();
    nameOfRental = new SimpleStringProperty();
    descriptionOfRental = new SimpleStringProperty();
    stateOfRental = new SimpleStringProperty();
    priceOfRental = new SimpleStringProperty();
    otherInformationOfRental = new SimpleStringProperty();
    categoryOfRental = new SimpleStringProperty();
    usernameOfRental = new SimpleStringProperty();
    locationOfRental = new SimpleStringProperty();
    ratingOfUserOfRental = new SimpleStringProperty();

    rentalModel.addListener("selectedRental", this::selectedRental);
  }

  /**
   * (?)
   * @param evt
   */
  private void selectedRental(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {

      if (evt.getNewValue() instanceof Rental)
      {
        Rental rental = (Rental) evt.getNewValue();
        Member member = memberModel.getMemberById(rental.getMemberId());

        nameOfRental.setValue("Name: " + rental.getName());
        descriptionOfRental.setValue("Description: " + rental.getDescription());
        stateOfRental.setValue("State: " + rental.getStateName());
        priceOfRental.setValue("Price: " + (rental.getPrice()) + " DKK/day");
        imageProperty.setValue(new Image(rental.getPictureLink()));
        if (rental.getOtherInformation() != null)
        {
          otherInformationOfRental
              .setValue("Other Information: " + rental.getOtherInformation());
        }
        if (rental.getSelectedCategories() != null)
        {
          categoryOfRental.setValue(
              "Categories: " + rental.getSelectedCategories().toString());
        }
        usernameOfRental.setValue("Username: " + member.getUsername());
        locationOfRental.setValue("Location: " + member.getAddressCity());
        ratingOfUserOfRental.setValue("Rating: " + (member.getAverageReview()));
      }
    });
  }

  /**
   * Name of rental.
   *
   * @return returns nameOfRental
   */
  public StringProperty nameOfRentalProperty()
  {
    return nameOfRental;
  }

  /**
   * Description of rental.
   *
   * @return returns descriptionOfRental
   */
  public StringProperty descriptionOfRentalProperty()
  {
    return descriptionOfRental;
  }

  /**
   * State of rental.
   *
   * @return returns stateOfRental
   */
  public StringProperty stateOfRentalProperty()
  {
    return stateOfRental;
  }
  /**
   * Price of rental.
   *
   * @return returns priceOfRental
   */
  public StringProperty priceOfRentalProperty()
  {
    return priceOfRental;
  }

  /**
   * Other information of rental.
   *
   * @return returns otherInformationOfRental
   */
  public StringProperty otherInformationOfRentalProperty()
  {
    return otherInformationOfRental;
  }

  /**
   * Category of rental.
   *
   * @return returns categoryOfRental
   */
  public StringProperty categoryOfRentalProperty()
  {
    return categoryOfRental;
  }

  /**
   * Username member that posted rental.
   *
   * @return returns usernameOfRental
   */
  public StringProperty usernameOfRentalProperty()
  {
    return usernameOfRental;
  }

  /**
   * Location of member that posted rental
   *
   * @return returns locationOfRental
   */
  public StringProperty locationOfRentalProperty()
  {
    return locationOfRental;
  }

  /**
   * Rating of user that posted rental.
   *
   * @return returns ratingOfUserOfRental
   */
  public StringProperty ratingOfUserOfRentalProperty()
  {
    return ratingOfUserOfRental;
  }

  /**
   * Image of posted rental.
   *
   * @return returns image
   */
  public ObjectProperty<Image> imagePropertyProperty()
  {
    return imageProperty;
  }

  public void setMemberUsername()
  {
    memberModel.setMemberUsername(usernameOfRental.getValue().substring(10));
  }

  public void setMemberRentals()
  {
    rentalModel.setAllMemberRentals(usernameOfRental.getValue().substring(10));
  }
  /**
   * Checks user type.
   *
   * @return returns which type of account is viewing profile
   */
  public String getUserType()
  {
    return memberModel.checkUserType();
  }

  public String getLoggedInUsername()
  {
    return memberModel.getLoggedInUsername();
  }
}
