package client.viewmodel.manage_rentals;

import client.model.member.MemberModel;
import client.model.rental.RentalModel;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import shared.transferobjects.Rental;

import java.beans.PropertyChangeEvent;
/**
 * A class that holds and manages data from the ManageRentals view.
 */
public class ManageRentalsViewModel
{
  private RentalModel rentalModel;
  private MemberModel memberModel;

  private StringProperty nameOfRental;
  private StringProperty descriptionOfRental;
  private StringProperty stateOfRental;
  private StringProperty priceOfRental;
  private StringProperty otherInformationOfRental;
  private StringProperty categoryOfRental;
  private ObjectProperty<Image> imageProperty;
  /**
   * Instantiates StringProperties used for binding with the fields in the controller
   *
   * @param rentalModel The model that this ViewModel uses
   * @param memberModel The model that this ViewModel uses
   */
  public ManageRentalsViewModel(RentalModel rentalModel,
      MemberModel memberModel)
  {
    this.rentalModel = rentalModel;
    this.memberModel = memberModel;

    nameOfRental = new SimpleStringProperty();
    descriptionOfRental = new SimpleStringProperty();
    stateOfRental = new SimpleStringProperty();
    priceOfRental = new SimpleStringProperty();
    otherInformationOfRental = new SimpleStringProperty();
    categoryOfRental = new SimpleStringProperty();
    imageProperty = new SimpleObjectProperty<>();

    rentalModel.addListener("selectedRental", this::selectedRental);
  }
    /**
     * The method listens for events with selectedRental propertyName and
     * rental related data is sent to the controller through binding.
     * @param evt
     */
  private void selectedRental(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {

      if (evt.getNewValue() instanceof Rental)
      {
        Rental rental = (Rental) evt.getNewValue();

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
      }
    });
  }

  public boolean deleteRental()
  {
    return rentalModel.deleteRental(rentalModel.getSelectedRental());
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

  public void getSelectedRental()
  {
    rentalModel.sendSelectedRental(rentalModel.getSelectedRental());
  }

  public ObjectProperty<Image> imagePropertyProperty()
  {
    return imageProperty;
  }

  public void setMemberUsername()
  {
    memberModel.setMemberUsername(memberModel.getLoggedInUsername());
  }

  public void setAllMemberRentals()
  {
    rentalModel.setAllMemberRentals(memberModel.getLoggedInUsername());
  }
}
