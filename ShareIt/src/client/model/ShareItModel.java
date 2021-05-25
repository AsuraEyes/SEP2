package client.model;

import shared.transferobjects.*;
import shared.util.Subject;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ShareItModel extends Subject
{
    String checkMemberData(String username, String password, String confirmPassword, String email, String phone, String otherInformation, String street, String streetNo, String postalCode,  String city) throws IOException;
    String updateCheckMemberData(String username, String password, String confirmPassword, String email, String phone, String otherInformation, String street, String streetNo, String postalCode,  String city) throws IOException;
    List<Rental> checkSearch(String search)throws IOException;
    String checkRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, ArrayList<String> selectedCategories) throws IOException;
    String updateCheckRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, ArrayList<String> selectedCategories) throws IOException;
    List<Rental> checkSearchWithFilter(String search,String city, ArrayList<String> selectedCategories)throws IOException;
    String addFeedback(double starValue, String feedback, String username1, String username2)throws IOException;
    String addReport(String feedback, String username1, String username2) throws IOException;
    ArrayList<City> getCityList();
    ArrayList<State> getStateList();
    ArrayList<Category> getCategoryList();
    ArrayList<Rental> getRentalsList();
    String checkUserType();
    String getLoggedInUsername();
    void sendSelectedRental(Rental rental);
    Member getMemberById(int id);


    String checkLogInCredentials(String username, String password);

    ArrayList<Rental> getRentalsOfMemberList(String username);

    void setMemberUsername(String memberUsername);
    String getMemberUsername();

    Member getMemberByUsername(String memberUsername);
    String getSearchText();
    void setSearchText(String text);

    ArrayList<Rating> getAllRatingsOnMember(String memberUsername);

    boolean deleteMember(Member member);
    Rating getRating(String fromUsername, String toUsername);
    Report getReport(String fromUsername, String toUsername);
    void updateRating(Rating rating);
    void updateReport(Report report);
    boolean deleteRental(Rental rental);
    void setSelectedRental(Rental rental);
    Rental getSelectedRental();

    List<Member> checkSearchForMember(String value);

    List<Member> getMembersList();
    ArrayList<Message> getAllReceivedMessages();
    List<Report> getReportList();
    void setUsernames(String reporterNameLabel, String reportedNameLabel);
    String getReporterPerson();
    String getReportedPerson();
    void setReporterUsername(String reporterUsername);
    void setReportedUsername(String reportedUsername);

//    ArrayList<Message> getAllReceivedMessages(String loggedUsername);
    ArrayList<Message> getMessagesFromUser(int loggedUserId, int fromUserid);
    ArrayList<Warning> getWarnings(String administrator, int idTo);
    void sendMessage(Message message);
    void setAllReceivedMessages(String loggedUsername);

    void sendWarning(Warning warning);


}
