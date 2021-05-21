package client.viewmodel.manage_rentals;

import client.model.ShareItModel;
import client.model.state.StateManager;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import shared.transferobjects.Member;
import shared.transferobjects.Rental;

import java.beans.PropertyChangeEvent;

public class ManageRentalsViewModel {
    private ShareItModel shareItModel;

    private StringProperty nameOfRental;
    private StringProperty descriptionOfRental;
    private StringProperty stateOfRental;
    private StringProperty priceOfRental;
    private StringProperty otherInformationOfRental;
    private StringProperty categoryOfRental;

    public ManageRentalsViewModel(ShareItModel shareItModel){
        this.shareItModel = shareItModel;
        nameOfRental = new SimpleStringProperty();
        descriptionOfRental = new SimpleStringProperty();
        stateOfRental = new SimpleStringProperty();
        priceOfRental = new SimpleStringProperty();
        otherInformationOfRental = new SimpleStringProperty();
        categoryOfRental = new SimpleStringProperty();

        shareItModel.addListener("selectedRental",this::selectedRental);
    }

    private void selectedRental(PropertyChangeEvent evt)
    {
        Platform.runLater(() -> {

            if (evt.getNewValue() instanceof Rental)
            {

                Rental rental = (Rental) evt.getNewValue();
                shareItModel.setSelectedRental(rental);
                nameOfRental.setValue(rental.getName());
                descriptionOfRental.setValue(rental.getDescription());
                stateOfRental.setValue(rental.getStateName());
                priceOfRental.setValue(String.valueOf(rental.getPrice()));
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

    public boolean deleteRental() {
        return shareItModel.deleteRental(shareItModel.getSelectedRental());
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
    public void getSelectedRental(){
        //Rental rental = new Rental()

        shareItModel.sendSelectedRental(shareItModel.getSelectedRental());
    }


}
