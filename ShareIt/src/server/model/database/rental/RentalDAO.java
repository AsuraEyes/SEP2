package server.model.database.rental;

import shared.transferobjects.Rental;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RentalDAO {
    /**
     * Creates new rental by putting data provided by user into database
     * @param name name of the rental input by user while creating new rental offer
     * @param pictureLink the picture link of the rental
     * @param description description of the rental input by user while creating new rental offer
     * @param price price of the rental input by user while creating new rental offer
     * @param otherInformation other information of the rental input by user while creating new rental offer
     * @param stateName state of the rental chosen from the list of possible states by user while creating new rental offer
     * @param username username of the member who owns the rental
     * @param selectedCategories categories of the rental chosen from the list of possible categories by user while creating new rental offer
     * @return returns new object of Rental with data which was provided by user while creating new rental offer
     * @throws SQLException
     */
    Rental create(String name, String pictureLink, String description, int price, String otherInformation, String stateName, String username, ArrayList<String> selectedCategories) throws SQLException;


    /**
     *
     * @param name
     * @return
     * @throws SQLException
     */
    List<Rental> readByName(String name) throws SQLException;
    /**
     * Checks all the rentals with given value
     * @param search
     * @return returns list of rentals where value matches with their name or description
     * @throws SQLException
     */
    List<Rental> readBySearch(String search) throws SQLException;
    /**
     * Checks all the rentals with given values
     * @param search search is a phrase that was input by user in order to get rentals matched with it
     * @param city city that member who posted rental should be from
     * @param categories categories that searched for rental should be in
     * @return returns list of rentals where values input matches with rentals values
     * @throws SQLException
     */
    List<Rental> readBySearchAndFilter(String search, String city, ArrayList<String> categories) throws SQLException;
    /**
     * Updates rental data based on data input provided by user
     * @param rental rental data that was changed
     * @throws SQLException
     */
    void update(Rental rental) throws SQLException;
    /**
     * Deletes rental from the database
     * @param rental rental object that will be deleted
     * @throws SQLException
     * @return
     */
    boolean delete(Rental rental) throws SQLException;
    /**
     *
     * @return
     * @throws SQLException
     */
    int getNextAvailableId() throws SQLException;
    /**
     * Gets all the possible rentals
     * @return list of all rentals that are stored in the database
     * @throws SQLException
     */
    List<Rental> readRentals()
        throws SQLException;
    /**
     * Gets all rentals connected to the username
     * @param username username that all rentals will be connected with
     * @return list of rentals that are matching with members username
     */
    ArrayList<Rental> getRentalsOfMemberList(String username);

}
