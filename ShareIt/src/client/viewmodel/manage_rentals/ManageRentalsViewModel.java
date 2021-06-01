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
     * Instantiates a new ManageRentalsViewModel.
     *
     * @param shareItModel The model that this ViewModel uses
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
     *
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
    /**
     * Checks rental data before deleting.
     *
     * @return  deletes if process was successful
     */
  public boolean deleteRental()
  {
    return rentalModel.deleteRental(rentalModel.getSelectedRental());
  }
    /**
     * Gets nameOfRental.
     *
     * @return returns nameOfRental
     */
  public StringProperty nameOfRentalProperty()
  {
    return nameOfRental;
  }
    /**
     * Gets descriptionOfRental.
     *
     * @return returns descriptionOfRental
     */
  public StringProperty descriptionOfRentalProperty()
  {
    return descriptionOfRental;
  }
    /**
     * Gets stateOfRental.
     *
     * @return returns stateOfRental
     */
  public StringProperty stateOfRentalProperty()
  {
    return stateOfRental;
  }
    /**
     * Gets priceOfRental.
     *
     * @return returns nameOfRental
     */
  public StringProperty priceOfRentalProperty()
  {
    return priceOfRental;
  }
    /**
     * Gets otherInformationOfRental.
     *
     * @return returns otherInformationOfRental
     */
  public StringProperty otherInformationOfRentalProperty()
  {
    return otherInformationOfRental;
  }
    /**
     * Category of rental property string property.
     *
     * @return the string property
     */
  public StringProperty categoryOfRentalProperty()
  {
    return categoryOfRental;
  }
    /**
     * Gets selected rental.
     */
  public void getSelectedRental()
  {
    rentalModel.sendSelectedRental(rentalModel.getSelectedRental());
  }
    /**
     * Image property property object property.
     *
     * @return the object property
     */
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
