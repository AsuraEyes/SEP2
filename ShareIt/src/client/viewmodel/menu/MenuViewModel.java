package client.viewmodel.menu;

import client.model.state.StateManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MenuViewModel
{
  private StringProperty usernameLabel;
  public MenuViewModel(){
    usernameLabel = new SimpleStringProperty();
  }
  public String checkUserType(){
    return StateManager.getUsertype();
  }
  public StringProperty getUsernameLabel()
  {
    return usernameLabel;
  }
}
