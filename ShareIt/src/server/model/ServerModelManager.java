package server.model;

import shared.transferobjects.*;
import shared.util.Subject;

import java.util.ArrayList;

public interface ServerModelManager extends Subject
{
    String checkMemberData(String username, String password, String confirmPassword, String email, String otherInformation, String phone, String street, String streetNo, String postalCode, String city);
    String checkSearch(String search);
    String checkRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, String username, ArrayList<String> selectedCategories);

    ArrayList<City> getCityList();
  /*void sendMessage(Message msg);
  String getMessages();*/

    ArrayList<State> getStateList();
    ArrayList<Category> getCategoryList();
    ArrayList<Rental> getRentalsList();
}
