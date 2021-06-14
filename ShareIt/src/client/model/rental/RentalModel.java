package client.model.rental;

import shared.transferobjects.*;
import shared.util.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * The interface of Rental model.
 * Middle point between Client and ViewModels handling rental data
 */
public interface RentalModel extends Subject
{
  /**
   * Shows all the rentals matched with phrase given from ViewModel.
   * The list is filtered in DataCheckRental and retrieved from database.
   *
   * @param search the search
   * @return the list of rentals
   */
  List<Rental> checkSearch(String search);
  /**
   * Gets data of new Rental from ViewModel, checks it in the DataCheckRental on the server side,
   * and stores it in the database if successful.
   *
   * @param name               the name
   * @param pictureLink        the picture link
   * @param description        the description
   * @param price              the price
   * @param otherInformation   the other information
   * @param stateName          the state name
   * @param selectedCategories the selected categories
   * @return message regarding the creation of the rental, be it successful or not.
   */
  String checkRentalData(String name, String pictureLink, String description,
      String price, String otherInformation, String stateName,
      ArrayList<String> selectedCategories);
  /**
   * Gets data of new Rental from ViewModel, checks it in the DataCheckRental on the server side,
   * and updates it in the database if successful.
   *
   * @param name               the name
   * @param pictureLink        the picture link
   * @param description        the description
   * @param price              the price
   * @param otherInformation   the other information
   * @param stateName          the state name
   * @param selectedCategories the selected categories
   * @return data from Client
   */
  String updateCheckRentalData(String name, String pictureLink,
      String description, String price, String otherInformation,
      String stateName, ArrayList<String> selectedCategories);
  /**
   * Shows all the rentals matched with phrase, city and selected categories given  from ViewModel.
   * The list is filtered in DataCheckRental and retrieved from database.
   *
   * @param search             the search
   * @param city               the city
   * @param selectedCategories the selected categories
   * @return the list of rentals
   */
  List<Rental> checkSearchWithFilter(String search, String city,
      ArrayList<String> selectedCategories);
  /**
   * Gets city list from the database.
   *
   * @return the city list
   */
  ArrayList<City> getCityList();
  /**
   * Gets state list from the database.
   *
   * @return the state list
   */
  ArrayList<State> getStateList();
  /**
   * Gets category list from the database.
   *
   * @return the category list
   */
  ArrayList<Category> getCategoryList();
  /**
   * Gets rentals list from the database.
   *
   * @return the rentals list
   */
  ArrayList<Rental> getRentalsList();
  /**
   * Send selected rental to other views using property change.
   *
   * @param rental the rental
   */
  void sendSelectedRental(Rental rental);
  /**
   * Gets list of rentals for the member from the RentalModelManager;
   *
   * @return the list of rentals of member
   */
  ArrayList<Rental> getRentalsOfMemberList();
  /**
   * It retrieves a list of the member's rentals' ids, looks for the matching ids in the
   * allRentals list from RentalModelManager, and sets the matching rentals in a
   * new list with rentals for the member.
   *
   * @param username the username
   */
  void setAllMemberRentals(String username);
  /**
   * Deletes the rental from the database and the lists from the RentalModelManager.
   *
   * @param rental The rental that is being deleted
   * @return true if data was validated properly
   */
  boolean deleteRental(Rental rental);
  /**
   * Gets selected rental.
   *
   * @return the selected rental
   */
  Rental getSelectedRental();
  /**
   * The selected rental in a ViewModel is set in RentalModelManager
   *
   * @param rental the rental
   */
  void setSelectedRental(Rental rental);
  /**
   * Load all rentals from the database at the beginning of the application.
   */
  void loadRentals();
  /**
   * Deletes rentals after member was deleted.
   *
   * @param member Member that was deleted
   */
  void updateRentalsAfterMemberDelete(Member member);
}
