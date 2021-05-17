/*package client.model;

import client.network.Client;
import client.networking.Client;
import shared.transferObjects.Message;
import shared.transferObjects.UserName;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ChatModelManager implements ChatModel
{
  private PropertyChangeSupport support;
  private Client client;

  public ChatModelManager(Client client) throws IOException
  {
    this.client = client;
    support = new PropertyChangeSupport(this);
    client.startClient();
    client.addListener("NewMessage", this::onNewMessage);
    client.addListener("NewUser", this::onNewUser);
  }

  private void onNewUser(PropertyChangeEvent propertyChangeEvent)
  {
    support.firePropertyChange(propertyChangeEvent);
  }

  private void onNewMessage(PropertyChangeEvent evt){
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

  @Override public String getMessages() throws RemoteException
  {
    return client.getMessages();
  }

  @Override public void sendMessage(String msg) throws RemoteException
  {
    client.sendMessage(new Message(msg, new UserName(getUser().toString())));
  }

  @Override public void setUser(String userName) throws RemoteException
  {
    client.setUser(userName);
  }

  @Override public UserName getUser() throws RemoteException
  {
    return  client.getUser();
  }

  @Override public ArrayList<UserName> getUserList() throws RemoteException
  {
    return client.getUserList();
  }
}
*/