package client.model.member;

import shared.transferobjects.*;
import shared.util.Subject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface MemberModel extends Subject
{
  String checkMemberData(String username, String password, String confirmPassword, String email, String phone, String otherInformation, String street, String streetNo, String postalCode,  String city) throws
      IOException;
  String updateCheckMemberData(String username, String password, String confirmPassword, String email, String phone, String otherInformation, String street, String streetNo, String postalCode,  String city) throws IOException;
  String checkUserType();
  String checkLogInCredentials(String username, String password);
  String getLoggedInUsername();
  Member getMemberById(int id);
  void setMemberUsername(String memberUsername);
  String getMemberUsername();
  Member getMemberByUsername(String memberUsername);
  List<Member> getMembersList();

  List<Member> checkSearchForMember(String value);

  void setUsernames(String reporterNameLabel, String reportedNameLabel);
  String getReporterPerson();
  String getReportedPerson();
  void setReporterUsername(String reporterUsername);
  void setReportedUsername(String reportedUsername);

  boolean deleteMember(Member member);


}
