package client.model.member;

import client.model.state.MemberState;
import client.model.state.StateManager;
import client.network.Client;
import shared.transferobjects.Member;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.List;

public class MemberModelManager implements MemberModel
{
  private PropertyChangeSupport support;
  private Client client;
  private String memberUsername;
  private String reporterPerson;
  private String reportedPerson;

  public MemberModelManager(Client client) throws IOException
  {
    this.client = client;
    client.startClient();
    support = new PropertyChangeSupport(this);
  }
  @Override public String checkMemberData(String username, String password,
      String confirmPassword, String email, String phone,
      String otherInformation, String street, String streetNo,
      String postalCode, String city) throws IOException
  {
    String messageToReturn = client.checkMemberData (username, password, confirmPassword, email, phone, otherInformation, street, streetNo, postalCode, city);
    if(messageToReturn.equals("Adding successful")){
      StateManager.getInstance().setLoginState(new MemberState(username));
    }

    return messageToReturn;
  }

  @Override public String updateCheckMemberData(String username,
      String password, String confirmPassword, String email, String phone,
      String otherInformation, String street, String streetNo,
      String postalCode, String city) throws IOException
  {
    String messageToReturn = client.updateCheckMemberData (username, password, confirmPassword, email, phone, otherInformation, street, streetNo, postalCode, city);
    if(messageToReturn.equals("Edit successful")){
      StateManager.getInstance().setLoginState(new MemberState(username));
    }
    return messageToReturn;
  }

  @Override public String checkUserType()
  {
    return StateManager.getInstance().getUsertype();
  }

  @Override public String checkLogInCredentials(String username,
      String password)
  {
    return client.checkLogInCredentials(username, password);
  }

  @Override public String getLoggedInUsername()
  {
    return StateManager.getInstance().getUsername();  }

  @Override public Member getMemberById(int id)
  {
    Member member = client.getMemberById(id);
    support.firePropertyChange("getMember",1,member);
    return member;
  }

  @Override public void setMemberUsername(String memberUsername)
  {
    this.memberUsername = memberUsername;

  }

  @Override public String getMemberUsername()
  {
    return memberUsername;
  }

  @Override public Member getMemberByUsername(String memberUsername)
  {
    return client.getMemberByUsername(memberUsername);
  }

  @Override public List<Member> getMembersList()
  {
    return client.getMembersList();
  }

  @Override public List<Member> checkSearchForMember(String value)
  {
    return client.checkSearchForMember(value);
  }

  @Override public void setUsernames(String reporterNameLabel,
      String reportedNameLabel)
  {
    reporterPerson = reporterNameLabel;
    reportedPerson = reportedNameLabel;
  }

  @Override public String getReporterPerson()
  {
    return reporterPerson;
  }

  @Override public String getReportedPerson()
  {
    return reportedPerson;
  }

  @Override public void setReporterUsername(String reporterUsername)
  {
    this.reporterPerson = reporterUsername;
  }

  @Override public void setReportedUsername(String reportedUsername)
  {
    this.reportedPerson = reportedUsername;
  }

  @Override public boolean deleteMember(Member member)
  {
    return client.deleteMember(member);  }

  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {
    if(propertyName != null)
      support.addPropertyChangeListener(propertyName, listener);
    else
      support.addPropertyChangeListener(listener);
  }
}
