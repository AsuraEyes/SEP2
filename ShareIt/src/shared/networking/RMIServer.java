package shared.networking;

import shared.transferobjects.Category;
import shared.transferobjects.City;
import shared.transferobjects.Member;
import shared.transferobjects.State;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RMIServer extends Remote
{
  void startServer() throws RemoteException, AlreadyBoundException;
  /*void sendMessage(Message msg) throws RemoteException;
  String getMessages() throws RemoteException;
  void setUser(UserName userName) throws RemoteException;
  UserName getUser() throws RemoteException;
  ArrayList<UserName> getUserList() throws RemoteException;*/
  void registerClient(RemoteObserver client) throws RemoteException;
  void unregisterClient(RemoteObserver client) throws RemoteException;

  String checkMemberData(String username, String password, String confirmPassword, String email, String phone, String otherInformation, String street, String streetNo, String postalCode, String city) throws RemoteException;
  String checkSearch(String search) throws RemoteException;
  String checkRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, String username) throws RemoteException;

  ArrayList<City> getCityList() throws RemoteException;

  ArrayList<State> getStateList() throws RemoteException;

  ArrayList<Category> getCategoryList() throws RemoteException;

}