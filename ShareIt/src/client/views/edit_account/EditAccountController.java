package client.views.edit_account;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.edit_account.EditAccountViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.CustomPasswordField;
import org.controlsfx.validation.ValidationSupport;

import java.io.IOException;
import java.sql.SQLException;

public class EditAccountController {
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

    private EditAccountViewModel editAccountViewModel;
    private ViewHandler viewHandler;
    private ValidationSupport validationSupport;
    private Notifications notifications;


    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws SQLException, IOException {
        this.viewHandler = viewHandler;
        editAccountViewModel = viewModelFactory.getEditAccountViewModel();
        usernameField.textProperty().bindBidirectional(editAccountViewModel.getUsernameField());
        passwordField.textProperty().bindBidirectional(editAccountViewModel.getPasswordField());
        confirmPasswordField.textProperty().bindBidirectional(editAccountViewModel.getConfirmPasswordField());
        streetField.textProperty().bindBidirectional(editAccountViewModel.getStreetField());
        streetNumberField.textProperty().bindBidirectional(editAccountViewModel.getStreetNumberField());
        floorField.textProperty().bindBidirectional(editAccountViewModel.getFloorField());
        postalCodeField.textProperty().bindBidirectional(editAccountViewModel.getPostalCodeField());
        emailField.textProperty().bindBidirectional(editAccountViewModel.getEmailField());
        telephoneNoField.textProperty().bindBidirectional(editAccountViewModel.getTelephoneNoField());
        otherInfoField.textProperty().bindBidirectional(editAccountViewModel.getOtherInfoField());

        locationBox.setItems(editAccountViewModel.getLocations());
        locationBox.getSelectionModel().selectFirst();

      notifications =  Notifications.create()
                .title("Error - invalid input!")
                .graphic(new Rectangle(300, 300, Color.RED)) // sets node to display
                .hideAfter(Duration.seconds(3));

    }
    public void goBackToProfile(ActionEvent actionEvent) throws IOException {
//        viewHandler.setView(viewHandler.menu(), viewHandler.);
    }

    public void editButton(ActionEvent actionEvent) throws IOException {
//        boolean ok = true;
//        if(checkField(usernameField) && checkField(passwordField) && checkField(confirmPasswordField) && checkField(streetField) && checkField(streetNumberField) && checkField(postalCodeField)){
//            String message = editAccountViewModel.onEditButtonPressed(locationBox.getValue());
//            switch (message){
//                case "Adding successful":
//                    //notifications.owner(parent).text("Your account has been successfully created! ").title(message).showConfirm();
//
//                    Stage stage = (Stage) viewHandler.getStage().getScene().getWindow();
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "");
//                    alert.setTitle("Confirmation");
//                    alert.setHeaderText("Account successfully edited");
//                    alert.initOwner(stage);
//                    alert.getDialogPane().setContentText("Click ok to return to your profile.");
//
//                    Optional<ButtonType> result = alert.showAndWait();
//                    if (result.get() == ButtonType.OK)
//                    {
//                        viewHandler.setView(viewHandler.menu(), viewHandler.welcomePage());
//                    }
//                    break;
//                default:
//                    notifications.owner(parent).text(message).showError();
//            }
//        }
    }

    private boolean checkField(TextField nameOfField){
        if(nameOfField.textProperty().getValue() == null || nameOfField.textProperty().getValue().isBlank()){
            notifications.owner(parent).text(nameOfField.getPromptText()+" cannot be empty").showError();
            return false;
        }
        return true;
    }
}
