package server.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ServerModelImpl implements ServerModelManager
{
  private PropertyChangeSupport support;

  public ServerModelImpl(){
    support = new PropertyChangeSupport(this);
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
}
