package client.network;

import client.model.state.StateManager;
import shared.networking.RMIServer;
import shared.networking.RemoteObserver;
import shared.transferobjects.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RMIClient implements Client, RemoteObserver
{
  private PropertyChangeSupport support;
  private RMIServer server;

  public RMIClient(){
    support = new PropertyChangeSupport(this);
  }

  @Override public void startClient()
  {
    try
    {
      UnicastRemoteObject.exportObject(this,0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1199);
      server = (RMIServer) registry.lookup("ShareIt");
      server.registerClient(this);
      System.out.println("Client Connected");
    }
    catch (RemoteException | NotBoundException e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public String checkMemberData(String username, String password, String confirmPassword, String email, String phone, String otherInformation, String street, String streetNo, String postalCode, String city) throws IOException {
    try{
      return server.checkMemberData(username, password, confirmPassword, email, phone, otherInformation, street, streetNo, postalCode, city);
    }
    catch (RemoteException e){
      e.printStackTrace();
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override
  public String updateCheckMemberData(String username, String password, String confirmPassword, String email, String phone, String otherInformation, String street, String streetNo, String postalCode, String city) throws IOException {
    try{
      return server.updateCheckMemberData(username, password, confirmPassword, email, phone, otherInformation, street, streetNo, postalCode, city);
    }
    catch (RemoteException e){
      e.printStackTrace();
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override
  public String checkRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, String username, ArrayList<String> selectedCategories) throws IOException {
    try {
      return server.checkRentalData(name, pictureLink, description, price, otherInformation, stateName, StateManager.getInstance().getUsername(), selectedCategories);
    }
    catch (RemoteException e){
      e.printStackTrace();
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override public String addFeedback(double starValue, String feedback, String username1, String username2) throws IOException
  {
    try
    {

      return server.addFeedback(starValue, feedback, username1, username2);
    }
    catch (RemoteException e){
      e.printStackTrace();
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override public List<Rental> checkSearch(String search) throws IOException
  {
    try{
    return server.checkSearch(search);
  } catch (RemoteException e){
      e.printStackTrace();
      throw new RuntimeException("Could not contact server");
    }
  }
  @Override public List<Rental> checkSearchWithFilter(String search,String city,ArrayList<String> selectedCategories ) throws IOException
  {
    try
    {
      return server.checkSearchWithFilter(search,city,selectedCategories );
    }catch (RemoteException e){
      e.printStackTrace();
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override
  public ArrayList<City> getCityList() {
    try {
      return server.getCityList();
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public ArrayList<State> getStateList() {
    try{
      return server.getStateList();
    }
    catch (RemoteException e){
      e.printStackTrace();
    }
    return  null;
  }

  @Override
  public ArrayList<Category> getCategoryList() {
    try{
      return server.getCategoryList();
    }
    catch (RemoteException e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public ArrayList<Rental> getRentalsList()
  {
    try {
      return server.getRentalsList();
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Member getMemberById(int id)
  {
    try {
      return server.getMemberById(id);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String checkLogInCredentials(String username, String password){
    try {
      return server.checkLogInCredentials(username, password);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public ArrayList<Rental> getRentalsOfMemberList(String username) {
    try{
      return server.getRentalsOfMemberList(username);
    }
    catch (RemoteException e){
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Member getMemberByUsername(String memberUsername) {
    try{
      return server.getMemberByUsername(memberUsername);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public ArrayList<Rating> getAllRatingsOnMember(String memberUsername) {
    try{
      return server.getAllRatingsOnMember(memberUsername);
    }
    catch (Exception e){
      e.printStackTrace();
    }
    return null;
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

  @Override public void update(String propertyName, Object newValue) throws RemoteException
  {
    if(propertyName.equals("dataValidation")){
      support.firePropertyChange(propertyName, 0, newValue);
      support.firePropertyChange("selectedRental", 0, 0);
    }

    /*if(object instanceof Message)
    {
      support.firePropertyChange("NewMessage", null, object);
    }
    else if (object instanceof UserName)
    {
      support.firePropertyChange("NewUser", null, object);
    }*/
  }
}
