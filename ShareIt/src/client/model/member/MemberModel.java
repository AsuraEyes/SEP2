package client.model.member;

import shared.transferobjects.Member;
import shared.transferobjects.Report;
import shared.util.Subject;

import java.util.List;

/**
 * The interface of Member model.
 */
public interface MemberModel extends Subject
{
  /**
   * Middle point between Client and ViewModels handling member data
   *
   * @param username         the username
   * @param password         the password
   * @param confirmPassword  the confirm password
   * @param email            the email
   * @param phone            the phone
   * @param otherInformation the other information
   * @param street           the street
   * @param streetNo         the street no
   * @param postalCode       the postal code
   * @param city             the city
   * @return data from Client
   */
  String checkMemberData(String username, String password,
      String confirmPassword, String email, String phone,
      String otherInformation, String street, String streetNo,
      String postalCode, String city);
  /**
   * Gets updated member data from ViewModel and sends it to the database.
   *
   * @param username         the username
   * @param password         the password
   * @param confirmPassword  the confirm password
   * @param email            the email
   * @param phone            the phone
   * @param otherInformation the other information
   * @param street           the street
   * @param streetNo         the street no
   * @param postalCode       the postal code
   * @param city             the city
   * @return data from Client
   */
  String updateCheckMemberData(String username, String password,
      String confirmPassword, String email, String phone,
      String otherInformation, String street, String streetNo,
      String postalCode, String city);
  /**
   * Checks user type by getting data from ViewModel.
   *
   * @return String type of user that is using an application
   */
  String checkUserType();
  /**
   * Check logIn credentials by getting data from ViewModel and sends it to the database.
   *
   * @param username the username
   * @param password the password
   * @return data from Client
   */
  String checkLogInCredentials(String username, String password);
  /**
   * Gets logged in username from the StateManager.
   *
   * @return logged in username
   */
  String getLoggedInUsername();

  /**
   * Gets member by id with data from the database.
   *
   * @param id the id
   * @return Member's ID from Client
   */
  Member getMemberById(int id);
  /**
   * Gets member username from MemberModelManager.
   *
   * @return Member's username
   */
  String getMemberUsername();
  /**
   * Sets member username from MemberModelManager.
   *
   * @param memberUsername Member's username
   */
  void setMemberUsername(String memberUsername);
  /**
   * Gets member by username with data from database.
   *
   * @param memberUsername Member's username
   * @return Member object matched with username from Client
   */
  Member getMemberByUsername(String memberUsername);
  /**
   * Gets members list from database.
   *
   * @return the members list
   */
  List<Member> getMembersList();
  /**
   * Sets user type.
   *
   * @param userType       Type of user
   * @param memberUsername Member's username
   */
  void setUserType(String userType, String memberUsername);

  /**
   * Checks search for member input from ViewModel and sends it to the database .
   *
   * @param value the value
   * @return the list of members with data matching the input from the Client
   */
  List<Member> checkSearchForMember(String value);

  /**
   * Sets selected report in the MemberModelManager.
   *
   * @param reporterNameLabel Member that reports
   * @param reportedNameLabel Member that is being reported
   */
  void setSelectedReport(String reporterNameLabel, String reportedNameLabel);
  /**
   * Gets report list from database.
   *
   * @return a list of all the reports
   */
  List<Report> getReportList();
  /**
   * Gets selected report from MemberModelManager.
   *
   * @return selected report
   */
  Report getSelectedReport();

  /**
   * Checks members data before deleting by sending it to the Client and deletes it.
   *
   * @param member Member that is being deleted
   * @return true if data was validated properly
   */
  boolean deleteMember(Member member);

  /**
   * Sets report list in the MemberModelManager.
   */
  void setReportList();
}
