package client.viewmodel.log_in;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LogInViewModel
{
  private StringProperty userName;
  private StringProperty errorLabel;
  private StringProperty searchField;

  public LogInViewModel(){
    userName = new SimpleStringProperty("Username");
    errorLabel = new SimpleStringProperty();
    searchField = new SimpleStringProperty("Search");
  }

  public StringProperty getErrorLabel()
  {
    return errorLabel;
  }

  public StringProperty getSearchField()
  {
    return searchField;
  }

  public StringProperty getUserName()
  {
    return userName;
  }
}
