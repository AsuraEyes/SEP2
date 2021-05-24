package client.network;


import shared.transferobjects.*;
import shared.util.Subject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface Client extends Subject
{
  void startClient() throws IOException;
  String checkMemberData(String username, String password, String confirmPassword, String email, String phone, String otherInformation, String street, String streetNo, String postalCode, String city) throws IOException;
  String updateCheckMemberData(String username, String password, String confirmPassword, String email, String phone, String otherInformation, String street, String streetNo, String postalCode,  String city) throws IOException;
  List<Rental> checkSearch(String search) throws IOException;
  List<Rental> checkSearchWithFilter(String search,String city,ArrayList<String> selectedCategories) throws IOException;
  String checkRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, String username, ArrayList<String> selectedCategories) throws IOException;
  String updateCheckRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, int rentalId, ArrayList<String> selectedCategories) throws IOException;
  String addFeedback(double starRating, String feedback, String username1, String username2) throws IOException;
  String addReport(String feedback, String username1, String username2) throws IOException;
  ArrayList<City> getCityList();
  ArrayList<State> getStateList();
  ArrayList<Category> getCategoryList();
  ArrayList<Rental> getRentalsList();
  Member getMemberById(int id);

    String checkLogInCredentials(String username, String password);

  ArrayList<Rental> getRentalsOfMemberList(String username);

    Member getMemberByUsername(String memberUsername);

    ArrayList<Rating> getAllRatingsOnMember(String memberUsername);

  boolean deleteMember(Member member);
  Rating getRating(String fromUsername, String toUsername);
  Report getReport(String fromUsername, String toUsername);
  void updateRating(Rating rating);
  void updateReport(Report report);
  boolean deleteRental(Rental rental);

    List<Member> checkSearchForMember(String value);

    List<Member> getMembersList();
}
