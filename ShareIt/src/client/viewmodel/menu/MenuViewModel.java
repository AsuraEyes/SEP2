package client.viewmodel.menu;

import client.model.member.MemberModel;
import client.model.message.MessageModel;
import client.model.rental.RentalModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A class that holds and manages data from the Menu view.
 */
public class MenuViewModel
{
  private RentalModel rentalModel;
  private MemberModel memberModel;
  private MessageModel messageModel;

  private StringProperty usernameLabel;
  /**
   * Instantiates StringProperties used for binding with the fields in the controller
   *
   * @param rentalModel The model that this ViewModel uses
   * @param memberModel The model that this ViewModel uses
   * @param messageModel The model that this ViewModel uses
   */
  public MenuViewModel(RentalModel rentalModel, MemberModel memberModel,
      MessageModel messageModel)
  {
    this.rentalModel = rentalModel;
    this.memberModel = memberModel;
    this.messageModel = messageModel;
    usernameLabel = new SimpleStringProperty();

  }
  /**
   * Checks who is logged in.
   *
   * @return  type of account who is logged in, where ("") means Visitor
   */
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
    memberModel.setUserType(userType,memberModel.getLoggedInUsername());
  }
}
