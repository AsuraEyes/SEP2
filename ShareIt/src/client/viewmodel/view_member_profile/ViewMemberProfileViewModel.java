package client.viewmodel.view_member_profile;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewMemberProfileViewModel
{
  private final SimpleStringProperty searchField;
  private final SimpleStringProperty usernameLabel;
  private final SimpleStringProperty locationLabel;
  private final SimpleStringProperty ratingLabel;
  private final SimpleStringProperty addressLabel;
  private final SimpleStringProperty contactLabel;
  private final SimpleStringProperty otherInformationLabel;

  public ViewMemberProfileViewModel()
  {
    searchField = new SimpleStringProperty();
    usernameLabel = new SimpleStringProperty();
    locationLabel = new SimpleStringProperty();
    ratingLabel = new SimpleStringProperty();
    addressLabel = new SimpleStringProperty();
    contactLabel = new SimpleStringProperty();
    otherInformationLabel = new SimpleStringProperty();
  }

  public StringProperty getSearchField()
  {
    return searchField;
  }

  public StringProperty getUsernameLabel()
  {
    return usernameLabel;
  }

  public StringProperty getLocationLabel()
  {
    return locationLabel;
  }

  public StringProperty getRatingLabel()
  {
    return ratingLabel;
  }

  public StringProperty getAddressLabel()
  {
    return addressLabel;
  }

  public StringProperty getContactLabel()
  {
    return contactLabel;
  }

  public StringProperty getOtherInformationLabel()
  {
    return otherInformationLabel;
  }

}
