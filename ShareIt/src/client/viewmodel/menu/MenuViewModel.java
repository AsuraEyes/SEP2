package client.viewmodel.menu;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A class that holds and manages data from the Menu view.
 */
public class MenuViewModel
{
  private ShareItModel model;
  private StringProperty usernameLabel;

  /**
   * Instantiates a new MenuViewModel.
   *
   * @param model The model that this ViewModel uses
   */
  public MenuViewModel(ShareItModel model){
    usernameLabel = new SimpleStringProperty();
    this.model = model;
  }

  /**
   * Checks who is logged in.
   *
   * @return returns type of account who is logged in, where ("") means Visitor
   */
  public String checkUserType(){
    String userType = model.checkUserType();
    if(userType.equals("Member")){
      usernameLabel.setValue("Logged in as: "+model.getLoggedInUsername());
    }
    else if(userType.equals("Administrator")){
      usernameLabel.setValue("Administrator: "+model.getLoggedInUsername());
    }
    else
      usernameLabel.setValue("");
    return userType;
  }

  /**
   * Gets username of currently logged in person.
   *
   * @return returns username of logged in person
   */
  public String getUsernameLoggedIn(){
    return model.getLoggedInUsername();
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
  public void setMemberUsername(){
    model.setMemberUsername(usernameLabel.getValue());
  }

  /**
   * Loads all received messages.
   */
  public void loadAllReceivedMessages(){
    model.setAllReceivedMessages(model.getLoggedInUsername());
  }

  /**
   * Loads all warnings.
   */
  public void loadAllWarnings() {
    model.setAllReceivedWarnings();
    }
}
