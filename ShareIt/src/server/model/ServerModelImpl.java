package server.model;

import server.model.data_check.DataCheckMember;
import server.model.database.city.CityDAOImpl;
import server.model.database.state.StateDAOImpl;
import shared.transferobjects.City;
import shared.transferobjects.State;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServerModelImpl implements ServerModelManager
{
  private PropertyChangeSupport support;
  private DataCheckMember dataCheckMember;

  public ServerModelImpl(){
    support = new PropertyChangeSupport(this);
    dataCheckMember = new DataCheckMember();
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
  public void checkData(String username, String password, String confirmPassword, String email, String otherInformation, String phone, String street, String streetNo, String postalCode, String city) {
    dataCheckMember.checkData(username, password, confirmPassword, email, otherInformation, phone, street, streetNo, postalCode, city);
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
}
