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
   * Instantiates StringProperties used for binding with the fields in the controller
   *
   * @param rentalModel The model that this ViewModel uses
   * @param memberModel The model that this ViewModel uses
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
   * The method listens for events with selectedRental propertyName and
   * rental related data together with member's data, is sent to the controller
   * through binding.
   * @param evt value of selected rental
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
              "Categories: " + rental.toArrayString());
        }
        usernameOfRental.setValue("Username: " + member.getUsername());
        locationOfRental.setValue("Location: " + member.getAddressCity());
        ratingOfUserOfRental.setValue("Rating: " + (member.getAverageReview()));
      }
    });
  }

  public StringProperty nameOfRentalProperty()
  {
    return nameOfRental;
  }

  public StringProperty descriptionOfRentalProperty()
  {
    return descriptionOfRental;
  }

  public StringProperty stateOfRentalProperty()
  {
    return stateOfRental;
  }

  public StringProperty priceOfRentalProperty()
  {
    return priceOfRental;
  }

  public StringProperty otherInformationOfRentalProperty()
  {
    return otherInformationOfRental;
  }

  public StringProperty categoryOfRentalProperty()
  {
    return categoryOfRental;
  }

  public StringProperty usernameOfRentalProperty()
  {
    return usernameOfRental;
  }

  public StringProperty locationOfRentalProperty()
  {
    return locationOfRental;
  }

  public StringProperty ratingOfUserOfRentalProperty()
  {
    return ratingOfUserOfRental;
  }

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

  public String getUserType()
  {
    return memberModel.checkUserType();
  }

  public String getLoggedInUsername()
  {
    return memberModel.getLoggedInUsername();
  }
}
