package client.viewmodel.create_account;

import client.model.data_check.DataCheckMember;
import client.model.database.member.MemberDAOImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.AnchorPane;

import java.lang.reflect.AnnotatedArrayType;
import java.sql.SQLException;

public class CreateAccountViewModel {
    private DataCheckMember dataCheckMember;
    private final StringProperty searchField;
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

    public CreateAccountViewModel() throws SQLException {
        dataCheckMember = new DataCheckMember();
        searchField = new SimpleStringProperty();
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

    public StringProperty getSearchField(){
        return searchField;
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

    public void onCreateButtonPressed(){
        dataCheckMember.checkData(usernameField.getValue(), passwordField.getValue(), confirmPasswordField.getValue(), emailField.getValue(), telephoneNoField.getValue(), otherInfoField.getValue(), streetField.getValue(), streetNumberField.getValue()+", "+floorField.getValue(), postalCodeField.getValue(),  "Horsens");
    }
}
