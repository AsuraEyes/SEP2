package client.views.create_account;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.model.state.MemberState;
import client.model.state.StateManager;
import client.viewmodel.create_account.CreateAccountViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.MaskerPane;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.CustomPasswordField;
import org.controlsfx.validation.ValidationSupport;
import shared.transferobjects.City;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class CreateAccountController {
    @FXML
    private AnchorPane parent;
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
    private ChoiceBox<String> locationBox;
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

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws SQLException, IOException {
        this.viewHandler = viewHandler;
        createAccountViewModel = viewModelFactory.getCreateAccountViewModel();
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

        locationBox.setItems(createAccountViewModel.getLocations());
        locationBox.getSelectionModel().selectFirst();

//        validationSupport = new ValidationSupport();
//        validationSupport.setErrorDecorationEnabled(false);
//        validationSupport.registerValidator(usernameField, Validator.createEmptyValidator("Username is required"));

        notifications =  Notifications.create()
                .title("Error - invalid input!")
                .graphic(new Rectangle(300, 300, Color.RED)) // sets node to display
                .hideAfter(Duration.seconds(3));
    }

    public void goBackToLogInButton(ActionEvent actionEvent) throws IOException {
        viewHandler.setView(viewHandler.menu(), viewHandler.logIn());
    }

    public void createButton(ActionEvent actionEvent) throws IOException {
        boolean ok = true;
        if(checkField(usernameField) && checkField(passwordField) && checkField(confirmPasswordField) && checkField(streetField) && checkField(streetNumberField) && checkField(postalCodeField)){
            String message = createAccountViewModel.onCreateButtonPressed(locationBox.getValue());
            switch (message){
                case "Adding successful":
                    //notifications.owner(parent).text("Your account has been successfully created! ").title(message).showConfirm();

                    Stage stage = (Stage) viewHandler.getStage().getScene().getWindow();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "");
                    alert.setTitle("Confirmation");
                    alert.setHeaderText("Account successfully created");
                    alert.initOwner(stage);
                    alert.getDialogPane().setContentText("Click ok to get to welcome page.");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK)
                    {
                        viewHandler.setView(viewHandler.menu(), viewHandler.welcomePage());
                    }
                    break;
                default:
                    notifications.owner(parent).text(message).showError();
            }
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
