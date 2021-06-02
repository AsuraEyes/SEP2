package client.model.rental;

import shared.transferobjects.*;
import shared.util.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * The interface of Rental model.
 * Middle point between Client and ViewModels handling member data
 */
public interface RentalModel extends Subject
{
  /**
   * Shows all the rentals matched with phrase given from ViewModel and sends it to the CLient.
   *
   * @param search the search
   * @return returns the list of rentals
   */
  List<Rental> checkSearch(String search);
  /**
   * Gets data of new Rental from ViewModel and sends it to the Client.
   *
   * @param name               the name
   * @param pictureLink        the picture link
   * @param description        the description
   * @param price              the price
   * @param otherInformation   the other information
   * @param stateName          the state name
   * @param selectedCategories the selected categories
   * @return returns data from Client
   */
  String checkRentalData(String name, String pictureLink, String description,
      String price, String otherInformation, String stateName,
      ArrayList<String> selectedCategories);
  /**
   * Gets updated member data from ViewModel and sends it to the Client.
   *
   * @param name               the name
   * @param pictureLink        the picture link
   * @param description        the description
   * @param price              the price
   * @param otherInformation   the other information
   * @param stateName          the state name
   * @param selectedCategories the selected categories
   * @return returns data from Client
   */
  String updateCheckRentalData(String name, String pictureLink,
      String description, String price, String otherInformation,
      String stateName, ArrayList<String> selectedCategories);
  /**
   * Shows all the rentals matched with phrase, city and selected categories given  from ViewModel and sends it to the CLient.
   *
   * @param search             the search
   * @param city               the city
   * @param selectedCategories the selected categories
   * @return returns the list of rentals
   */
  List<Rental> checkSearchWithFilter(String search, String city,
      ArrayList<String> selectedCategories);
  /**
   * Gets city list from the Client.
   *
   * @return the city list
   */
  ArrayList<City> getCityList();
  /**
   * Gets state list from the Client.
   *
   * @return the state list
   */
  ArrayList<State> getStateList();
  /**
   * Gets category list from the Client.
   *
   * @return the category list
   */
  ArrayList<Category> getCategoryList();
  /**
   * Gets rentals list.
   *
   * @return the rentals list
   */
  ArrayList<Rental> getRentalsList();
  /**
   * Send selected rental using property changer.
   *
   * @param rental the rental
   */
  void sendSelectedRental(Rental rental);
  /**
   * Gets rentals of member list.
   *
   * @return the rentals of member list
   */
  ArrayList<Rental> getRentalsOfMemberList();
  /**
   * Sets all member rentals.
   *
   * @param username the username
   */
  void setAllMemberRentals(String username);
  /**
   * Checks rental data before deleting by sending it to the Client.
   *
   * @param rental The rental that is being deleted
   * @return returns true if data was validated properly
   */
  boolean deleteRental(Rental rental);
  /**
   * Gets selected rental.
   *
   * @return the selected rental
   */
  Rental getSelectedRental();
  /**
   * Sets selected rental.
   *
   * @param rental the rental
   */
  void setSelectedRental(Rental rental);
  /**
   * Load rentals from Client.
   */
  void loadRentals();
  /**
   * Deletes rentals after member was deleted.
   *
   * @param member Member that was deleted
   */
  void updateRentalsAfterMemberDelete(Member member);
}
