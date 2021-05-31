package client.viewmodel.create_account;

import client.core.ClientFactory;
import client.core.ModelFactory;
import client.core.ViewHandler;
import client.core.ViewModelFactory;

import client.model.member.MemberModel;
import client.model.member.MemberModelManager;
import client.model.message.MessageModel;
import client.model.message.MessageModelManager;
import client.model.rental.RentalModel;
import client.model.rental.RentalModelManager;
import client.network.Client;
import client.viewmodel.create_account.CreateAccountViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.transferobjects.City;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CreateAccountViewModelTest {
    private CreateAccountViewModel vm;
    private RentalModel rentalModel;
    private MemberModel memberModel;
    private MessageModel messageModel;
    private ModelFactory modelFactory;

    @BeforeEach
    public void setup() throws IOException, SQLException {
        ClientFactory clientFactory = new ClientFactory();
        modelFactory = new ModelFactory(clientFactory);
        rentalModel = new RentalModelManager(clientFactory.getClient());
        memberModel = new MemberModelManager(clientFactory.getClient(), modelFactory);
        messageModel = new MessageModelManager(clientFactory.getClient());
        vm = new CreateAccountViewModel(rentalModel, memberModel, messageModel);
    }

    @Test
    public void createValidUser() throws IOException {
        //arrange
        StringProperty usernameField = new SimpleStringProperty();
        StringProperty passwordField = new SimpleStringProperty();
        StringProperty confirmPasswordField = new SimpleStringProperty();
        StringProperty streetField = new SimpleStringProperty();
        StringProperty streetNumberField = new SimpleStringProperty();
        StringProperty postalCodeField = new SimpleStringProperty();
        StringProperty emailField = new SimpleStringProperty();
        StringProperty telephoneNoField = new SimpleStringProperty();
        StringProperty otherInfoField = new SimpleStringProperty();


        vm.getUsernameField().bind(usernameField);
        vm.getPasswordField().bind(passwordField);
        vm.getConfirmPasswordField().bind(confirmPasswordField);
        vm.getStreetField().bind(streetField);
        vm.getStreetNumberField().bind(streetNumberField);
        vm.getPostalCodeField().bind(postalCodeField);
        vm.getEmailField().bind(emailField);
        vm.getTelephoneNoField().bind(telephoneNoField);
        vm.getOtherInfoField().bind(otherInfoField);

        usernameField.setValue("brandNewUsername");
        passwordField.setValue("password");
        confirmPasswordField.setValue("password");
        streetField.setValue("Sundvej");
        streetNumberField.setValue("6B");
        postalCodeField.setValue("8700");
        emailField.setValue("304125@viauc.dk");
        telephoneNoField.setValue("");
        otherInfoField.setValue("Other information");

        String result = vm.onCreateButtonPressed("Horsens");

        assertEquals("Adding successful", result);
    }

    @Test
    public void notMatchingPasswords() throws IOException {
        //arrange
        StringProperty usernameField = new SimpleStringProperty();
        StringProperty passwordField = new SimpleStringProperty();
        StringProperty confirmPasswordField = new SimpleStringProperty();
        StringProperty streetField = new SimpleStringProperty();
        StringProperty streetNumberField = new SimpleStringProperty();
        StringProperty postalCodeField = new SimpleStringProperty();
        StringProperty emailField = new SimpleStringProperty();
        StringProperty telephoneNoField = new SimpleStringProperty();
        StringProperty otherInfoField = new SimpleStringProperty();
        vm.getUsernameField().bindBidirectional(usernameField);
        vm.getPasswordField().bindBidirectional(passwordField);
        vm.getConfirmPasswordField().bindBidirectional(confirmPasswordField);
        vm.getStreetField().bindBidirectional(streetField);
        vm.getStreetNumberField().bindBidirectional(streetNumberField);
        vm.getPostalCodeField().bindBidirectional(postalCodeField);
        vm.getEmailField().bindBidirectional(emailField);
        vm.getTelephoneNoField().bindBidirectional(telephoneNoField);
        vm.getOtherInfoField().bindBidirectional(otherInfoField);

        usernameField.setValue("newUsername");
        passwordField.setValue("password");
        confirmPasswordField.setValue("password1");
        streetField.setValue("Sundvej");
        streetNumberField.setValue("6B");
        postalCodeField.setValue("8700");
        emailField.setValue("304125@viauc.dk");
        telephoneNoField.setValue("");
        otherInfoField.setValue("Other information");

        String result = vm.onCreateButtonPressed("Horsens");

        assertEquals("Not matching passwords.", result);
    }

    @Test
    public void notMatchingPasswordsCaseInsensitive() throws IOException {
        //arrange
        StringProperty usernameField = new SimpleStringProperty();
        StringProperty passwordField = new SimpleStringProperty();
        StringProperty confirmPasswordField = new SimpleStringProperty();
        StringProperty streetField = new SimpleStringProperty();
        StringProperty streetNumberField = new SimpleStringProperty();
        StringProperty postalCodeField = new SimpleStringProperty();
        StringProperty emailField = new SimpleStringProperty();
        StringProperty telephoneNoField = new SimpleStringProperty();
        StringProperty otherInfoField = new SimpleStringProperty();
        vm.getUsernameField().bindBidirectional(usernameField);
        vm.getPasswordField().bindBidirectional(passwordField);
        vm.getConfirmPasswordField().bindBidirectional(confirmPasswordField);
        vm.getStreetField().bindBidirectional(streetField);
        vm.getStreetNumberField().bindBidirectional(streetNumberField);
        vm.getPostalCodeField().bindBidirectional(postalCodeField);
        vm.getEmailField().bindBidirectional(emailField);
        vm.getTelephoneNoField().bindBidirectional(telephoneNoField);
        vm.getOtherInfoField().bindBidirectional(otherInfoField);

        usernameField.setValue("newUsername");
        passwordField.setValue("Password");
        confirmPasswordField.setValue("password");
        streetField.setValue("Sundvej");
        streetNumberField.setValue("6B");
        postalCodeField.setValue("8700");
        emailField.setValue("304125@viauc.dk");
        telephoneNoField.setValue("");
        otherInfoField.setValue("Other information");

        String result = vm.onCreateButtonPressed("Horsens");

        assertEquals("Not matching passwords.", result);
    }

    @Test
    public void usernameTaken() throws IOException {
        //arrange
        StringProperty usernameField = new SimpleStringProperty();
        StringProperty passwordField = new SimpleStringProperty();
        StringProperty confirmPasswordField = new SimpleStringProperty();
        StringProperty streetField = new SimpleStringProperty();
        StringProperty streetNumberField = new SimpleStringProperty();
        StringProperty postalCodeField = new SimpleStringProperty();
        StringProperty emailField = new SimpleStringProperty();
        StringProperty telephoneNoField = new SimpleStringProperty();
        StringProperty otherInfoField = new SimpleStringProperty();
        vm.getUsernameField().bindBidirectional(usernameField);
        vm.getPasswordField().bindBidirectional(passwordField);
        vm.getConfirmPasswordField().bindBidirectional(confirmPasswordField);
        vm.getStreetField().bindBidirectional(streetField);
        vm.getStreetNumberField().bindBidirectional(streetNumberField);
        vm.getPostalCodeField().bindBidirectional(postalCodeField);
        vm.getEmailField().bindBidirectional(emailField);
        vm.getTelephoneNoField().bindBidirectional(telephoneNoField);
        vm.getOtherInfoField().bindBidirectional(otherInfoField);

        usernameField.setValue("bob");
        passwordField.setValue("SpongeBob");
        confirmPasswordField.setValue("SpongeBob");
        streetField.setValue("Sundvej");
        streetNumberField.setValue("6B");
        postalCodeField.setValue("8700");
        emailField.setValue("304125@viauc.dk");
        telephoneNoField.setValue("");
        otherInfoField.setValue("Other information");

        String result = vm.onCreateButtonPressed("Horsens");

        assertEquals("This username is already taken.", result);
    }

    @Test
    public void zeroContactGiven() throws IOException {
        StringProperty usernameField = new SimpleStringProperty();
        StringProperty passwordField = new SimpleStringProperty();
        StringProperty confirmPasswordField = new SimpleStringProperty();
        StringProperty streetField = new SimpleStringProperty();
        StringProperty streetNumberField = new SimpleStringProperty();
        StringProperty postalCodeField = new SimpleStringProperty();
        StringProperty emailField = new SimpleStringProperty();
        StringProperty telephoneNoField = new SimpleStringProperty();
        StringProperty otherInfoField = new SimpleStringProperty();
        vm.getUsernameField().bindBidirectional(usernameField);
        vm.getPasswordField().bindBidirectional(passwordField);
        vm.getConfirmPasswordField().bindBidirectional(confirmPasswordField);
        vm.getStreetField().bindBidirectional(streetField);
        vm.getStreetNumberField().bindBidirectional(streetNumberField);
        vm.getPostalCodeField().bindBidirectional(postalCodeField);
        vm.getEmailField().bindBidirectional(emailField);
        vm.getTelephoneNoField().bindBidirectional(telephoneNoField);
        vm.getOtherInfoField().bindBidirectional(otherInfoField);

        usernameField.setValue("newer_Bob");
        passwordField.setValue("SpongeBob<3");
        confirmPasswordField.setValue("SpongeBob<3");
        streetField.setValue("Sundvej");
        streetNumberField.setValue("6B");
        postalCodeField.setValue("8700");
        emailField.setValue("");
        telephoneNoField.setValue("");
        otherInfoField.setValue("Other information");

        String result = vm.onCreateButtonPressed("Horsens");

        assertEquals("At least one contact information has to be given.", result);
    }

    @Test
    public void oneContactGivenEmail() throws IOException {
        StringProperty usernameField = new SimpleStringProperty();
        StringProperty passwordField = new SimpleStringProperty();
        StringProperty confirmPasswordField = new SimpleStringProperty();
        StringProperty streetField = new SimpleStringProperty();
        StringProperty streetNumberField = new SimpleStringProperty();
        StringProperty postalCodeField = new SimpleStringProperty();
        StringProperty emailField = new SimpleStringProperty();
        StringProperty telephoneNoField = new SimpleStringProperty();
        StringProperty otherInfoField = new SimpleStringProperty();
        vm.getUsernameField().bindBidirectional(usernameField);
        vm.getPasswordField().bindBidirectional(passwordField);
        vm.getConfirmPasswordField().bindBidirectional(confirmPasswordField);
        vm.getStreetField().bindBidirectional(streetField);
        vm.getStreetNumberField().bindBidirectional(streetNumberField);
        vm.getPostalCodeField().bindBidirectional(postalCodeField);
        vm.getEmailField().bindBidirectional(emailField);
        vm.getTelephoneNoField().bindBidirectional(telephoneNoField);
        vm.getOtherInfoField().bindBidirectional(otherInfoField);

        usernameField.setValue("new_Bob_420_again");
        passwordField.setValue("SpongeBob<3");
        confirmPasswordField.setValue("SpongeBob<3");
        streetField.setValue("Sundvej");
        streetNumberField.setValue("6B");
        postalCodeField.setValue("8700");
        emailField.setValue("valid.mail@gmail.com");
        telephoneNoField.setValue("");
        otherInfoField.setValue("Other information");

        String result = vm.onCreateButtonPressed("Horsens");

        assertEquals("Adding successful", result);
    }

    @Test
    public void oneContactGivenTelephone() throws IOException {
        StringProperty usernameField = new SimpleStringProperty();
        StringProperty passwordField = new SimpleStringProperty();
        StringProperty confirmPasswordField = new SimpleStringProperty();
        StringProperty streetField = new SimpleStringProperty();
        StringProperty streetNumberField = new SimpleStringProperty();
        StringProperty postalCodeField = new SimpleStringProperty();
        StringProperty emailField = new SimpleStringProperty();
        StringProperty telephoneNoField = new SimpleStringProperty();
        StringProperty otherInfoField = new SimpleStringProperty();
        vm.getUsernameField().bindBidirectional(usernameField);
        vm.getPasswordField().bindBidirectional(passwordField);
        vm.getConfirmPasswordField().bindBidirectional(confirmPasswordField);
        vm.getStreetField().bindBidirectional(streetField);
        vm.getStreetNumberField().bindBidirectional(streetNumberField);
        vm.getPostalCodeField().bindBidirectional(postalCodeField);
        vm.getEmailField().bindBidirectional(emailField);
        vm.getTelephoneNoField().bindBidirectional(telephoneNoField);
        vm.getOtherInfoField().bindBidirectional(otherInfoField);

        usernameField.setValue("new_Bobby");
        passwordField.setValue("SpongeBob<3");
        confirmPasswordField.setValue("SpongeBob<3");
        streetField.setValue("Sundvej");
        streetNumberField.setValue("6B");
        postalCodeField.setValue("8700");
        emailField.setValue("");
        telephoneNoField.setValue("+45 82 69 42 08");
        otherInfoField.setValue("Other information");

        String result = vm.onCreateButtonPressed("Horsens");

        assertEquals("Adding successful", result);
    }

    @Test
    public void bothContactInformationGiven() throws IOException {
        StringProperty usernameField = new SimpleStringProperty();
        StringProperty passwordField = new SimpleStringProperty();
        StringProperty confirmPasswordField = new SimpleStringProperty();
        StringProperty streetField = new SimpleStringProperty();
        StringProperty streetNumberField = new SimpleStringProperty();
        StringProperty postalCodeField = new SimpleStringProperty();
        StringProperty emailField = new SimpleStringProperty();
        StringProperty telephoneNoField = new SimpleStringProperty();
        StringProperty otherInfoField = new SimpleStringProperty();
        vm.getUsernameField().bindBidirectional(usernameField);
        vm.getPasswordField().bindBidirectional(passwordField);
        vm.getConfirmPasswordField().bindBidirectional(confirmPasswordField);
        vm.getStreetField().bindBidirectional(streetField);
        vm.getStreetNumberField().bindBidirectional(streetNumberField);
        vm.getPostalCodeField().bindBidirectional(postalCodeField);
        vm.getEmailField().bindBidirectional(emailField);
        vm.getTelephoneNoField().bindBidirectional(telephoneNoField);
        vm.getOtherInfoField().bindBidirectional(otherInfoField);

        usernameField.setValue("new_Bobik");
        passwordField.setValue("SpongeBob<3");
        confirmPasswordField.setValue("SpongeBob<3");
        streetField.setValue("Sundvej");
        streetNumberField.setValue("6B");
        postalCodeField.setValue("8700");
        emailField.setValue("valid.mail@gmail.com");
        telephoneNoField.setValue("+45 82 69 42 08");
        otherInfoField.setValue("Other information");

        String result = vm.onCreateButtonPressed("Horsens");

        assertEquals("Adding successful", result);
    }

    @Test
    public void invalidPostalCode() throws IOException {
        StringProperty usernameField = new SimpleStringProperty();
        StringProperty passwordField = new SimpleStringProperty();
        StringProperty confirmPasswordField = new SimpleStringProperty();
        StringProperty streetField = new SimpleStringProperty();
        StringProperty streetNumberField = new SimpleStringProperty();
        StringProperty postalCodeField = new SimpleStringProperty();
        StringProperty emailField = new SimpleStringProperty();
        StringProperty telephoneNoField = new SimpleStringProperty();
        StringProperty otherInfoField = new SimpleStringProperty();
        vm.getUsernameField().bindBidirectional(usernameField);
        vm.getPasswordField().bindBidirectional(passwordField);
        vm.getConfirmPasswordField().bindBidirectional(confirmPasswordField);
        vm.getStreetField().bindBidirectional(streetField);
        vm.getStreetNumberField().bindBidirectional(streetNumberField);
        vm.getPostalCodeField().bindBidirectional(postalCodeField);
        vm.getEmailField().bindBidirectional(emailField);
        vm.getTelephoneNoField().bindBidirectional(telephoneNoField);
        vm.getOtherInfoField().bindBidirectional(otherInfoField);

        usernameField.setValue("new_Bob_clear");
        passwordField.setValue("SpongeBob<3");
        confirmPasswordField.setValue("SpongeBob<3");
        streetField.setValue("Sundvej");
        streetNumberField.setValue("6B");
        postalCodeField.setValue("87Troll00");
        emailField.setValue("valid.mail@gmail.com");
        telephoneNoField.setValue("+45 82 69 42 08");
        otherInfoField.setValue("Other information");

        String result = vm.onCreateButtonPressed("Horsens");

        assertEquals("Postal code has to be a number.", result);
    }

}