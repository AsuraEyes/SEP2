package client.model.rental;

import shared.transferobjects.*;
import shared.util.Subject;

import java.util.ArrayList;
import java.util.List;

public interface RentalModel extends Subject
{
  List<Rental> checkSearch(String search);
  String checkRentalData(String name, String pictureLink, String description,
      String price, String otherInformation, String stateName,
      ArrayList<String> selectedCategories);
  String updateCheckRentalData(String name, String pictureLink,
      String description, String price, String otherInformation,
      String stateName, ArrayList<String> selectedCategories);
  List<Rental> checkSearchWithFilter(String search, String city,
      ArrayList<String> selectedCategories);
  ArrayList<City> getCityList();
  ArrayList<State> getStateList();
  ArrayList<Category> getCategoryList();
  ArrayList<Rental> getRentalsList();
  void sendSelectedRental(Rental rental);
  ArrayList<Rental> getRentalsOfMemberList();
  void setAllMemberRentals(String username);
  boolean deleteRental(Rental rental);
  Rental getSelectedRental();
  void setSelectedRental(Rental rental);
  void loadRentals();
  void updateRentalsAfterMemberDelete(Member member);
}
