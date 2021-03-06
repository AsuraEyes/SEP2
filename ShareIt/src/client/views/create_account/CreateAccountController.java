package client.views.create_account;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.create_account.CreateAccountViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.CustomPasswordField;

import java.util.Optional;
/**
 * A class that manages an interface and handle interactions in CreateAccount view
 */
public class CreateAccountController
{
  @FXML private AnchorPane parent;
  @FXML private TextField usernameField;
  @FXML private PasswordField passwordField;
  @FXML private CustomPasswordField confirmPasswordField;
  @FXML private TextField streetField;
  @FXML private TextField streetNumberField;
  @FXML private TextField postalCodeField;
  @FXML private ChoiceBox<String> locationBox;
  @FXML private TextField emailField;
  @FXML private TextField telephoneNoField;
  @FXML private TextArea otherInfoField;

  private CreateAccountViewModel createAccountViewModel;
  private ViewHandler viewHandler;
  private Notifications notifications;
    /**
     * Init.
     *
     * @param viewHandler      the view handler
     * @param viewModelFactory the view model factory
     */
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    createAccountViewModel = viewModelFactory.getCreateAccountViewModel();
    usernameField.textProperty()
        .bindBidirectional(createAccountViewModel.getUsernameField());
    passwordField.textProperty()
        .bindBidirectional(createAccountViewModel.getPasswordField());
    confirmPasswordField.textProperty()
        .bindBidirectional(createAccountViewModel.getConfirmPasswordField());
    streetField.textProperty()
        .bindBidirectional(createAccountViewModel.getStreetField());
    streetNumberField.textProperty()
        .bindBidirectional(createAccountViewModel.getStreetNumberField());
    postalCodeField.textProperty()
        .bindBidirectional(createAccountViewModel.getPostalCodeField());
    emailField.textProperty()
        .bindBidirectional(createAccountViewModel.getEmailField());
    telephoneNoField.textProperty()
        .bindBidirectional(createAccountViewModel.getTelephoneNoField());
    otherInfoField.textProperty()
        .bindBidirectional(createAccountViewModel.getOtherInfoField());

    locationBox.setItems(createAccountViewModel.getLocations());
    locationBox.getSelectionModel().selectFirst();

    notifications = Notifications.create().title("Error - invalid input!")
        .graphic(new Rectangle(300, 300, Color.RED))
        .hideAfter(Duration.seconds(3));
  }
    /**
     * Changes a view when button was pressed.
     *
     */
  public void goBackToLogInButton()
  {
    usernameField.clear();
    passwordField.clear();
    confirmPasswordField.clear();
    streetField.clear();
    streetNumberField.clear();
    postalCodeField.clear();
    emailField.clear();
    telephoneNoField.clear();
    otherInfoField.clear();
    viewHandler.setView(viewHandler.menu(), viewHandler.logIn());
  }
    /**
     * If data is valid it uses a method from viewModel
     *
     */
  public void createButton()
  {
    if (checkField(usernameField) && checkField(passwordField) && checkField(
        confirmPasswordField) && checkField(streetField) && checkField(
        streetNumberField) && checkField(postalCodeField))
    {
      String message = createAccountViewModel
          .onCreateButtonPressed(locationBox.getValue());
      switch (message)
      {
        case "Adding successful":
          Stage stage = (Stage) viewHandler.getStage().getScene().getWindow();
          Alert alert = new Alert(Alert.AlertType.INFORMATION, "");
          alert.setTitle("Confirmation");
          alert.setHeaderText("Account successfully created");
          alert.initOwner(stage);
          alert.getDialogPane()
              .setContentText("Click ok to get to welcome page.");

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
    /**
     * Validates data
     * @param nameOfField name of field
     * @return true if data is valid, false if not
     */
  private boolean checkField(TextField nameOfField)
  {
    if (nameOfField.textProperty().getValue() == null || nameOfField
        .textProperty().getValue().isBlank())
    {
      notifications.owner(parent)
          .text(nameOfField.getPromptText() + " cannot be empty").showError();
      return false;
    }
    return true;
  }

}
