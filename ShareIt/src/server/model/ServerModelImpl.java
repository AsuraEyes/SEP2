package server.model;

import server.model.data_check.*;
import server.model.database.category.CategoryDAOImpl;
import server.model.database.message.MessageDAOImpl;
import server.model.database.city.CityDAOImpl;
import server.model.database.member.MemberDAOImpl;
import server.model.database.rating.RatingDAOImpl;
import server.model.database.rental.RentalDAOImpl;
import server.model.database.report.ReportDAOImpl;
import server.model.database.state.StateDAOImpl;
import server.model.database.warning.WarningDAOImpl;
import shared.transferobjects.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServerModelImpl implements ServerModelManager
{
  private PropertyChangeSupport support;
  private DataCheckMember dataCheckMember;
  private DataCheckRental dataCheckRental;
  private DataCheckSearch dataCheckSearch;
  private DataCheckRating dataCheckRating;
  private DataCheckReport dataCheckReport;

  public ServerModelImpl(){
    support = new PropertyChangeSupport(this);
    dataCheckMember = new DataCheckMember();
    dataCheckRental = new DataCheckRental();
    dataCheckSearch = new DataCheckSearch();
    dataCheckRating = new DataCheckRating();
    dataCheckReport = new DataCheckReport();
  }

  @Override public void addListener(String propertyName, PropertyChangeListener listener) {
    if(propertyName != null)
      support.addPropertyChangeListener(propertyName, listener);
    else
      support.addPropertyChangeListener(listener);
  }

  @Override
  public String checkMemberData(String username, String password, String confirmPassword, String email, String otherInformation, String phone, String street, String streetNo, String postalCode, String city) {
    return dataCheckMember.checkData(username, password, confirmPassword, email, otherInformation, phone, street, streetNo, postalCode, city);
  }

  @Override
  public String updateCheckMemberData(String username, String password, String confirmPassword, String email, String phone, String otherInformation, String street, String streetNo, String postalCode, String city) {
    return dataCheckMember.updateCheckData(username, password, confirmPassword, email, otherInformation, phone, street, streetNo, postalCode, city);
  }

  @Override
  public String checkRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, String username, ArrayList<String> selectedCategories) {
    return dataCheckRental.checkRentalData(name, pictureLink, description, price, otherInformation, stateName, username, selectedCategories);
  }

  @Override
  public String updateCheckRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, int rentalId, ArrayList<String> selectedCategories) {
    return dataCheckRental.updateCheckRentalData(name, pictureLink, description, price, otherInformation, stateName, rentalId, selectedCategories);
  }

  @Override
  public List<Rental> checkSearchWithFilter(String search, String city, ArrayList<String> selectedCategories) {
    return dataCheckSearch.checkSearchWithFilter(search, city, selectedCategories);
  }

  @Override
  public List<Rental> checkSearch(String search) {
    return dataCheckSearch.checkSearch(search);
  }

  @Override public String addFeedback(double starValue, String feedback, String username1, String username2) {
    return dataCheckRating.addFeedback(starValue, feedback, username1, username2);
  }

  @Override public String addReport(String feedback, String username1, String username2) {
    return dataCheckReport.addReport(feedback,username1,username2);
  }

  @Override
  public ArrayList<City> getCityList() {
    try {
      return (ArrayList<City>) CityDAOImpl.getInstance().readCity();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public ArrayList<State> getStateList() {
    try {
      return (ArrayList<State>) StateDAOImpl.getInstance().readState();
    }
    catch (SQLException e){
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public ArrayList<Category> getCategoryList() {
    try {
      return (ArrayList<Category>) CategoryDAOImpl.getInstance().readCategory();
    }
    catch (SQLException e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public ArrayList<Rental> getRentalsList() {
    try {
      return (ArrayList<Rental>) RentalDAOImpl.getInstance().readRentals();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Member getMemberById(int id) {
    try {
      return MemberDAOImpl.getInstance().getMemberById(id);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String checkLogInCredentials(String username, String password) {
    try{
      return MemberDAOImpl.getInstance().checkLogInCredentials(username, password);
    }
    catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public ArrayList<Rental> getRentalsOfMemberList(String username) {
    try{
      return RentalDAOImpl.getInstance().getRentalsOfMemberList(username);
    }
    catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public Member getMemberByUsername(String memberUsername) {
    try{
      return MemberDAOImpl.getInstance().getMemberByUsername(memberUsername);
    }
    catch (SQLException e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public ArrayList<Rating> getAllRatingsOnMember(String memberUsername) {
    try{
      return RatingDAOImpl.getInstance().getAllRatingsOnMember(memberUsername);
    }
    catch (SQLException e){
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean deleteMember(Member member) {
    try{
      return MemberDAOImpl.getInstance().delete(member);
    }
    catch (SQLException e){
      e.printStackTrace();
    }
    return false;
  }

  @Override public Rating getRating(String fromUsername, String toUsername) {
    try {
      return RatingDAOImpl.getInstance().getRating(fromUsername, toUsername);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Report getReport(String fromUsername, String toUsername) {
    try {
      return ReportDAOImpl.getInstance().getReport(fromUsername, toUsername);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public void updateRating(Rating rating) {
    try {
      RatingDAOImpl.getInstance().updateRating(rating);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override public void updateReport(Report report) {
    try {
      ReportDAOImpl.getInstance().updateReport(report);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override public boolean deleteRental(Rental rental) {
    try {
      return RentalDAOImpl.getInstance().delete(rental);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public List<Member> checkSearchForMember(String value) {
    try{
      return MemberDAOImpl.getInstance().readByUsername(value);
    }
    catch (SQLException e){
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Member> getMembersList() {
    try{
      return MemberDAOImpl.getInstance().readMembers();
    }
    catch (SQLException e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public List<Report> getReportList() {
    try{
      return ReportDAOImpl.getInstance().readReports();
    }
    catch (SQLException e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public ArrayList<Message> getAllReceivedMessages(int loggedUserId) {
    try {
      return MessageDAOImpl.getInstance().getAllReceivedMessages(loggedUserId);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public ArrayList<Message> getMessagesFromUser(int loggedUserId, int fromUserid) {
    try {
      return MessageDAOImpl
          .getInstance().getMessagesFromUser(loggedUserId, fromUserid);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public ArrayList<Warning> getWarnings(String administrator, int idTo) {
    try {
      return WarningDAOImpl.getInstance().getWarnings(administrator, idTo);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public void sendMessage(Message message) {
    try {
      support.firePropertyChange("newMessage", 0, MessageDAOImpl.getInstance().sendMessage(message));
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override public void sendWarning(Warning warning){
    try {
      support.firePropertyChange("newWarning", 0, WarningDAOImpl.getInstance().sendWarning(warning));
    }
    catch (SQLException e){
      e.printStackTrace();
    }
  }
}
