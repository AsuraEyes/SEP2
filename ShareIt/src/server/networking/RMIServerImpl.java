package server.networking;

import server.model.ServerModelManager;
import shared.networking.RMIServer;
import shared.networking.RemoteObserver;
import shared.transferobjects.*;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RMIServerImpl implements RMIServer
{
  private final ServerModelManager serverModelManager;

  public RMIServerImpl(ServerModelManager serverModelManager) throws RemoteException{
    UnicastRemoteObject.exportObject(this, 0);
    this.serverModelManager = serverModelManager;
  }

  public void startServer() throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1199);
    registry.bind("ShareIt", this);
  }

  /*@Override public void sendMessage(Message msg) throws RemoteException
  {
    messageManager.sendMessage(msg);
  }

  @Override public String getMessages() throws RemoteException
  {
    return messageManager.getMessages();
  }

  @Override public void setUser(UserName userName) throws RemoteException
  {
    messageManager.setUserInTheList(userName);
  }

  @Override public UserName getUser() throws RemoteException
  {
    return null;
  }

  @Override public ArrayList<UserName> getUserList() throws RemoteException
  {
    return messageManager.getUserNamesList();
  }
*/
  @Override public void registerClient(RemoteObserver client)
      throws RemoteException
  {
    PropertyChangeListener listener;
    //PropertyChangeListener finalListener = listener;
    listener = evt ->{
      try
      {
        client.update(evt.getPropertyName(), evt.getNewValue());
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    };
    serverModelManager.addListener("newMessage", listener);
    //serverModelManager.addListener("selectedRental", listener);
    //serverModelManager.addListener("NewUser", listener);
  }

  @Override public void unregisterClient(RemoteObserver client)
      throws RemoteException
  {
  }

  @Override
  public String checkMemberData(String username, String password, String confirmPassword, String email, String phone, String otherInformation, String street, String streetNo, String postalCode, String city) throws RemoteException {
    return serverModelManager.checkMemberData(username, password, confirmPassword, email, otherInformation, phone, street, streetNo, postalCode, city);
  }

  @Override
  public String updateCheckMemberData(String username, String password, String confirmPassword, String email, String phone, String otherInformation, String street, String streetNo, String postalCode, String city) throws IOException {
    return serverModelManager.updateCheckMemberData(username, password, confirmPassword, email, phone, otherInformation, street, streetNo, postalCode, city);
  }

  @Override
  public String checkRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, String username, ArrayList<String> selectedCategories) throws RemoteException {
    return serverModelManager.checkRentalData(name, pictureLink, description, price, otherInformation, stateName, username, selectedCategories);
  }

  @Override
  public String updateCheckRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, int rentalId, ArrayList<String> selectedCategories) throws RemoteException {
    return serverModelManager.updateCheckRentalData(name, pictureLink, description, price, otherInformation, stateName, rentalId, selectedCategories);
  }

  @Override public String addFeedback(double starValue, String feedback, String username1, String username2)
      throws RemoteException
  {
    System.out.println(starValue);
    return serverModelManager.addFeedback(starValue, feedback, username1, username2);
  }

  @Override
  public List<Rental> checkSearch(String search) throws RemoteException
  {
    return serverModelManager.checkSearch(search);
  }
  @Override public List<Rental> checkSearchWithFilter(String search,String city, ArrayList<String> selectedCategories ) throws RemoteException
  {
    return serverModelManager.checkSearchWithFilter(search,city,selectedCategories);
  }

  @Override
  public ArrayList<City> getCityList() {
    return serverModelManager.getCityList();
  }

  @Override
  public ArrayList<State> getStateList() throws RemoteException {
    return serverModelManager.getStateList();
  }

  @Override
  public ArrayList<Category> getCategoryList() throws RemoteException {
    return serverModelManager.getCategoryList();
  }

  @Override public ArrayList<Rental> getRentalsList() throws RemoteException
  {
    return serverModelManager.getRentalsList();
  }

  @Override public Member getMemberById(int id) throws RemoteException
  {
    return serverModelManager.getMemberById(id);
  }

  @Override
  public String checkLogInCredentials(String username, String password) {
    return serverModelManager.checkLogInCredentials(username, password);
  }

  @Override
  public ArrayList<Rental> getRentalsOfMemberList(String username) throws RemoteException {
    return serverModelManager.getRentalsOfMemberList(username);
  }

  @Override
  public Member getMemberByUsername(String memberUsername) {
    return serverModelManager.getMemberByUsername(memberUsername);
  }

  @Override
  public ArrayList<Rating> getAllRatingsOnMember(String memberUsername) {
    return serverModelManager.getAllRatingsOnMember(memberUsername);
  }

  @Override
  public boolean deleteMember(Member member){
    return serverModelManager.deleteMember(member);
  }

  @Override public Rating getRating(String fromUsername, String toUsername)
      throws RemoteException
  {
    return serverModelManager.getRating(fromUsername, toUsername);
  }

  @Override public void updateRating(Rating rating)
  {
    serverModelManager.updateRating(rating);
  }

  @Override public boolean deleteRental(Rental rental)
  {
    return serverModelManager.deleteRental(rental);
  }

  @Override public ArrayList<Message> getAllReceivedMessages(int loggedUserId)
  {
    return serverModelManager.getAllReceivedMessages(loggedUserId);
  }

  @Override public ArrayList<Message> getMessagesFromUser(int loggedUserId,
      int fromUserid) throws RemoteException
  {
    return serverModelManager.getMessagesFromUser(loggedUserId, fromUserid);
  }

  @Override public void sendMessage(Message message)
  {
    serverModelManager.sendMessage(message);
  }

}
