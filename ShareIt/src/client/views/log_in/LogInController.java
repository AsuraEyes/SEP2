package client.views.log_in;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.log_in.LogInViewModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.CustomPasswordField;

import java.io.IOException;

public class LogInController
{
  public TextField searchField;
  public TextField userNameField;
  public Label errorLabel;
  public CustomPasswordField passwordField;

  private LogInViewModel logInViewModel;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    logInViewModel = viewModelFactory.getLogInViewModel();
    userNameField.textProperty().bindBidirectional(logInViewModel.getUserName());
    errorLabel.textProperty().bind(logInViewModel.getErrorLabel());
    searchField.textProperty().bindBidirectional(logInViewModel.getSearchField());
    passwordField.textProperty().bindBidirectional(logInViewModel.getPasswordField());
  }

  public void searchButton(ActionEvent actionEvent)
  {

  }

  public void logInButton(ActionEvent actionEvent)
  {

  }

  public void createNewAccount(ActionEvent actionEvent) throws IOException
  {
    viewHandler.setView(viewHandler.menu(), viewHandler.createAccount());
  }
}
