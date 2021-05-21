package client.viewmodel.manage_rentals;

import client.model.ShareItModel;
import client.model.state.StateManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Member;
import shared.transferobjects.Rental;

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

//    public boolean deleteRental() {
//        Rental rental = new Rental();
//        boolean deleteSuccessFull = shareItModel.deleteRental(rental);
//        return deleteSuccessFull;
//    }
}
