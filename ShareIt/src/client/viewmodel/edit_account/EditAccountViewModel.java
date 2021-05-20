package client.viewmodel.edit_account;

import client.model.ShareItModel;
import client.model.state.StateManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobjects.City;
import shared.transferobjects.Member;

import java.io.IOException;
import java.util.ArrayList;

public class EditAccountViewModel {
    private ShareItModel shareItModel;
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
    private ObservableList<String> locationsList;

    public EditAccountViewModel(ShareItModel model) {
        shareItModel = model;
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

    public String onEditButtonPressed(String selectedCity) throws IOException {
        return shareItModel.updateCheckMemberData(usernameField.getValue(), passwordField.getValue(), confirmPasswordField.getValue(),
                emailField.getValue(), telephoneNoField.getValue(), otherInfoField.getValue(), streetField.getValue(),
                streetNumberField.getValue()+", "+floorField.getValue(), postalCodeField.getValue(),  selectedCity);
    }

    public ObservableList<String> getLocations(){
        ArrayList<City> cityList = shareItModel.getCityList();
        ArrayList<String> cityListString = new ArrayList<>();
        for (int i = 0; i < cityList.size(); i++) {
            cityListString.add(cityList.get(i).toString());
        }
        locationsList = FXCollections.observableArrayList(cityListString);
        return locationsList;
    }

    public void setProfile(){
        Member member = shareItModel.getMemberByUsername(StateManager.getInstance()
            .getUsername());
        usernameField.setValue(member.getUsername());
        //locationsList.setValue(member.getAddressCity());
        passwordField.setValue(member.getPassword());
        confirmPasswordField.setValue(member.getPassword());
        streetField.setValue(member.getAddressStreet());
        streetNumberField.setValue(member.getAddressNo());
        postalCodeField.setValue(String.valueOf(member.getAddressPostalCode()));
        emailField.setValue(member.getEmailAddress());
        telephoneNoField.setValue(member.getPhoneNo());
        otherInfoField.setValue(member.getOtherInformation());
    }
}
