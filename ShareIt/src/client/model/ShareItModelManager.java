package client.model;

import client.network.Client;
import shared.transferobjects.City;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ShareItModelManager implements ShareItModel
{
  private PropertyChangeSupport support;
  private Client client;
  public ShareItModelManager(Client client) throws IOException
  {
    this.client = client;
    client.startClient();
    support = new PropertyChangeSupport(this);
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
  public void checkMemberData(String username, String password, String confirmPassword, String email, String phone, String otherInformation, String street, String streetNo, String postalCode, String city) throws IOException {
    client.checkMemberData (username, password, confirmPassword, email, phone, otherInformation, street, streetNo, postalCode, city);
  }

  @Override
  public ArrayList<City> getCityList() {
    return client.getCityList();
  }
}
