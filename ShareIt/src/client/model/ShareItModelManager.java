package client.model;

import client.model.state.MemberState;
import client.model.state.StateManager;
import client.network.Client;
import shared.transferobjects.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShareItModelManager implements ShareItModel
{
  private PropertyChangeSupport support;
  private Client client;
  private String memberUsername;
  private String searchText;
  private Rental rental;
  private String reporterPerson;
  private String reportedPerson;

  @Override
  public String getMemberUsername() {
    return memberUsername;
  }

  public String getReporterPerson()
  {
    return reporterPerson;
  }

  public String getReportedPerson()
  {
    return reportedPerson;
  }

  @Override
  public void setReporterUsername(String reporterUsername) {
    memberUsername = reporterUsername;
  }

  @Override
  public void setReportedUsername(String reportedUsername) {
    memberUsername = reportedUsername;
  }

  @Override
  public Member getMemberByUsername(String memberUsername) {
    return client.getMemberByUsername(memberUsername);
  }

  @Override
  public void setMemberUsername(String memberUsername) {
    System.out.println("the one in model manager "+memberUsername);
    this.memberUsername = memberUsername;
  }

  @Override public void setUsernames(String reporterNameLabel,
      String reportedNameLabel)
  {
    reporterPerson = reporterNameLabel;
    reportedPerson = reportedNameLabel;
  }

  public ShareItModelManager(Client client) throws IOException
  {
    this.client = client;
    client.startClient();
    support = new PropertyChangeSupport(this);
    client.addListener("newMessage", this::onNewMessage);
    client.addListener("dataValidation", this::onDataValidation);
  }

  private void onNewMessage(PropertyChangeEvent evt)
  {
    support.firePropertyChange(evt);
  }

  public void onDataValidation(PropertyChangeEvent evt){
    support.firePropertyChange(evt);
  }

  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {
    if(propertyName != null)
      support.addPropertyChangeListener(propertyName, listener);
    else
      support.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(String propertyName,
      PropertyChangeListener listener)
  {
    if(propertyName != null)
      support.removePropertyChangeListener(propertyName, listener);
    else
      support.removePropertyChangeListener(listener);
  }

  @Override
  public String checkMemberData(String username, String password, String confirmPassword, String email, String phone, String otherInformation, String street, String streetNo, String postalCode, String city) throws IOException {
    String messageToReturn = client.checkMemberData (username, password, confirmPassword, email, phone, otherInformation, street, streetNo, postalCode, city);
    if(messageToReturn.equals("Adding successful")){
      StateManager.getInstance().setLoginState(new MemberState(username));
    }

    return messageToReturn;
  }

  @Override
  public String updateCheckMemberData(String username, String password, String confirmPassword, String email, String phone, String otherInformation, String street, String streetNo, String postalCode, String city) throws IOException {
    String messageToReturn = client.updateCheckMemberData (username, password, confirmPassword, email, phone, otherInformation, street, streetNo, postalCode, city);
    if(messageToReturn.equals("Edit successful")){
      StateManager.getInstance().setLoginState(new MemberState(username));
    }

    return messageToReturn;
  }

  @Override
  public String checkRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, ArrayList<String> selectedCategories) throws IOException {
    return client.checkRentalData(name, pictureLink,  description,  price, otherInformation,  stateName, getLoggedInUsername(), selectedCategories);
  }

  @Override
  public String updateCheckRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, ArrayList<String> selectedCategories) throws IOException {
    return client.updateCheckRentalData(name, pictureLink,  description,  price, otherInformation,  stateName, getSelectedRental().getId(), selectedCategories);
  }


  @Override public List<Rental> checkSearchWithFilter(String search,String city, ArrayList<String> selectedCategories ) throws IOException
  {
    return client.checkSearchWithFilter(search,city, selectedCategories );
  }
  @Override public String addFeedback(double starValue, String feedback, String username1, String username2) throws IOException
  {
    System.out.println(starValue);
    return client.addFeedback(starValue, feedback,username1,username2 );
  }

  @Override public String addReport(String feedback, String username1,
      String username2) throws IOException
  {
    return client.addReport(feedback,username1,username2);
  }

  @Override public List<Rental> checkSearch(String search) throws IOException
  {
    return client.checkSearch(search);
  }

  @Override
  public ArrayList<City> getCityList() {
    return client.getCityList();
  }

  @Override
  public ArrayList<State> getStateList() {
    return client.getStateList();
  }

  @Override
  public ArrayList<Category> getCategoryList() {
    return client.getCategoryList();
  }

  @Override public ArrayList<Rental> getRentalsList()
  {
    return client.getRentalsList();
  }

  @Override
  public String checkUserType() {
    return StateManager.getInstance().getUsertype();
  }

  @Override
  public String getLoggedInUsername(){
    return StateManager.getInstance().getUsername();
  }

  @Override
  public String checkLogInCredentials(String username, String password){
    return client.checkLogInCredentials(username, password);
  }

  @Override
  public ArrayList<Rental> getRentalsOfMemberList(String username) {
    return client.getRentalsOfMemberList(username);
  }

  @Override
  public void sendSelectedRental(Rental rental)
  {
    support.firePropertyChange("selectedRental",1,rental);
  }

  @Override public Member getMemberById(int id)
  {
    Member member = client.getMemberById(id);
    support.firePropertyChange("getMember",1,member);
    return member;
  }

  @Override public String getSearchText()
  {
    System.out.println("Model: " + searchText);
    return searchText;
  }

  @Override public void setSearchText(String text)
  {
    searchText = text;
  }

  @Override
  public ArrayList<Rating> getAllRatingsOnMember(String memberUsername) {
    return client.getAllRatingsOnMember(memberUsername);
  }

  @Override
  public boolean deleteMember(Member member) {
    return client.deleteMember(member);
  }

  @Override public Rating getRating(String fromUsername, String toUsername)
  {
    return client.getRating(fromUsername, toUsername);
  }

  @Override public Report getReport(String fromUsername, String toUsername)
  {
    return client.getReport(fromUsername, toUsername);
  }

  @Override public void updateRating(Rating rating)
  {
    client.updateRating(rating);
  }

  @Override public void updateReport(Report report)
  {
    client.updateReport(report);
  }

  @Override public boolean deleteRental(Rental rental)
  {
    return client.deleteRental(rental);
  }

  @Override public void setSelectedRental(Rental rental)
  {
    this.rental = rental;
  }

  @Override public Rental getSelectedRental()
  {
    System.out.println(rental.getSelectedCategories());
    return rental;
  }

  @Override public ArrayList<Message> getAllReceivedMessages(String loggedUsername)
  {
    getMemberByUsername(loggedUsername);
    return client.getAllReceivedMessages(getMemberByUsername(loggedUsername).getId());
  }

  @Override public ArrayList<Message> getMessagesFromUser(int loggedUserId,
      int fromUserid)
  {
    return client.getMessagesFromUser(loggedUserId, fromUserid);
  }

  @Override public void sendMessage(Message message)
  {
    client.sendMessage(message);
  }

  @Override
  public List<Member> checkSearchForMember(String value) {
    return client.checkSearchForMember(value);
  }

  @Override
  public List<Member> getMembersList() {
    return client.getMembersList();
  }

  @Override
  public List<Report> getReportList() { return client.getReportList(); }

}
