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
    private final StringProperty telephoneNo1Field;
    private final StringProperty telephoneNo2Field;
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
        telephoneNo1Field = new SimpleStringProperty();
        telephoneNo2Field = new SimpleStringProperty();
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
    public StringProperty getTelephoneNo1Field(){
        return telephoneNo1Field;
    }
    public StringProperty getTelephoneNo2Field(){
        return telephoneNo2Field;
    }
    public StringProperty getOtherInfoField(){
        return otherInfoField;
    }

    public void onCreateButtonPressed(){
        dataCheckMember.checkData(usernameField.getValue(), passwordField.getValue(), confirmPasswordField.getValue(), emailField.getValue(), otherInfoField.getValue(), telephoneNo1Field.getValue(), telephoneNo2Field.getValue(), streetField.getValue(), streetNumberField.getValue()+", "+floorField.getValue(), postalCodeField.getValue(),  "Horsens");
    }
}
