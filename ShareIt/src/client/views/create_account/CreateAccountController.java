package client.views.create_account;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.create_account.CreateAccountViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.CustomPasswordField;

import java.sql.SQLException;

public class CreateAccountController {
    @FXML
    private TextField searchField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CustomPasswordField confirmPasswordField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField streetNumberField;
    @FXML
    private TextField floorField;
    @FXML
    private TextField apartmentNoField;
    @FXML
    private ComboBox locationBox;
    @FXML
    private TextField emailField;
    @FXML
    private TextField telephoneNo1Field;
    @FXML
    private TextField telephoneNo2Field;
    @FXML
    public TextArea otherInfoField;

    private CreateAccountViewModel createAccountViewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws SQLException {
        this.viewHandler = viewHandler;
        createAccountViewModel = viewModelFactory.getCreateAccountViewModel();
        searchField.textProperty().bindBidirectional(createAccountViewModel.getSearchField());
        usernameField.textProperty().bindBidirectional(createAccountViewModel.getUsernameField());
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

    public void createButton(ActionEvent actionEvent) throws SQLException {
        createAccountViewModel.onCreateButtonPressed();
    }
}
