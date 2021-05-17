package client.viewmodel.create_account;

import client.model.ShareItModel;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobjects.City;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreateAccountViewModel {
    private ShareItModel model;
    private final StringProperty usernameField;
    private final StringProperty passwordField;
    private final StringProperty confirmPasswordField;
    private final StringProperty streetField;
    private final StringProperty streetNumberField;
    private final StringProperty floorField;
    private final StringProperty postalCodeField;
    private final StringProperty emailField;
    private final StringProperty telephoneNoField;
    private final StringProperty otherInfoField;
    private final ObservableValue locationBox;
    private ObservableList<String> locationsList;

    public CreateAccountViewModel(ShareItModel model) throws SQLException {
        this.model = model;
        usernameField = new SimpleStringProperty();
        passwordField = new SimpleStringProperty();
        confirmPasswordField = new SimpleStringProperty();
        streetField = new SimpleStringProperty();
        streetNumberField = new SimpleStringProperty();
        floorField = new SimpleStringProperty();
        postalCodeField = new SimpleStringProperty();
        emailField = new SimpleStringProperty();
        telephoneNoField = new SimpleStringProperty();
        otherInfoField = new SimpleStringProperty();
        locationBox = new SimpleStringProperty();
        //model.addListener("dataValidation", this::onDataValidation);
    }

    public StringProperty getUsernameField(){
        return usernameField;
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
    public StringProperty getPostalCodeField(){
        return postalCodeField;
    }
    public StringProperty getEmailField(){
        return emailField;
    }
    public StringProperty getTelephoneNoField(){
        return telephoneNoField;
    }
    public StringProperty getOtherInfoField(){
        return otherInfoField;
    }
    public ObservableValue getLocationBox() {
        return locationBox;
    }

    public String onCreateButtonPressed(String selectedCity) throws IOException {
        return model.checkMemberData(usernameField.getValue(), passwordField.getValue(), confirmPasswordField.getValue(), emailField.getValue(), telephoneNoField.getValue(), otherInfoField.getValue(), streetField.getValue(), streetNumberField.getValue()+", "+floorField.getValue(), postalCodeField.getValue(),  selectedCity);
    }

    public ObservableList<String> getLocations(){
        ArrayList<City> cityList = model.getCityList();
        ArrayList<String> cityListString = new ArrayList<>();
        for (int i = 0; i < cityList.size(); i++) {
            cityListString.add(cityList.get(i).toString());
        }
        locationsList = FXCollections.observableArrayList(cityListString);
        return locationsList;
    }

//    public void onDataValidation(PropertyChangeEvent evt){
//        if(evt.getPropertyName().equals("success")){
//
//        }
//    }
}
