package shared.networking;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The interface of Remote observer.
 */
public interface RemoteObserver extends Remote
{
  /**
   * Updates on every value that has been changed.
   *
   * @param propertyName the property name
   * @param newValue     the new value
   * @throws RemoteException
   */
  void update(String propertyName, Object newValue) throws RemoteException;
}
