package server.model;

import server.model.data_check.DataCheckMember;
import server.model.data_check.DataCheckRental;
import server.model.data_check.DataCheckSearch;
import server.model.database.category.CategoryDAOImpl;
import server.model.database.city.CityDAOImpl;
import server.model.database.state.StateDAOImpl;
import shared.transferobjects.Category;
import shared.transferobjects.City;
import shared.transferobjects.Member;
import shared.transferobjects.State;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServerModelImpl implements ServerModelManager
{
  private PropertyChangeSupport support;
  private DataCheckMember dataCheckMember;
  private DataCheckRental dataCheckRental;
  private DataCheckSearch dataCheckSearch;

  public ServerModelImpl(){
    support = new PropertyChangeSupport(this);
    dataCheckMember = new DataCheckMember();
    dataCheckRental = new DataCheckRental();
    dataCheckSearch = new DataCheckSearch();
  }

  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {
    if(propertyName != null)
      support.addPropertyChangeListener(propertyName, listener);
    else
      support.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(String propertyName,
      PropertyChangeListener listener)
  {
    if(propertyName != null)
      support.removePropertyChangeListener(propertyName, listener);
    else
      support.removePropertyChangeListener(listener);
  }

  @Override
  public String checkMemberData(String username, String password, String confirmPassword, String email, String otherInformation, String phone, String street, String streetNo, String postalCode, String city) {
    String message = dataCheckMember.checkData(username, password, confirmPassword, email, otherInformation, phone, street, streetNo, postalCode, city);

    //support.firePropertyChange("dataValidation", 0, message);
    return message;
  }

  @Override
  public String checkRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, String username, ArrayList<String> selectedCategories) {
    return dataCheckRental.checkRentalData(name, pictureLink, description, price, otherInformation, stateName, username, selectedCategories);
  }

  @Override
  public String checkSearch(String search)
  {
    String message = dataCheckSearch.checkSearch(search);
    return message;
  }

  @Override public String checkSearchWithFilter(String search,String city,ArrayList<String> selectedCategories)
  {
    String message = dataCheckSearch.checkSearchWithFilter(search,city,selectedCategories);
    return message;
  }

  @Override
  public ArrayList<City> getCityList() {
    try {
      return (ArrayList<City>) CityDAOImpl.getInstance().readCity();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public ArrayList<State> getStateList() {
    try {
      return (ArrayList<State>) StateDAOImpl.getInstance().readState();
    }
    catch (SQLException e){
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public ArrayList<Category> getCategoryList() {
    try {
      return (ArrayList<Category>) CategoryDAOImpl.getInstance().readCategory();
    }
    catch (SQLException e){
      e.printStackTrace();
    }
    return null;
  }
}
