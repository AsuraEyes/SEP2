package shared.networking;

import shared.transferobjects.*;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

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
  List<Rental> checkSearch(String search) throws RemoteException;
  List<Rental> checkSearchWithFilter(String search,String city, ArrayList<String> selectedCategories)throws RemoteException;
  String checkRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, String username, ArrayList<String> selectedCategories) throws RemoteException;

  ArrayList<City> getCityList() throws RemoteException;

  ArrayList<State> getStateList() throws RemoteException;

  ArrayList<Category> getCategoryList() throws RemoteException;
  ArrayList<Rental> getRentalsList() throws RemoteException;


}
