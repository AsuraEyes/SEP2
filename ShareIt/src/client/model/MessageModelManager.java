package client.model;

import client.model.state.StateManager;
import client.network.Client;
import shared.transferobjects.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessageModelManager implements MessageModel
{
  private PropertyChangeSupport support;
  private Client client;
  private String searchText;
  private ArrayList<Message> allReceivedMessages;
  private ArrayList<Warning> allWarnings;
  private ArrayList<Report> allReports;

  public MessageModelManager(Client client){
    this.client = client;
    allReceivedMessages = new ArrayList<>();
    allWarnings = new ArrayList<>();
    allReports = new ArrayList<>();
    support = new PropertyChangeSupport(this);
    client.addListener("newMessage", this::onNewMessage);
    client.addListener("newWarning", this::onNewWarning);

  }

  private void onNewWarning(PropertyChangeEvent evt)
  {
    support.firePropertyChange(evt);
  }

  private void onNewMessage(PropertyChangeEvent evt)
  {
    support.firePropertyChange(evt);
  }

  @Override public String addFeedback(double starValue, String feedback,
      String username1, String username2) throws IOException
  {
    return client.addFeedback(starValue, feedback,username1,username2 );
  }

  @Override public String addReport(String feedback, String username1,
      String username2) throws IOException
  {
    return client.addReport(feedback,username1,username2);
  }

  @Override public String getSearchText()
  {
    return searchText;
  }

  @Override public void setSearchText(String text)
  {
    searchText = text;
  }

  @Override public ArrayList<Rating> getAllRatingsOnMember(
      String memberUsername)
  {
    return client.getAllRatingsOnMember(memberUsername);
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

  @Override public ArrayList<Message> getAllReceivedMessages()
  {
    return allReceivedMessages;
  }

  @Override public ArrayList<Warning> getAllWarnings()
  {
    return allWarnings;
  }

  @Override public List<Report> getReportList()
  {
    return client.getReportList();
  }

  @Override public ArrayList<Message> getMessagesFromUser(int loggedUserId,
      int fromUserid)
  {
    return client.getMessagesFromUser(loggedUserId, fromUserid);
  }

  @Override public ArrayList<Warning> getWarnings(String administrator,
      int idTo)
  {
    return client.getWarnings(administrator, idTo);
  }

  @Override public void sendMessage(Message message)
  {
    client.sendMessage(message);
  }

  @Override public void setAllReceivedMessages(String loggedUsername)
  {
    allReceivedMessages = client.getAllReceivedMessages(getMemberByUsername(loggedUsername).getId());

  }

  @Override public void setAllReceivedWarnings()
  {
    allWarnings = client.getWarnings("administrator", getMemberByUsername(
        StateManager.getInstance().getUsername()).getId());

  }

  @Override public void sendWarning(Warning warning)
  {
    client.sendWarning(warning);

  }

  @Override public Member getMemberByUsername(String memberUsername)
  {
    return null;
  }

  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {
    if(propertyName != null)
      support.addPropertyChangeListener(propertyName, listener);
    else
      support.addPropertyChangeListener(listener);
  }
}