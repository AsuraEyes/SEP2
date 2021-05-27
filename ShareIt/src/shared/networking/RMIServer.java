package shared.networking;

import shared.transferobjects.*;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RMIServer extends Remote
{
  void startServer() throws RemoteException, AlreadyBoundException;
  void registerClient(RemoteObserver client) throws RemoteException;

  String checkMemberData(String username, String password, String confirmPassword, String email, String phone, String otherInformation, String street, String streetNo, String postalCode, String city) throws RemoteException;
  String updateCheckMemberData(String username, String password, String confirmPassword, String email, String phone, String otherInformation, String street, String streetNo, String postalCode,  String city) throws IOException;
  List<Rental> checkSearch(String search) throws RemoteException;
  List<Rental> checkSearchWithFilter(String search,String city, ArrayList<String> selectedCategories)throws RemoteException;
  String checkRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, String username, ArrayList<String> selectedCategories)
      throws RemoteException, SQLException;
  String updateCheckRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, int rentalId, ArrayList<String> selectedCategories) throws RemoteException;
  String addFeedback(double starValue,String feedback, String username1, String username2 ) throws RemoteException;
  String addReport(String feedback, String username1, String username2 ) throws RemoteException;
  ArrayList<City> getCityList() throws RemoteException;

  ArrayList<State> getStateList() throws RemoteException;

  ArrayList<Category> getCategoryList() throws RemoteException;
  ArrayList<Rental> getRentalsList() throws RemoteException;

  Member getMemberById(int id) throws RemoteException;


    String checkLogInCredentials(String username, String password) throws RemoteException;

  ArrayList<Integer> getRentalsOfMemberList(String username) throws RemoteException;

    Member getMemberByUsername(String memberUsername) throws RemoteException;

    ArrayList<Rating> getAllRatingsOnMember(String memberUsername) throws RemoteException;

    boolean deleteMember(Member member) throws RemoteException;

  Rating getRating(String fromUsername, String toUsername) throws RemoteException;
  Report getReport(String fromUsername, String toUsername) throws RemoteException;
  void updateRating(Rating rating) throws RemoteException;
  void updateReport(Report report) throws RemoteException;
  boolean deleteRental(Rental rental) throws RemoteException;

    List<Member> checkSearchForMember(String value) throws RemoteException;

    List<Member> getMembersList() throws RemoteException;
  ArrayList<Message> getAllReceivedMessages(int loggedUserId) throws RemoteException;
  ArrayList<Message> getMessagesFromUser(int loggedUserId, int fromUserid) throws RemoteException;
  ArrayList<Warning> getWarnings(String administrator, int idTo) throws RemoteException;

  void sendMessage(Message message) throws RemoteException;
  void sendWarning(Warning warning) throws RemoteException;

    List<Report> getReportList() throws RemoteException;


}
