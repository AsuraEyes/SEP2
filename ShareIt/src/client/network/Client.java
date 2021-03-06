package client.network;

import shared.transferobjects.*;
import shared.util.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * An interface of Client that transfers data between RMIServer and Models
 */
public interface Client extends Subject
{
  void startClient();
  String checkMemberData(String username, String password,
      String confirmPassword, String email, String phone,
      String otherInformation, String street, String streetNo,
      String postalCode, String city);
  String updateCheckMemberData(String username, String password,
      String confirmPassword, String email, String phone,
      String otherInformation, String street, String streetNo,
      String postalCode, String city);
  List<Rental> checkSearch(String search);
  List<Rental> checkSearchWithFilter(String search, String city,
      ArrayList<String> selectedCategories);
  String checkRentalData(String name, String pictureLink, String description,
      String price, String otherInformation, String stateName, String username,
      ArrayList<String> selectedCategories);
  String updateCheckRentalData(String name, String pictureLink,
      String description, String price, String otherInformation,
      String stateName, int rentalId, ArrayList<String> selectedCategories);
  String addFeedback(double starRating, String feedback, String username1,
      String username2);
  String addReport(String feedback, String username1, String username2);
  ArrayList<City> getCityList();
  ArrayList<State> getStateList();
  ArrayList<Category> getCategoryList();
  ArrayList<Rental> getRentalsList();
  Member getMemberById(int id);

  String checkLogInCredentials(String username, String password);

  ArrayList<Integer> getRentalsOfMemberList(String username);

  Member getMemberByUsername(String memberUsername);

  ArrayList<Rating> getAllRatingsOnMember(String memberUsername);

  boolean deleteMember(Member member);
  Rating getRating(String fromUsername, String toUsername);
  Report getReport(String fromUsername, String toUsername);
  void updateRating(Rating rating);
  void updateReport(Report report);
  boolean deleteRental(Rental rental);
  ArrayList<Message> getAllReceivedMessages(int loggedUserId);
  ArrayList<Message> getMessagesFromUser(int loggedUserId, int fromUserid);
  ArrayList<Warning> getWarnings(String administrator, int idTo);

  void sendMessage(Message message);
  void sendWarning(Warning warning);

  List<Member> checkSearchForMember(String value);

  List<Member> getMembersList();
  List<Report> getReportList();

}
