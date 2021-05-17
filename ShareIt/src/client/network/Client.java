package client.network;


import shared.transferobjects.City;
import shared.util.Subject;
import java.io.IOException;
import java.util.ArrayList;

public interface Client extends Subject
{
  void startClient() throws IOException;
  String checkMemberData(String username, String password, String confirmPassword, String email, String phone, String otherInformation, String street, String streetNo, String postalCode, String city) throws IOException;

  ArrayList<City> getCityList();
}
