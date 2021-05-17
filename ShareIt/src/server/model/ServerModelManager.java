package server.model;

import shared.transferobjects.City;
import shared.util.Subject;

import java.util.ArrayList;

public interface ServerModelManager extends Subject
{
    String checkData(String username, String password, String confirmPassword, String email, String otherInformation, String phone, String street, String streetNo, String postalCode, String city);

    ArrayList<City> getCityList();
  /*void sendMessage(Message msg);
  String getMessages();*/
}
