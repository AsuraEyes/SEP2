package client.viewmodel.create_account;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CreateAccountViewModel {
    private final StringProperty searchField;
    private final StringProperty userNameField;
    private final StringProperty passwordField;
    private final StringProperty confirmPasswordField;
    private final StringProperty streetField;
    private final StringProperty streetNumberField;
    private final StringProperty floorField;
    private final StringProperty apartmentNoField;
    private final StringProperty emailField;
    private final StringProperty telephoneNo1Field;
    private final StringProperty telephoneNo2Field;
    private final StringProperty otherInfoField;

    public CreateAccountViewModel(){
        searchField = new SimpleStringProperty("Search");
        userNameField = new SimpleStringProperty("Username");
        passwordField = new SimpleStringProperty("Password");
        confirmPasswordField = new SimpleStringProperty("Confirm Password");
        streetField = new SimpleStringProperty("Street");
        streetNumberField = new SimpleStringProperty("Street Number");
        floorField = new SimpleStringProperty("Floor");
        apartmentNoField = new SimpleStringProperty("Apartment Number");
        emailField = new SimpleStringProperty("Email");
        telephoneNo1Field = new SimpleStringProperty("Telephone Number 1");
        telephoneNo2Field = new SimpleStringProperty("Telephone Number 2");
        otherInfoField = new SimpleStringProperty("Other Information");
    }

    public StringProperty getSearchField(){
        return searchField;
    }
    public StringProperty getUserNameField(){
        return userNameField;
    }
    public StringProperty getPasswordField(){
        return passwordField;
    }
    public StringProperty getConfirmPasswordField(){
        return confirmPasswordField;
    }
    public StringProperty getStreetField(){
        return streetField;
    }
    public StringProperty getStreetNumberField(){
        return streetNumberField;
    }
    public StringProperty getFloorField(){
        return floorField;
    }
    public StringProperty getApartmentNoField(){
        return apartmentNoField;
    }
    public StringProperty getEmailField(){
        return emailField;
    }
    public StringProperty getTelephoneNo1Field(){
        return telephoneNo1Field;
    }
    public StringProperty getTelephoneNo2Field(){
        return telephoneNo2Field;
    }
    public StringProperty getOtherInfoField(){
        return otherInfoField;
    }
}
