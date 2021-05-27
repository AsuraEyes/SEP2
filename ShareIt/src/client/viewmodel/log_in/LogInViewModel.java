package client.viewmodel.log_in;


import client.model.member.MemberModel;
import client.model.message.MessageModel;
import client.model.rental.RentalModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LogInViewModel {
  private RentalModel rentalModel;
  private MemberModel memberModel;
  private MessageModel messageModel;

  private final StringProperty userName;
  private final StringProperty searchField;
  private final StringProperty passwordField;

  public LogInViewModel(RentalModel rentalModel, MemberModel memberModel, MessageModel messageModel){
    this.rentalModel = rentalModel;
    this.memberModel = memberModel;
    this.messageModel = messageModel;

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
    return memberModel.checkLogInCredentials(userName.getValue(), passwordField.getValue());
  }
}
