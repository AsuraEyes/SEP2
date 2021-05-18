package server.model;

import shared.transferobjects.Category;
import shared.transferobjects.City;
import shared.transferobjects.Member;
import shared.transferobjects.State;
import shared.util.Subject;

import java.util.ArrayList;

public interface ServerModelManager extends Subject
{
    String checkData(String username, String password, String confirmPassword, String email, String otherInformation, String phone, String street, String streetNo, String postalCode, String city);
    String checkSearch(String search);
    void checkRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, Member member);

    ArrayList<City> getCityList();
  /*void sendMessage(Message msg);
  String getMessages();*/

    ArrayList<State> getStateList();
    ArrayList<Category> getCategoryList();
}
