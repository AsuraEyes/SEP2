package client.network;


import shared.transferobjects.Category;
import shared.transferobjects.City;
import shared.transferobjects.Member;
import shared.transferobjects.State;
import shared.util.Subject;
import java.io.IOException;
import java.util.ArrayList;

public interface Client extends Subject
{
  void startClient() throws IOException;
  String checkMemberData(String username, String password, String confirmPassword, String email, String phone, String otherInformation, String street, String streetNo, String postalCode, String city) throws IOException;
  String checkSearch(String search) throws IOException;
  String checkRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, Member member) throws IOException;

  ArrayList<City> getCityList();
  ArrayList<State> getStateList();
  ArrayList<Category> getCategoryList();
}
