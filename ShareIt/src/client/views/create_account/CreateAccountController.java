package client.views.create_account;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.create_account.CreateAccountViewModel;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.CustomPasswordField;

public class CreateAccountController {
    public TextField searchField;
    public TextField userNameField;
    private PasswordField passwordField;
    private CustomPasswordField confirmPasswordField;
    private TextField streetField;
    private TextField streetNumberField;
    private TextField floorField;
    private TextField apartmentNoField;
    private ComboBox locationBox;
    private TextField emailField;
    private TextField telephoneNo1Field;
    private TextField telephoneNo2Field;
    public TextArea otherInfoField;

    private CreateAccountViewModel createAccountViewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        createAccountViewModel = viewModelFactory.getCreateAccountViewModel();
        searchField.textProperty().bindBidirectional(createAccountViewModel.getSearchField());
        userNameField.textProperty().bindBidirectional(createAccountViewModel.getUserNameField());
        passwordField.textProperty().bindBidirectional(createAccountViewModel.getPasswordField());
        confirmPasswordField.textProperty().bindBidirectional(createAccountViewModel.getConfirmPasswordField());
        streetField.textProperty().bindBidirectional(createAccountViewModel.getStreetField());
        streetNumberField.textProperty().bindBidirectional(createAccountViewModel.getStreetNumberField());
        floorField.textProperty().bindBidirectional(createAccountViewModel.getFloorField());
        apartmentNoField.textProperty().bindBidirectional(createAccountViewModel.getApartmentNoField());
        emailField.textProperty().bindBidirectional(createAccountViewModel.getEmailField());
        telephoneNo1Field.textProperty().bindBidirectional(createAccountViewModel.getTelephoneNo1Field());
        telephoneNo2Field.textProperty().bindBidirectional(createAccountViewModel.getTelephoneNo2Field());
        otherInfoField.textProperty().bindBidirectional(createAccountViewModel.getOtherInfoField());
    }

    public void searchButton(ActionEvent actionEvent) {

    }

    public void goBackToLogInButton(ActionEvent actionEvent) {

    }

    public void createButton(ActionEvent actionEvent) {

    }
}
