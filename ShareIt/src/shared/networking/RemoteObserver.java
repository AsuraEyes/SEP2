package shared.networking;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteObserver extends Remote
{
  void update(String propertyName, Object newValue) throws RemoteException;
}
