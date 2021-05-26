package client.viewmodel.log_in;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LogInViewModel {
  ShareItModel model;
  private final StringProperty userName;
  private final StringProperty searchField;
  private final StringProperty passwordField;

  public LogInViewModel(ShareItModel shareItModel){
    this.model = shareItModel;
    userName = new SimpleStringProperty();
    searchField = new SimpleStringProperty();
    passwordField = new SimpleStringProperty();
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

  public String onLogInButton() {
    return model.checkLogInCredentials(userName.getValue(), passwordField.getValue());
  }
}
