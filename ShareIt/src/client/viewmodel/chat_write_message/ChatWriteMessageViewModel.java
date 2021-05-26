package client.viewmodel.chat_write_message;

import client.model.ShareItModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Message;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class ChatWriteMessageViewModel
{
  private StringProperty username;
  private StringProperty messages;
  private StringProperty inputTextChat;
  private ShareItModel model;
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
      if(message.getMemberFrom() == model.getMemberByUsername(model.getLoggedInUsername()).getId() || message.getMemberFrom() == model.getMemberByUsername(
          model.getMemberUsername()).getId())
      {
        if(message.getMemberTo() == model.getMemberByUsername(model.getLoggedInUsername()).getId() || message.getMemberTo() == model.getMemberByUsername(
            model.getMemberUsername()).getId())
        {
          messages.setValue(messages.getValue() + "\n" + evt.getNewValue().toString());
        }
      }
    });
  }

  public StringProperty getUsername(){
    return username;
  }

  public void getMember(){
    username.setValue(model.getMemberUsername());
  }

  public StringProperty getMessages() {
    return messages;
  }
  public void loadLogs()
  {
    messages.setValue("Welcome to chat!");
    ArrayList<Message> listOfMessages = model.getMessagesFromUser(model.getMemberByUsername(model.getLoggedInUsername()).getId(), model.getMemberByUsername(
        model.getMemberUsername()).getId());
    if(listOfMessages != null){
      for (int i = 0; i < listOfMessages.size(); i++)
      {
        messages.setValue(messages.getValue() + "\n" + listOfMessages.get(i).toString());
      }
    }

  }

  public StringProperty getInputTextChat()
  {
    return inputTextChat;
  }

  public void sendMessage(){
    int idFrom = model.getMemberByUsername(model.getLoggedInUsername()).getId();
    int idTo = model.getMemberByUsername(model.getMemberUsername()).getId();
    Message message = new Message(idFrom, idTo, inputTextChat.getValue(), null);
    model.sendMessage(message);
  }
  public void loadAllReceivedMessages(){
    model.setAllReceivedMessages(model.getLoggedInUsername());
  }

}
