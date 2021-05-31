package client.model.member;

import shared.transferobjects.Member;
import shared.transferobjects.Report;
import shared.util.Subject;

import java.util.List;

public interface MemberModel extends Subject
{
  String checkMemberData(String username, String password,
      String confirmPassword, String email, String phone,
      String otherInformation, String street, String streetNo,
      String postalCode, String city);
  String updateCheckMemberData(String username, String password,
      String confirmPassword, String email, String phone,
      String otherInformation, String street, String streetNo,
      String postalCode, String city);
  String checkUserType();
  String checkLogInCredentials(String username, String password);
  String getLoggedInUsername();
  Member getMemberById(int id);
  String getMemberUsername();
  void setMemberUsername(String memberUsername);
  Member getMemberByUsername(String memberUsername);
  List<Member> getMembersList();
  void setUserType(String userType, String memberUsername);

  List<Member> checkSearchForMember(String value);

  void setSelectedReport(String reporterNameLabel, String reportedNameLabel);
  List<Report> getReportList();
  Report getSelectedReport();

  boolean deleteMember(Member member);

  void setReportList();
}
