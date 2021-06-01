package client.viewmodel.manage_rentals;

import client.model.ShareItModel;
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
public class ManageRentalsViewModel {
    private ShareItModel shareItModel;

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
    public ManageRentalsViewModel(ShareItModel shareItModel){
        this.shareItModel = shareItModel;
        nameOfRental = new SimpleStringProperty();
        descriptionOfRental = new SimpleStringProperty();
        stateOfRental = new SimpleStringProperty();
        priceOfRental = new SimpleStringProperty();
        otherInformationOfRental = new SimpleStringProperty();
        categoryOfRental = new SimpleStringProperty();
        imageProperty = new SimpleObjectProperty<>();

        shareItModel.addListener("selectedRental",this::selectedRental);
    }

    /**
     *
     * @param evt
     */
    private void selectedRental(PropertyChangeEvent evt) {
        Platform.runLater(() -> {

            if (evt.getNewValue() instanceof Rental) {

                Rental rental = (Rental) evt.getNewValue();
                shareItModel.setSelectedRental(rental);
                nameOfRental.setValue(rental.getName());
                descriptionOfRental.setValue(rental.getDescription());
                stateOfRental.setValue(rental.getStateName());
                priceOfRental.setValue(String.valueOf(rental.getPrice()));
                imageProperty.setValue(new Image(rental.getPictureLink()));
                if(rental.getOtherInformation() !=null)
                {
                    otherInformationOfRental.setValue(rental.getOtherInformation());
                }
                if(rental.getSelectedCategories() != null)
                {
                    categoryOfRental.setValue(rental.getSelectedCategories().toString());
                }
            }
        });
    }

    /**
     * Checks rental data before deleting.
     *
     * @return  deletes if process was successful
     */
    public boolean deleteRental() {
        return shareItModel.deleteRental(shareItModel.getSelectedRental());
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
    public void getSelectedRental(){
        shareItModel.sendSelectedRental(shareItModel.getSelectedRental());
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

}
