package client.model.rental;

import shared.transferobjects.Category;
import shared.transferobjects.City;
import shared.transferobjects.Rental;
import shared.transferobjects.State;
import shared.util.Subject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface RentalModel extends Subject
{
  List<Rental> checkSearch(String search)throws IOException;
  String checkRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, ArrayList<String> selectedCategories) throws IOException;
  String updateCheckRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, ArrayList<String> selectedCategories) throws IOException;
  List<Rental> checkSearchWithFilter(String search,String city, ArrayList<String> selectedCategories)throws IOException;
  ArrayList<City> getCityList();
  ArrayList<State> getStateList();
  ArrayList<Category> getCategoryList();
  ArrayList<Rental> getRentalsList();
  void sendSelectedRental(Rental rental);
  ArrayList<Rental> getRentalsOfMemberList(String username);
  boolean deleteRental(Rental rental);
  void setSelectedRental(Rental rental);
  Rental getSelectedRental();

}
