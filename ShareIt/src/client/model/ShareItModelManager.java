package client.model;

import client.model.state.MemberState;
import client.model.state.StateManager;
import client.network.Client;
import shared.transferobjects.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ShareItModelManager implements ShareItModel
{
  private PropertyChangeSupport support;
  private Client client;
  public ShareItModelManager(Client client) throws IOException
  {
    this.client = client;
    client.startClient();
    support = new PropertyChangeSupport(this);
    client.addListener("selectedRental", this::onDataValidation);
    client.addListener("dataValidation", this::onDataValidation);
  }

  public void onDataValidation(PropertyChangeEvent evt){
    support.firePropertyChange(evt);
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
  public String checkMemberData(String username, String password, String confirmPassword, String email, String phone, String otherInformation, String street, String streetNo, String postalCode, String city) throws IOException {
    String messageToReturn = client.checkMemberData (username, password, confirmPassword, email, phone, otherInformation, street, streetNo, postalCode, city);
    if(messageToReturn.equals("Adding successful")){
      StateManager.getInstance().setLoginState(new MemberState(username));
    }

    return messageToReturn;
  }

  @Override
  public String checkRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, String username, ArrayList<String> selectedCategories) throws IOException {
    return client.checkRentalData(name, pictureLink,  description,  price, otherInformation,  stateName,  username,  selectedCategories);
  }


  @Override public List<Rental> checkSearchWithFilter(String search,String city, ArrayList<String> selectedCategories ) throws IOException
  {
    return client.checkSearchWithFilter(search,city, selectedCategories );
  }

  @Override public List<Rental> checkSearch(String search) throws IOException
  {
    return client.checkSearch(search);
  }

  @Override
  public ArrayList<City> getCityList() {
    return client.getCityList();
  }

  @Override
  public ArrayList<State> getStateList() {
    return client.getStateList();
  }

  @Override
  public ArrayList<Category> getCategoryList() {
    return client.getCategoryList();
  }

  @Override public ArrayList<Rental> getRentalsList() throws RemoteException
  {
    return client.getRentalsList();
  }

  @Override
  public String checkUserType() {
    return StateManager.getInstance().getUsertype();
  }

  @Override public void getSelectedRental(Rental rental)
  {
    support.firePropertyChange("selectedRental",1,rental);
  }
}
