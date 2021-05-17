package client.viewmodel.log_in;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LogInViewModel
{
  private final StringProperty userName;
  private final StringProperty errorLabel;
  private final StringProperty searchField;
  private final StringProperty passwordField;

  public LogInViewModel(ShareItModel shareItModel){
    userName = new SimpleStringProperty("Username");
    errorLabel = new SimpleStringProperty();
    searchField = new SimpleStringProperty("Search");
    passwordField = new SimpleStringProperty("Password");
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
}
