package server.model;

import server.model.data_check.DataCheckMember;
import server.model.data_check.DataCheckRating;
import server.model.data_check.DataCheckRental;
import server.model.data_check.DataCheckSearch;
import server.model.database.category.CategoryDAOImpl;
import server.model.database.city.CityDAOImpl;
import server.model.database.member.MemberDAOImpl;
import server.model.database.rating.RatingDAO;
import server.model.database.rating.RatingDAOImpl;
import server.model.database.rental.RentalDAOImpl;
import server.model.database.state.StateDAOImpl;
import shared.transferobjects.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServerModelImpl implements ServerModelManager
{
  private PropertyChangeSupport support;
  private DataCheckMember dataCheckMember;
  private DataCheckMember updateDataCheckMember;
  private DataCheckRental dataCheckRental;
  private DataCheckSearch dataCheckSearch;
  private DataCheckRating dataCheckRating;

  public ServerModelImpl(){
    support = new PropertyChangeSupport(this);
    dataCheckMember = new DataCheckMember();
    dataCheckRental = new DataCheckRental();
    dataCheckSearch = new DataCheckSearch();
    dataCheckRating = new DataCheckRating();
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
  public String checkMemberData(String username, String password, String confirmPassword, String email, String otherInformation, String phone, String street, String streetNo, String postalCode, String city) {
    String message = dataCheckMember.checkData(username, password, confirmPassword, email, otherInformation, phone, street, streetNo, postalCode, city);

    //support.firePropertyChange("dataValidation", 0, message);
    return message;
  }

  @Override
  public String updateCheckMemberData(String username, String password, String confirmPassword, String email, String phone, String otherInformation, String street, String streetNo, String postalCode, String city) throws IOException {
    String message = dataCheckMember.updateCheckData(username, password, confirmPassword, email, otherInformation, phone, street, streetNo, postalCode, city);
    return message;
  }

  @Override
  public String checkRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, String username, ArrayList<String> selectedCategories) {
    return dataCheckRental.checkRentalData(name, pictureLink, description, price, otherInformation, stateName, selectedCategories);
  }

  @Override
  public List<Rental> checkSearchWithFilter(String search, String city, ArrayList<String> selectedCategories) {
    return dataCheckSearch.checkSearchWithFilter(search, city, selectedCategories);
  }

  @Override
  public List<Rental> checkSearch(String search)
  {
    List<Rental> list = dataCheckSearch.checkSearch(search);
    return list;
  }

  @Override public String addFeedback(double starValue, String feedback, String username1, String username2)
  {

    String message = dataCheckRating.addFeedback(starValue, feedback, username1, username2);
    return message;
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

  @Override public ArrayList<Rental> getRentalsList()
  {
    try
    {
      return (ArrayList<Rental>) RentalDAOImpl.getInstance().readRentals();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Member getMemberById(int id)
  {
    try
    {
      return MemberDAOImpl.getInstance().getMemberById(id);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
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

  @Override public Rating getRating(String fromUsername, String toUsername)
  {
    try
    {
      return RatingDAOImpl.getInstance().getRating(fromUsername, toUsername);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override public void updateRating(Rating rating)
  {
    try
    {
      RatingDAOImpl.getInstance().updateRating(rating);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }
}
