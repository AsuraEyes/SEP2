package client.model.rental;

import client.core.ModelFactory;
import client.model.state.StateManager;
import client.network.Client;
import shared.transferobjects.*;

import java.beans.PropertyChangeEvent;
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
  private ArrayList<Rental> allRentals;
  private ArrayList<Rental> allMemberRentals;

  public RentalModelManager(Client client)
  {
    this.client = client;
    allRentals = new ArrayList<>();
    allMemberRentals = new ArrayList<>();
    support = new PropertyChangeSupport(this);
    client.addListener("newRental", this::onNewRental);
    client.addListener("deleteRental", this::onDeleteRental);
    client.addListener("updateRental", this::onUpdateRental);
    loadRentals();
  }

  private void onUpdateRental(PropertyChangeEvent evt)
  {
    Rental updatedRental = (Rental) evt.getNewValue();
    for (int i = 0; i < allRentals.size(); i++)
    {
      if(allRentals.get(i).getId() == updatedRental.getId())
      {
        allRentals.remove(allRentals.get(i));
        allRentals.add(updatedRental);
      }
    }
  }

  private void onDeleteRental(PropertyChangeEvent evt)
  {
    Rental deletedRental = (Rental) evt.getNewValue();
    for (int i = 0; i < allRentals.size(); i++)
    {
      if(allRentals.get(i).getId() == deletedRental.getId())
      {
        allRentals.remove(allRentals.get(i));
      }
    }
  }

  public void onNewRental(PropertyChangeEvent evt){
    allRentals.add((Rental) evt.getNewValue());
  }

  @Override public List<Rental> checkSearch(String search)
  {
    return client.checkSearch(search);
  }

  @Override public String checkRentalData(String name, String pictureLink,
      String description, String price, String otherInformation,
      String stateName, ArrayList<String> selectedCategories)
  {
    return client.checkRentalData(name, pictureLink,  description,  price, otherInformation,  stateName,
        StateManager.getInstance().getUsername(), selectedCategories);
  }

  @Override public String updateCheckRentalData(String name, String pictureLink,
      String description, String price, String otherInformation,
      String stateName, ArrayList<String> selectedCategories)
  {
    return client.updateCheckRentalData(name, pictureLink,  description,  price, otherInformation,  stateName, getSelectedRental().getId(), selectedCategories);
  }

  @Override public List<Rental> checkSearchWithFilter(String search,
      String city, ArrayList<String> selectedCategories)
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
    return allRentals;
  }

  @Override public void sendSelectedRental(Rental rental)
  {
    support.firePropertyChange("selectedRental",1,rental);
  }

  @Override public ArrayList<Rental> getRentalsOfMemberList()
  {
    return allMemberRentals;
  }
  @Override public void setAllMemberRentals(String username){
    allMemberRentals.clear();
    ArrayList<Integer> rentalsId = client.getRentalsOfMemberList(username);
    for (Integer integer : rentalsId) {
      for (Rental allRental : allRentals){
        if (integer == allRental.getId())
          allMemberRentals.add(allRental);
      }
    }
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
  @Override
  public void loadRentals(){
    allRentals = client.getRentalsList();
  }
  @Override
  public void updateRentalsAfterMemberDelete(Member member){
      for (int i = 0; i < allRentals.size(); i++)
      {
        if(member.getId() == allRentals.get(i).getMemberId())
        {
          allRentals.remove(allRentals.get(i));
        }
      }

  }
}
