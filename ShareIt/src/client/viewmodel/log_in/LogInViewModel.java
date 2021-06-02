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
   * Instantiates StringProperties used for binding with the fields in the controller
   *
   * @param memberModel The model that this ViewModel uses
   */
  public LogInViewModel(MemberModel memberModel)
  {

    this.memberModel = memberModel;

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
  /**
   * After LogIn button have been pressed this method sends data to the model.
   *
   * @return validation message
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
