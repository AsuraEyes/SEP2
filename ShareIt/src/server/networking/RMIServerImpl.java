package server.networking;


import server.model.ServerModelManager;
import shared.networking.RMIServer;
import shared.networking.RemoteObserver;


import java.beans.PropertyChangeListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIServerImpl implements RMIServer
{
  private final ServerModelManager serverModelManager;

  public RMIServerImpl(ServerModelManager serverModelManager) throws RemoteException{
    UnicastRemoteObject.exportObject(this, 0);
    this.serverModelManager = serverModelManager;
  }

  public void startServer() throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
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
        client.update(evt.getNewValue());
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    };
    serverModelManager.addListener("NewMessage", listener);
    //serverModelManager.addListener("NewUser", listener);
  }

  @Override public void unregisterClient(RemoteObserver client)
      throws RemoteException
  {

  }
}
