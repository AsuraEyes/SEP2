package server.model;

import shared.transferobjects.*;
import shared.util.Subject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ServerModelManager extends Subject
{
    String checkMemberData(String username, String password, String confirmPassword, String email, String otherInformation, String phone, String street, String streetNo, String postalCode, String city);
    List<Rental> checkSearch(String search);
    String checkRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, String username, ArrayList<String> selectedCategories);
    List<Rental> checkSearchWithFilter(String search, String city, ArrayList<String> selectedCategories);
    String addFeedback(double starValue, String feedback, String username1, String username2);
  ArrayList<City> getCityList();
  /*void sendMessage(Message msg);
  String getMessages();*/

    ArrayList<State> getStateList();
    ArrayList<Category> getCategoryList();
    ArrayList<Rental> getRentalsList();
    Member getMemberById(int id);

    String checkLogInCredentials(String username, String password);
}
