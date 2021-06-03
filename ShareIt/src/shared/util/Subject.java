package shared.util;

import java.beans.PropertyChangeListener;

public interface Subject
{
  /**
   * Assign the specified class an observer, which will be able to retrieve the values carried
   * by the observables
   * @param propertyName carries the name of the observable
   * @param listener the observer, in our case is a method
   */
  void addListener(String propertyName, PropertyChangeListener listener);
}
