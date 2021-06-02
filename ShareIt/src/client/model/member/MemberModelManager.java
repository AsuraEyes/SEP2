package client.model.member;

import client.core.ModelFactory;
import client.model.state.AdministratorState;
import client.model.state.MemberState;
import client.model.state.StateManager;
import client.model.state.VisitorState;
import client.network.Client;
import shared.transferobjects.Member;
import shared.transferobjects.Report;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * Member model class that implements methods from its interface.
 * Middle point between Client and ViewModels handling member data
 */
public class MemberModelManager implements MemberModel
{
  private final PropertyChangeSupport support;
  private final Client client;
  private String memberUsername;
  private ArrayList<Report> allReports;
  private Report selectedReport;
  private final ModelFactory modelFactory;

  /**
   * Constructor for MemberModelManager.
   * Listens to the member related properties, fired by the Client. In this case deleteMember property
   * updates the list of rentals stored in RentalModelManager after the deletion of a member's account
   * and its rentals.
   * Instantiates the lists for all reported members as new Arraylist.
   * @param client       the client
   * @param modelFactory the model factory needed to access the other ModelManagers.
   */
  public MemberModelManager(Client client, ModelFactory modelFactory)
  {
    this.modelFactory = modelFactory;
    this.client = client;
    support = new PropertyChangeSupport(this);
    allReports = new ArrayList<>();
    client.addListener("deleteMember", this::onMemberDelete);
  }

  private void onMemberDelete(PropertyChangeEvent evt)
  {
    modelFactory.getRentalModel()
        .updateRentalsAfterMemberDelete((Member) evt.getNewValue());
  }

  @Override public String checkMemberData(String username, String password,
      String confirmPassword, String email, String phone,
      String otherInformation, String street, String streetNo,
      String postalCode, String city)
  {
    String messageToReturn = client
        .checkMemberData(username, password, confirmPassword, email, phone,
            otherInformation, street, streetNo, postalCode, city);
    if (messageToReturn.equals("Adding successful"))
    {
      StateManager.getInstance().setLoginState(new MemberState(username));
    }

    return messageToReturn;
  }

  @Override public String updateCheckMemberData(String username,
      String password, String confirmPassword, String email, String phone,
      String otherInformation, String street, String streetNo,
      String postalCode, String city)
  {
    String messageToReturn = client
        .updateCheckMemberData(username, password, confirmPassword, email,
            phone, otherInformation, street, streetNo, postalCode, city);
    if (messageToReturn.equals("Edit successful"))
    {
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
    return StateManager.getInstance().getUsername();
  }

  @Override public Member getMemberById(int id)
  {
    Member member = client.getMemberById(id);
    support.firePropertyChange("getMember", 1, member);
    return member;
  }

  @Override public String getMemberUsername()
  {
    return memberUsername;
  }

  @Override public void setMemberUsername(String memberUsername)
  {
    this.memberUsername = memberUsername;

  }

  @Override public Member getMemberByUsername(String memberUsername)
  {
    return client.getMemberByUsername(memberUsername);
  }

  @Override public List<Member> getMembersList()
  {
    return client.getMembersList();
  }

  @Override public void setUserType(String userType, String memberUsername)
  {
    switch (userType)
    {
      case "visitor":
        StateManager.getInstance().setLoginState(new VisitorState());
        break;
      case "administrator":
        StateManager.getInstance()
            .setLoginState(new AdministratorState("administrator"));
        break;
      case "member":
        StateManager.getInstance()
            .setLoginState(new MemberState(memberUsername));
        break;
    }

  }

  @Override public List<Member> checkSearchForMember(String value)
  {
    return client.checkSearchForMember(value);
  }

  @Override public void setSelectedReport(String reporterNameLabel,
      String reportedNameLabel)
  {
    for (int i = 0; i < allReports.size(); i++)
    {
      if (allReports.get(i).getUsernameFrom().equals(reporterNameLabel)
          && allReports.get(i).getUsernameTo().equals(reportedNameLabel))
      {
        selectedReport = allReports.get(i);
      }
    }
  }

  @Override public Report getSelectedReport()
  {
    return selectedReport;
  }

  @Override public boolean deleteMember(Member member)
  {
    return client.deleteMember(member);
  }

  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {
    if (propertyName != null)
      support.addPropertyChangeListener(propertyName, listener);
    else
      support.addPropertyChangeListener(listener);
  }

  @Override public List<Report> getReportList()
  {
    return allReports;
  }

  @Override public void setReportList()
  {
    allReports = (ArrayList<Report>) client.getReportList();
  }
}
