package client.model.rental;

import client.model.state.StateManager;
import client.network.Client;
import shared.transferobjects.Category;
import shared.transferobjects.City;
import shared.transferobjects.Rental;
import shared.transferobjects.State;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RentalModelManager implements RentalModel
{
  private PropertyChangeSupport support;
  private Client client;
  private Rental rental;

  public RentalModelManager(Client client)
  {
    this.client = client;
    support = new PropertyChangeSupport(this);
  }
  @Override public List<Rental> checkSearch(String search) throws IOException
  {
    return client.checkSearch(search);
  }

  @Override public String checkRentalData(String name, String pictureLink,
      String description, String price, String otherInformation,
      String stateName, ArrayList<String> selectedCategories) throws IOException
  {
    return client.checkRentalData(name, pictureLink,  description,  price, otherInformation,  stateName,
        StateManager.getInstance().getUsername(), selectedCategories);
  }

  @Override public String updateCheckRentalData(String name, String pictureLink,
      String description, String price, String otherInformation,
      String stateName, ArrayList<String> selectedCategories) throws IOException
  {
    return client.updateCheckRentalData(name, pictureLink,  description,  price, otherInformation,  stateName, getSelectedRental().getId(), selectedCategories);
  }

  @Override public List<Rental> checkSearchWithFilter(String search,
      String city, ArrayList<String> selectedCategories) throws IOException
  {
    return client.checkSearchWithFilter(search,city, selectedCategories );
  }

  @Override public ArrayList<City> getCityList()
  {
    return client.getCityList();
  }

  @Override public ArrayList<State> getStateList()
  {
    return client.getStateList();
  }

  @Override public ArrayList<Category> getCategoryList()
  {
    return client.getCategoryList();
  }

  @Override public ArrayList<Rental> getRentalsList()
  {
    return client.getRentalsList();
  }

  @Override public void sendSelectedRental(Rental rental)
  {
    support.firePropertyChange("selectedRental",1,rental);
  }

  @Override public ArrayList<Rental> getRentalsOfMemberList(String username)
  {
    return client.getRentalsOfMemberList(username);
  }

  @Override public boolean deleteRental(Rental rental)
  {
    return client.deleteRental(rental);
  }

  @Override public void setSelectedRental(Rental rental)
  {
    this.rental = rental;
  }

  @Override public Rental getSelectedRental()
  {
    return rental;
  }

  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {
    if(propertyName != null)
      support.addPropertyChangeListener(propertyName, listener);
    else
      support.addPropertyChangeListener(listener);
  }
}
