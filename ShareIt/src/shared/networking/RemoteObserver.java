package shared.networking;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteObserver extends Remote
{
    void update(Object object) throws RemoteException;
}
