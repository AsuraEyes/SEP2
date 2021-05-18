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
    return model.checkUserType();
  }
  public StringProperty getUsernameLabel()
  {
    return usernameLabel;
  }
}
