package client.viewmodel.edit_account;

import client.model.member.MemberModel;
import client.model.rental.RentalModel;
import client.model.state.VisitorState;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobjects.City;
import shared.transferobjects.Member;

import java.util.ArrayList;
/**
 * A class that holds and manages data from the EditAccount view.
 */
public class EditAccountViewModel
{
  private RentalModel rentalModel;
  private MemberModel memberModel;

  private StringProperty usernameField;
  private StringProperty passwordField;
  private StringProperty confirmPasswordField;
  private StringProperty streetField;
  private StringProperty streetNumberField;
  private StringProperty postalCodeField;
  private StringProperty emailField;
  private StringProperty telephoneNoField;
  private StringProperty otherInfoField;
  private ObservableList<String> locationsList;
    /**
     * Instantiates StringProperties used for binding with the fields in the controller
     *
     * @param rentalModel The model that this ViewModel uses
     * @param memberModel The model that this ViewModel uses
     */
  public EditAccountViewModel(RentalModel rentalModel, MemberModel memberModel)
  {
    this.rentalModel = rentalModel;
    this.memberModel = memberModel;
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

  public StringProperty getUsernameField()
  {
    return usernameField;
  }

  public StringProperty getPasswordField()
  {
    return passwordField;
  }

  public StringProperty getConfirmPasswordField()
  {
    return confirmPasswordField;
  }

  public StringProperty getStreetField()
  {
    return streetField;
  }

  public StringProperty getStreetNumberField()
  {
    return streetNumberField;
  }

  public StringProperty getPostalCodeField()
  {
    return postalCodeField;
  }

  public StringProperty getEmailField()
  {
    return emailField;
  }

  public StringProperty getTelephoneNoField()
  {
    return telephoneNoField;
  }

  public StringProperty getOtherInfoField()
  {
    return otherInfoField;
  }
    /**
     * After Edit button have been pressed this method sends data to the model
     * and receives a validation message if data is missing, invalid or the account
     * was successfully edited.
     *
     * @param selectedCity The selected city
     * @return Member object with edited data
     */
  public String onEditButtonPressed(String selectedCity)
  {
    return memberModel.updateCheckMemberData(memberModel.getLoggedInUsername(),
        passwordField.getValue(), confirmPasswordField.getValue(),
        emailField.getValue(), telephoneNoField.getValue(),
        otherInfoField.getValue(), streetField.getValue(),
        streetNumberField.getValue(), postalCodeField.getValue(), selectedCity);
  }
    /**
     * Gets cities in a list from the database and places them in a Observable
     * list to be bound to a listing element in controller..
     *
     * @return a list of cities
     */
  public ObservableList<String> getLocations()
  {
    ArrayList<City> cityList = rentalModel.getCityList();
    ArrayList<String> cityListString = new ArrayList<>();
    for (int i = 0; i < cityList.size(); i++)
    {
      cityListString.add(cityList.get(i).toString());
    }
    locationsList = FXCollections.observableArrayList(cityListString);
    return locationsList;
  }
    /**
     * Sets the logged in username data in the controller by getting it from the
     * database.
     */
  public void setProfile()
  {
    Member member = memberModel
        .getMemberByUsername(memberModel.getLoggedInUsername());
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
     * Deletes logged member account and sets user type to Visitor
     *
     * @return true if process was successful
     */
  public boolean deleteAccount()
  {
    Member member = memberModel
        .getMemberByUsername(memberModel.getLoggedInUsername());
    boolean deleteSuccessful = memberModel.deleteMember(member);
    if (deleteSuccessful)
    {
      memberModel.setUserType("visitor", null);
    }
    return deleteSuccessful;
  }
    /**
     * Gets selected location.
     *
     * @return the selected location
     */
  public String getSelectedLocation()
  {
    return memberModel.getMemberByUsername(usernameField.getValue())
        .getAddressCity();
  }
}
