package client.viewmodel.menu;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MenuViewModel
{
  private ShareItModel model;
  private StringProperty usernameLabel;
  public MenuViewModel(ShareItModel model){
    usernameLabel = new SimpleStringProperty();
    this.model = model;
  }
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
  public String getUsernameLoggedIn(){
    return model.getLoggedInUsername();
  }
  public StringProperty getUsernameLabel()
  {
    return usernameLabel;
  }
  public void setMemberUsername(){
    model.setMemberUsername(usernameLabel.getValue());
  }
  public void loadAllReceivedMessages(){
    model.setAllReceivedMessages(model.getLoggedInUsername());
  }
}
