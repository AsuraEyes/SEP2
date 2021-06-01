package client.viewmodel.log_in;

import client.model.member.MemberModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * A class that holds and manages data from the LogIn view.
 */
public class LogInViewModel
{

  private StringProperty userName;
  private StringProperty searchField;
  private StringProperty passwordField;
  private MemberModel memberModel;
  /**
   * Instantiates a new LogInViewModel.
   *
   * @param shareItModel The model that this ViewModel uses
   */
  public LogInViewModel(MemberModel memberModel)
  {

    this.memberModel = memberModel;

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
  public String onLogInButton()
  {
    return memberModel
        .checkLogInCredentials(userName.getValue(), passwordField.getValue());
  }

  public void setUserType(String userType){
    memberModel.setUserType(userType,userName.getValue());
  }
}
