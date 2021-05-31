package client.viewmodel.menu;

import client.model.member.MemberModel;
import client.model.message.MessageModel;
import client.model.rental.RentalModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MenuViewModel
{
  private RentalModel rentalModel;
  private MemberModel memberModel;
  private MessageModel messageModel;

  private StringProperty usernameLabel;

  public MenuViewModel(RentalModel rentalModel, MemberModel memberModel,
      MessageModel messageModel)
  {
    this.rentalModel = rentalModel;
    this.memberModel = memberModel;
    this.messageModel = messageModel;
    usernameLabel = new SimpleStringProperty();

  }

  public String checkUserType()
  {
    String userType = memberModel.checkUserType();
    if (userType.equals("Member"))
    {
      usernameLabel
          .setValue("Logged in as: " + memberModel.getLoggedInUsername());
    }
    else if (userType.equals("Administrator"))
    {
      usernameLabel
          .setValue("Administrator: " + memberModel.getLoggedInUsername());
    }
    else
      usernameLabel.setValue("");
    return userType;
  }

  public StringProperty getUsernameLabel()
  {
    return usernameLabel;
  }

  public void setMemberUsername()
  {
    memberModel.setMemberUsername(usernameLabel.getValue());
  }

  public void loadAllReceivedMessages()
  {
    messageModel.setAllReceivedMessages(memberModel.getLoggedInUsername());
  }

  public void loadAllWarnings()
  {
    messageModel.setAllReceivedWarnings();
  }

  public void loadAllReportedMembers()
  {
    memberModel.setReportList();
  }

  public void setMemberRentals()
  {
    rentalModel.setAllMemberRentals(memberModel.getLoggedInUsername());
  }

  public void setUserType(String userType)
  {
    memberModel.setUserType(userType);
  }
}
