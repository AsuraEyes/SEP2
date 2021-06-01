package client.viewmodel.create_account;

import client.model.member.MemberModel;
import client.model.rental.RentalModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobjects.City;

import java.util.ArrayList;

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

  public String onCreateButtonPressed(String selectedCity)
  {
    return memberModel
        .checkMemberData(usernameField.getValue(), passwordField.getValue(),
            confirmPasswordField.getValue(), emailField.getValue(),
            telephoneNoField.getValue(), otherInfoField.getValue(),
            streetField.getValue(), streetNumberField.getValue(),
            postalCodeField.getValue(), selectedCity);
  }

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
