package client.views.create_account;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.create_account.CreateAccountViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.CustomPasswordField;
import org.controlsfx.validation.ValidationSupport;

import java.sql.SQLException;

public class CreateAccountController {
    @FXML
    private AnchorPane parent;
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
    private TextField postalCodeField;
    @FXML
    private ChoiceBox locationBox;
    @FXML
    private TextField emailField;
    @FXML
    private TextField telephoneNoField;
    @FXML
    private TextArea otherInfoField;

    private CreateAccountViewModel createAccountViewModel;
    private ViewHandler viewHandler;
    private ValidationSupport validationSupport;
    private Notifications notifications;

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
        postalCodeField.textProperty().bindBidirectional(createAccountViewModel.getPostalCodeField());
        emailField.textProperty().bindBidirectional(createAccountViewModel.getEmailField());
        telephoneNoField.textProperty().bindBidirectional(createAccountViewModel.getTelephoneNoField());
        otherInfoField.textProperty().bindBidirectional(createAccountViewModel.getOtherInfoField());


//        validationSupport = new ValidationSupport();
//        validationSupport.setErrorDecorationEnabled(false);
//        validationSupport.registerValidator(usernameField, Validator.createEmptyValidator("Username is required"));

        notifications =  Notifications.create()
                .title("Error - invalid input!")
                .graphic(new Rectangle(300, 300, Color.RED)) // sets node to display
                .hideAfter(Duration.seconds(3));
    }

    public void searchButton(ActionEvent actionEvent) {

    }

    public void goBackToLogInButton(ActionEvent actionEvent) {

    }

    public void createButton(ActionEvent actionEvent) throws SQLException {
        boolean ok = true;
        if(checkField(usernameField) && checkField(passwordField) && checkField(confirmPasswordField) && checkField(streetField) && checkField(streetNumberField) && checkField(postalCodeField)){
            createAccountViewModel.onCreateButtonPressed();
        }
    }

    private boolean checkField(TextField nameOfField){
        if(nameOfField.textProperty().getValue() == null || nameOfField.textProperty().getValue().isBlank()){
            notifications.owner(parent).text(nameOfField.getPromptText()+" cannot be empty").showError();
            return false;
        }
        return true;
    }
}
