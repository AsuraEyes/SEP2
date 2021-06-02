package shared.networking;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The interface of Remote observer.
 */
public interface RemoteObserver extends Remote
{
  /**
   * Gets the name of fired property from the server together with the new value
   *Sends it further to the model
   *
   * @param propertyName the property name
   * @param newValue     the new value
   * @throws RemoteException
   */
  void update(String propertyName, Object newValue) throws RemoteException;
}
