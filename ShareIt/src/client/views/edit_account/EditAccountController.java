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
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.CustomPasswordField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

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
    private Notifications notifications;


    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws SQLException, IOException {
        this.viewHandler = viewHandler;
        editAccountViewModel = viewModelFactory.getEditAccountViewModel();
        //Bindings.bindBidirectional(usernameField.getPromptText()., editAccountViewModel.getUsernameField());
        usernameField.textProperty().bind(editAccountViewModel.getUsernameField());
        passwordField.textProperty().bindBidirectional(editAccountViewModel.getPasswordField());
        confirmPasswordField.textProperty().bindBidirectional(editAccountViewModel.getConfirmPasswordField());
        streetField.textProperty().bindBidirectional(editAccountViewModel.getStreetField());
        streetNumberField.textProperty().bindBidirectional(editAccountViewModel.getStreetNumberField());
        postalCodeField.textProperty().bindBidirectional(editAccountViewModel.getPostalCodeField());
        emailField.textProperty().bindBidirectional(editAccountViewModel.getEmailField());
        telephoneNoField.textProperty().bindBidirectional(editAccountViewModel.getTelephoneNoField());
        otherInfoField.textProperty().bindBidirectional(editAccountViewModel.getOtherInfoField());
        editAccountViewModel.setProfile();


        locationBox.setItems(editAccountViewModel.getLocations());
        locationBox.setValue(editAccountViewModel.getSelectedLocation());

      notifications =  Notifications.create()
                .title("Error - invalid input!")
                .graphic(new Rectangle(300, 300, Color.RED)) // sets node to display
                .hideAfter(Duration.seconds(3));

    }
    public void goBackToProfile(ActionEvent actionEvent) throws IOException, SQLException {
        viewHandler.setView(viewHandler.menu(), viewHandler.manageAccount());
    }

    public void editButton(ActionEvent actionEvent) throws IOException {
        boolean ok = true;
        if(checkField(usernameField) && checkField(passwordField) && checkField(confirmPasswordField) && checkField(streetField) && checkField(streetNumberField) && checkField(postalCodeField)){
            String message = editAccountViewModel.onEditButtonPressed(locationBox.getValue());
            switch (message){
                case "Edit successful":
                    notifications.owner(parent).text("Your account has been successfully edited! ").title(message).showConfirm();
                    Stage stage = (Stage) viewHandler.getStage().getScene().getWindow();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "");
                    alert.setTitle("Confirmation");
                    alert.setHeaderText("Account successfully edited");
                    alert.initOwner(stage);
                    alert.getDialogPane().setContentText("Click ok to return to your profile.");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK)
                    {
                        viewHandler.setView(viewHandler.menu(), viewHandler.manageAccount());
                    }
                    break;
                default:
                    notifications.owner(parent).text(message).showError();
            }
        }
    }

    public void deleteButton(ActionEvent actionEvent) throws SQLException, IOException {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "");
    alert.setTitle("Delete account");
    alert.setHeaderText("Are you sure?");
    alert.getDialogPane().setContentText("Are you sure you want to permanently delete your account?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
        boolean success = editAccountViewModel.deleteAccount();
        if(success){
            Stage stage = (Stage) viewHandler.getStage().getScene().getWindow();
            alert = new Alert(Alert.AlertType.INFORMATION, "");
            alert.setTitle("Confirmation");
            alert.setHeaderText("Account successfully deleted");
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("Click ok to get to welcome page.");

            result = alert.showAndWait();
            if (result.get() == ButtonType.OK)
            {
                viewHandler.setView(viewHandler.menu(), viewHandler.welcomePage());
            }
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
