package client.viewmodel.edit_account;

import client.model.ShareItModel;
import client.model.state.StateManager;
import client.model.state.VisitorState;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobjects.City;
import shared.transferobjects.Member;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A class that holds and manages data from the EditAccount view.
 */
public class EditAccountViewModel {
    private ShareItModel shareItModel;
    private final StringProperty usernameField;
    private final StringProperty passwordField;
    private final StringProperty confirmPasswordField;
    private final StringProperty streetField;
    private final StringProperty streetNumberField;
    private final StringProperty postalCodeField;
    private final StringProperty emailField;
    private final StringProperty telephoneNoField;
    private final StringProperty otherInfoField;
    private ObservableList<String> locationsList;

    /**
     * Instantiates a new EditAccountViewModel.
     *
     * @param model The model that this ViewModel uses
     */
    public EditAccountViewModel(ShareItModel model) {
        shareItModel = model;
        usernameField = new SimpleStringProperty();
        passwordField = new SimpleStringProperty();
        confirmPasswordField = new SimpleStringProperty();
        streetField = new SimpleStringProperty();
        streetNumberField = new SimpleStringProperty();
        postalCodeField = new SimpleStringProperty();
        emailField = new SimpleStringProperty();
        telephoneNoField = new SimpleStringProperty();
        otherInfoField = new SimpleStringProperty();
    }

    /**
     * Gets userNameField.
     *
     * @return returns userNameField input
     */
    public StringProperty getUsernameField(){
        return usernameField;
    }

    /**
     * Gets passwordField.
     *
     * @return returns userNameField input
     */
    public StringProperty getPasswordField(){
        return passwordField;
    }

    /**
     * Gets confirmPasswordField.
     *
     * @return returns confirmPasswordField input
     */
    public StringProperty getConfirmPasswordField(){
        return confirmPasswordField;
    }

    /**
     * Gets streetField.
     *
     * @return returns streetField input
     */
    public StringProperty getStreetField(){
        return streetField;
    }

    /**
     * Gets streetNumberField.
     *
     * @return returns streetNumberField input
     */
    public StringProperty getStreetNumberField(){
        return streetNumberField;
    }

    /**
     * Gets postalCodeField.
     *
     * @return returns postalCodeField input
     */
    public StringProperty getPostalCodeField(){
        return postalCodeField;
    }

    /**
     * Gets emailField.
     *
     * @return returns emailField input
     */
    public StringProperty getEmailField(){
        return emailField;
    }

    /**
     * Gets telephoneField.
     *
     * @return returns telephoneField input
     */
    public StringProperty getTelephoneNoField(){
        return telephoneNoField;
    }

    /**
     * Gets otherInformationField.
     *
     * @return returns otherInformationField input
     */
    public StringProperty getOtherInfoField(){
        return otherInfoField;
    }

    /**
     * After Edit button have been pressed this method sends data to the model.
     *
     * @param selectedCity The selected city
     * @return returns Member object with edited data
     * @throws IOException
     */
    public String onEditButtonPressed(String selectedCity) throws IOException {
        return shareItModel.updateCheckMemberData(StateManager.getInstance().getUsername(), passwordField.getValue(), confirmPasswordField.getValue(),
                emailField.getValue(), telephoneNoField.getValue(), otherInfoField.getValue(), streetField.getValue(),
                streetNumberField.getValue(), postalCodeField.getValue(),  selectedCity);
    }

    /**
     * Gets cities in a list.
     *
     * @return returns a list of cities
     */
    public ObservableList<String> getLocations(){
        ArrayList<City> cityList = shareItModel.getCityList();
        ArrayList<String> cityListString = new ArrayList<>();
        for (int i = 0; i < cityList.size(); i++) {
            cityListString.add(cityList.get(i).toString());
        }
        locationsList = FXCollections.observableArrayList(cityListString);
        return locationsList;
    }

    /**
     * Sets profile information.
     */
    public void setProfile(){
        Member member = shareItModel.getMemberByUsername(StateManager.getInstance()
            .getUsername());
        usernameField.setValue(member.getUsername());
        passwordField.setValue(member.getPassword());
        confirmPasswordField.setValue(member.getPassword());
        streetField.setValue(member.getAddressStreet());
        streetNumberField.setValue(member.getAddressNo());
        postalCodeField.setValue(String.valueOf(member.getAddressPostalCode()));
        emailField.setValue(member.getEmailAddress());
        telephoneNoField.setValue(member.getPhoneNo());
        otherInfoField.setValue(member.getOtherInformation());
    }

    /**
     * Checks members data before deleting.
     *
     * @return  deletes if process was successful
     */
    public boolean deleteAccount(){
        Member member = shareItModel.getMemberByUsername(StateManager.getInstance()
                .getUsername());
        boolean deleteSuccessful = shareItModel.deleteMember(member);
        if(deleteSuccessful){
            StateManager.getInstance().setLoginState(new VisitorState());
        }
        return deleteSuccessful;
    }

    /**
     * Gets selected location.
     *
     * @return returns the selected location
     */
    public String getSelectedLocation() {
        return shareItModel.getMemberByUsername(usernameField.getValue()).getAddressCity();
    }
}
