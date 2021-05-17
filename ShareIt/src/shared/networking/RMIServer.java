package shared.networking;

import shared.transferobjects.City;

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

  ArrayList<City> getCityList() throws RemoteException;
}
