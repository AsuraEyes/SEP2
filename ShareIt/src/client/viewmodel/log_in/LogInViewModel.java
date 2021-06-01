package client.viewmodel.log_in;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A class that holds and manages data from the LogIn view.
 */
public class LogInViewModel {

  ShareItModel model;
  private final StringProperty userName;
  private final StringProperty searchField;
  private final StringProperty passwordField;

  /**
   * Instantiates a new LogInViewModel.
   *
   * @param shareItModel The model that this ViewModel uses
   */
  public LogInViewModel(ShareItModel shareItModel){
    this.model = shareItModel;
    userName = new SimpleStringProperty();
    searchField = new SimpleStringProperty();
    passwordField = new SimpleStringProperty();
  }

  /**
   * Gets searchField.
   *
   * @return returns searchField input
   */
  public StringProperty getSearchField()
  {
    return searchField;
  }

  /**
   * Gets usernameField.
   *
   * @return returns usernameField input
   */
  public StringProperty getUserName()
  {
    return userName;
  }

  /**
   * Gets passwordField.
   *
   * @return returns passwordField input
   */
  public StringProperty getPasswordField()
  {
    return passwordField;
  }

  /**
   * After LogIn button have been pressed this method sends data to the model.
   *
   * @return (?)
   */
  public String onLogInButton() {
    return model.checkLogInCredentials(userName.getValue(), passwordField.getValue());
  }
}
