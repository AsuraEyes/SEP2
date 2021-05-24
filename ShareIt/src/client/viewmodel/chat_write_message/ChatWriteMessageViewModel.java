package client.viewmodel.chat_write_message;

import client.model.ShareItModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobjects.Message;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ChatWriteMessageViewModel
{
  private StringProperty username;
  private StringProperty messages;
  private StringProperty inputTextChat;
  private ShareItModel model;
  private ObservableList<Message> listOfMessages;
  public ChatWriteMessageViewModel(ShareItModel model){
    this.model = model;
    username = new SimpleStringProperty();
    messages = new SimpleStringProperty();
    inputTextChat = new SimpleStringProperty();
    model.addListener("newMessage", this::onNewMessage);
  }

  private void onNewMessage(PropertyChangeEvent evt)
  {
    Platform.runLater(() ->{
      Message message = (Message) evt.getNewValue();
      /*if(message.getMemberFrom() == model.getMemberByUsername(model.getLoggedInUsername()).getId() && message.getMemberTo() == model.getMemberByUsername(
          model.getMemberUsername()).getId())
      {*/
        messages.setValue(messages.getValue() + "\n" + evt.getNewValue().toString());

    });
  }

  public StringProperty getUsername(){
    return username;
  }
  public void getMember(){

    username.setValue(model.getMemberUsername());
    System.out.println("Chat write View model" + username.getValue());
  }

  public StringProperty getMessages()
  {
    return messages;
  }
  public void loadLogs()
  {
    ArrayList<Message> listOfMessages = model.getMessagesFromUser(model.getMemberByUsername(model.getLoggedInUsername()).getId(), model.getMemberByUsername(
        model.getMemberUsername()).getId());
    for (int i = 0; i < listOfMessages.size(); i++)
    {
      messages.setValue(messages.getValue() + "\n" + listOfMessages.get(i).toString());
    }
  }

  public StringProperty getInputTextChat()
  {
    return inputTextChat;
  }

  public ObservableList<Message> getListOfMessages() {
    return listOfMessages;
  }

  public void sendMessage(){
    int idFrom = model.getMemberByUsername(model.getLoggedInUsername()).getId();
    int idTo = model.getMemberByUsername(model.getMemberUsername()).getId();
    Date timeStamp = Calendar
        .getInstance().getTime();

    Message message = new Message(idFrom, idTo, inputTextChat.getValue(), timeStamp);

    //messages.setValue(messages.getValue() + "\n" + timeStamp +": "+ inputTextChat.getValue());
    model.sendMessage(message);
  }

}
