package client.viewmodel.menu;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MenuViewModel
{
  private StringProperty usernameLabel;
  public MenuViewModel(){
    usernameLabel = new SimpleStringProperty();
  }

  public String checkUserType(){
    return "Administrator";
  }

  public StringProperty getUsernameLabel()
  {
    return usernameLabel;
  }
}
