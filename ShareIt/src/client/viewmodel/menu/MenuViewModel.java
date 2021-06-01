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
   * Instantiates a new MenuViewModel.
   *
   * @param model The model that this ViewModel uses
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
   * @return returns type of account who is logged in, where ("") means Visitor
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
  /**
   * Gets usernameLabel.
   *
   * @return returns usernameLabel
   */
  public StringProperty getUsernameLabel()
  {
    return usernameLabel;
  }
  /**
   * Sets member username.
   */
  public void setMemberUsername()
  {
    memberModel.setMemberUsername(usernameLabel.getValue());
  }
  /**
   * Loads all received messages.
   */
  public void loadAllReceivedMessages()
  {
    messageModel.setAllReceivedMessages(memberModel.getLoggedInUsername());
  }
  /**
   * Loads all warnings.
   */
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
