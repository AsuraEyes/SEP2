package client.viewmodel.log_in;

import client.model.member.MemberModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LogInViewModel
{

  private StringProperty userName;
  private StringProperty searchField;
  private StringProperty passwordField;
  private MemberModel memberModel;

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

  public String onLogInButton()
  {
    return memberModel
        .checkLogInCredentials(userName.getValue(), passwordField.getValue());
  }
  public void setUserType(String userType){
    memberModel.setUserType(userType,userName.getValue());
  }
}
