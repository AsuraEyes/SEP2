package client.model;

import shared.transferobjects.*;
import shared.util.Subject;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ShareItModel extends Subject
{
    String checkMemberData(String username, String password, String confirmPassword, String email, String phone, String otherInformation, String street, String streetNo, String postalCode,  String city) throws IOException;
    List<Rental> checkSearch(String search)throws IOException;
    String checkRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, String username, ArrayList<String> selectedCategories) throws IOException;
    List<Rental> checkSearchWithFilter(String search,String city, ArrayList<String> selectedCategories)throws IOException;
    String addFeedback(double starValue, String feedback, String username1, String username2)throws IOException;
    ArrayList<City> getCityList();
    ArrayList<State> getStateList();
    ArrayList<Category> getCategoryList();
    ArrayList<Rental> getRentalsList();
    String checkUserType();
    void getSelectedRental(Rental rental);
    Member getMemberById(int id);
    void getSearchText(String string);

    String checkLogInCredentials(String username, String password);

    ArrayList<Rental> getRentalsOfMemberList(String username);

    void setMemberUsername(String memberUsername);
    String getMemberUsername();

    Member getMemberByUsername(String memberUsername);
}
