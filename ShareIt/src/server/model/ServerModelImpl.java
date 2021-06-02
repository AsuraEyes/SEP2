package server.model;

import server.model.data_check.*;
import server.model.database.category.CategoryDAOImpl;
import server.model.database.city.CityDAOImpl;
import server.model.database.member.MemberDAOImpl;
import server.model.database.message.MessageDAOImpl;
import server.model.database.rating.RatingDAOImpl;
import server.model.database.rental.RentalDAOImpl;
import server.model.database.report.ReportDAOImpl;
import server.model.database.state.StateDAOImpl;
import server.model.database.warning.WarningDAOImpl;
import shared.transferobjects.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;


/**
 * ServerModelImpl class that implements methods from its interface.
 */
public class ServerModelImpl implements ServerModelManager
{
  private final PropertyChangeSupport support;
  private final DataCheckMember dataCheckMember;
  private final DataCheckRental dataCheckRental;
  private final DataCheckSearch dataCheckSearch;
  private final DataCheckRating dataCheckRating;
  private final DataCheckReport dataCheckReport;

  /**
   * Instantiates a new Server model.
   */
  public ServerModelImpl()
  {
    support = new PropertyChangeSupport(this);
    dataCheckMember = new DataCheckMember();
    dataCheckRental = new DataCheckRental();
    dataCheckSearch = new DataCheckSearch();
    dataCheckRating = new DataCheckRating();
    dataCheckReport = new DataCheckReport();
  }

  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {
    if (propertyName != null)
      support.addPropertyChangeListener(propertyName, listener);
    else
      support.addPropertyChangeListener(listener);
  }

  @Override public String checkMemberData(String username, String password,
      String confirmPassword, String email, String otherInformation,
      String phone, String street, String streetNo, String postalCode,
      String city)
  {
    return dataCheckMember
        .checkData(username, password, confirmPassword, email, otherInformation,
            phone, street, streetNo, postalCode, city);
  }

  @Override public String updateCheckMemberData(String username,
      String password, String confirmPassword, String email, String phone,
      String otherInformation, String street, String streetNo,
      String postalCode, String city)
  {
    return dataCheckMember
        .updateCheckData(username, password, confirmPassword, email,
            otherInformation, phone, street, streetNo, postalCode, city);
  }

  @Override public String checkRentalData(String name, String pictureLink,
      String description, String price, String otherInformation,
      String stateName, String username, ArrayList<String> selectedCategories)

  {
    String message = dataCheckRental
        .checkRentalData(name, pictureLink, description, price,
            otherInformation, stateName, username, selectedCategories);
    if (message.equals("Adding successful"))
    {
      support.firePropertyChange("newRental", 0,
          RentalDAOImpl.getInstance().getLastRental());
    }
    return message;
  }

  @Override public String updateCheckRentalData(String name, String pictureLink,
      String description, String price, String otherInformation,
      String stateName, int rentalId, ArrayList<String> selectedCategories)
  {
    String message = dataCheckRental
        .updateCheckRentalData(name, pictureLink, description, price,
            otherInformation, stateName, rentalId, selectedCategories);
    if (message.equals(""))
    {
      support.firePropertyChange("updateRental", 0,
          RentalDAOImpl.getInstance().getRentalById(rentalId));
    }
    return message;
  }

  @Override public List<Rental> checkSearchWithFilter(String search,
      String city, ArrayList<String> selectedCategories)
  {
    return dataCheckSearch
        .checkSearchWithFilter(search, city, selectedCategories);
  }

  @Override public List<Rental> checkSearch(String search)
  {
    return dataCheckSearch.checkSearch(search);
  }

  @Override public String addFeedback(double starValue, String feedback,
      String username1, String username2)
  {
    return dataCheckRating
        .addFeedback(starValue, feedback, username1, username2);
  }

  @Override public String addReport(String feedback, String username1,
      String username2)
  {
    return dataCheckReport.addReport(feedback, username1, username2);
  }

  @Override public ArrayList<City> getCityList()
  {
    return (ArrayList<City>) CityDAOImpl.getInstance().readCity();
  }

  @Override public ArrayList<State> getStateList()
  {
    return (ArrayList<State>) StateDAOImpl.getInstance().readState();
  }

  @Override public ArrayList<Category> getCategoryList()
  {
    return (ArrayList<Category>) CategoryDAOImpl.getInstance().readCategory();
  }

  @Override public ArrayList<Rental> getRentalsList()
  {
    return (ArrayList<Rental>) RentalDAOImpl.getInstance().readRentals();
  }

  @Override public Member getMemberById(int id)
  {
    return MemberDAOImpl.getInstance().getMemberById(id);
  }

  @Override public String checkLogInCredentials(String username,
      String password)
  {
    return MemberDAOImpl.getInstance()
        .checkLogInCredentials(username, password);
  }

  @Override public ArrayList<Integer> getRentalsOfMemberList(String username)
  {
    return RentalDAOImpl.getInstance().getRentalsOfMemberList(username);
  }

  @Override public Member getMemberByUsername(String memberUsername)
  {
    return MemberDAOImpl.getInstance().getMemberByUsername(memberUsername);
  }

  @Override public ArrayList<Rating> getAllRatingsOnMember(
      String memberUsername)
  {
    return RatingDAOImpl.getInstance().getAllRatingsOnMember(memberUsername);
  }

  @Override public boolean deleteMember(Member member)
  {
    if (MemberDAOImpl.getInstance().delete(member))
    {
      support.firePropertyChange("deleteMember", 0, member);
      return true;
    }
    return false;
  }

  @Override public Rating getRating(String fromUsername, String toUsername)
  {
    return RatingDAOImpl.getInstance().getRating(fromUsername, toUsername);
  }

  @Override public Report getReport(String fromUsername, String toUsername)
  {
    return ReportDAOImpl.getInstance().getReport(fromUsername, toUsername);
  }

  @Override public void updateRating(Rating rating)
  {
    RatingDAOImpl.getInstance().updateRating(rating);
  }

  @Override public void updateReport(Report report)
  {
    ReportDAOImpl.getInstance().updateReport(report);
  }

  @Override public boolean deleteRental(Rental rental)
  {
    if (RentalDAOImpl.getInstance().delete(rental))
    {
      support.firePropertyChange("deleteRental", 0, rental);
      return true;
    }
    return false;
  }

  @Override public List<Member> checkSearchForMember(String value)
  {
    return MemberDAOImpl.getInstance().readByUsername(value);
  }

  @Override public List<Member> getMembersList()
  {
    return MemberDAOImpl.getInstance().readMembers();
  }

  @Override public List<Report> getReportList()
  {
    return ReportDAOImpl.getInstance().readReports();
  }

  @Override public ArrayList<Message> getAllReceivedMessages(int loggedUserId)
  {
    return MessageDAOImpl.getInstance().getAllReceivedMessages(loggedUserId);
  }

  @Override public ArrayList<Message> getMessagesFromUser(int loggedUserId,
      int fromUserid)
  {
    return MessageDAOImpl.getInstance()
        .getMessagesFromUser(loggedUserId, fromUserid);
  }

  @Override public ArrayList<Warning> getWarnings(String administrator,
      int idTo)
  {
    return WarningDAOImpl.getInstance().getWarnings(administrator, idTo);
  }

  @Override public void sendMessage(Message message)
  {
    support.firePropertyChange("newMessage", 0,
        MessageDAOImpl.getInstance().sendMessage(message));
  }

  @Override public void sendWarning(Warning warning)
  {
    support.firePropertyChange("newWarning", 0,
        WarningDAOImpl.getInstance().sendWarning(warning));
  }
}
