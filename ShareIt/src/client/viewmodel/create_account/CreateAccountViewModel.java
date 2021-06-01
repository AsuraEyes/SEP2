package client.viewmodel.create_account;

import client.model.member.MemberModel;
import client.model.rental.RentalModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobjects.City;

import java.util.ArrayList;
/**
 * A class that holds and manages data from the CreateAccount view.
 */
public class CreateAccountViewModel
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
     * Instantiates a new CreateAccountViewModel.
     *
     * @param model The model that this ViewModel uses
     */
  public CreateAccountViewModel(RentalModel rentalModel,
      MemberModel memberModel)
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
    /**
     * Gets userNameField.
     *
     * @return returns userNameField input
     */
  public StringProperty getUsernameField()
  {
    return usernameField;
  }
    /**
     * Gets passwordField.
     *
     * @return returns passwordField input
     */
  public StringProperty getPasswordField()
  {
    return passwordField;
  }
    /**
     * Gets confirmPasswordField.
     *
     * @return returns confirmPasswordField input
     */
  public StringProperty getConfirmPasswordField()
  {
    return confirmPasswordField;
  }
    /**
     * Gets streetField.
     *
     * @return returns streetField input
     */
  public StringProperty getStreetField()
  {
    return streetField;
  }
    /**
     * Gets streetNumberField.
     *
     * @return returns streetNumberField input
     */
  public StringProperty getStreetNumberField()
  {
    return streetNumberField;
  }
    /**
     * Gets postalCodeField.
     *
     * @return returns postalCodeField input
     */
  public StringProperty getPostalCodeField()
  {
    return postalCodeField;
  }
    /**
     * Gets emailField.
     *
     * @return returns emailField input
     */
  public StringProperty getEmailField()
  {
    return emailField;
  }
    /**
     * Gets telephoneField.
     *
     * @return returns telephoneField input
     */
  public StringProperty getTelephoneNoField()
  {
    return telephoneNoField;
  }
    /**
     * Gets otherInformationField.
     *
     * @return returns otherInformationField input
     */
  public StringProperty getOtherInfoField()
  {
    return otherInfoField;
  }
    /**
     * After Create button have been pressed this method sends data to the model.
     *
     * @param selectedCity The selected city
     * @return returns new Member object
     * @throws IOException
     */
  public String onCreateButtonPressed(String selectedCity)
  {
    return memberModel
        .checkMemberData(usernameField.getValue(), passwordField.getValue(),
            confirmPasswordField.getValue(), emailField.getValue(),
            telephoneNoField.getValue(), otherInfoField.getValue(),
            streetField.getValue(), streetNumberField.getValue(),
            postalCodeField.getValue(), selectedCity);
  }
    /**
     * Gets cities in a list.
     *
     * @return returns a list of cities
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
}
