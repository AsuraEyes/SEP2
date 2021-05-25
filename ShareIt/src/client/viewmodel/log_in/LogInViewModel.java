package client.viewmodel.log_in;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.RemoteException;

public class LogInViewModel
{
  ShareItModel model;
  private final StringProperty userName;
  private final StringProperty errorLabel;
  private final StringProperty searchField;
  private final StringProperty passwordField;

  public LogInViewModel(ShareItModel shareItModel){
    this.model = shareItModel;
    userName = new SimpleStringProperty();
    errorLabel = new SimpleStringProperty();
    searchField = new SimpleStringProperty();
    passwordField = new SimpleStringProperty();

  }

  public StringProperty getErrorLabel()
  {
    return errorLabel;
  }

  public StringProperty getSearchField()
  {
    return searchField;
  }

  public StringProperty getUserName()
  {
    return userName;
  }

  public StringProperty getPasswordField()
  {
    return passwordField;
  }

  public String onLogInButton() throws RemoteException {
    return model.checkLogInCredentials(userName.getValue(), passwordField.getValue());
  }
}
