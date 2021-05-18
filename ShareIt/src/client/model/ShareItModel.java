package client.model;

import shared.transferobjects.*;
import shared.util.Subject;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ShareItModel extends Subject
{
    String checkMemberData(String username, String password, String confirmPassword, String email, String phone, String otherInformation, String street, String streetNo, String postalCode,  String city) throws IOException;
    String checkSearch(String search)throws IOException;
    String checkRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, String username, ArrayList<String> selectedCategories) throws IOException;
    String checkSearchWithFilter(String search,String city, ArrayList<String> selectedCategories)throws IOException;
    ArrayList<City> getCityList();
    ArrayList<State> getStateList();
    ArrayList<Category> getCategoryList();
    ArrayList<Rental> getRentalsList() throws RemoteException;

    String checkUserType();

}
