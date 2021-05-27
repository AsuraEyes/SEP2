package client.viewmodel.chat_write_message;


import client.model.member.MemberModel;
import client.model.message.MessageModel;
import client.model.rental.RentalModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Message;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class ChatWriteMessageViewModel
{
  private RentalModel rentalModel;
  private MemberModel memberModel;
  private MessageModel messageModel;

  private StringProperty username;
  private StringProperty messages;
  private StringProperty inputTextChat;


  public ChatWriteMessageViewModel(RentalModel rentalModel, MemberModel memberModel, MessageModel messageModel){
    this.rentalModel = rentalModel;
    this.memberModel = memberModel;
    this.messageModel = messageModel;

    username = new SimpleStringProperty();
    messages = new SimpleStringProperty();
    inputTextChat = new SimpleStringProperty();
    messageModel.addListener("newMessage", this::onNewMessage);
  }

  private void onNewMessage(PropertyChangeEvent evt)
  {
    Platform.runLater(() ->{
      Message message = (Message) evt.getNewValue();
      if(message.getMemberFrom() == memberModel.getMemberByUsername(memberModel.getLoggedInUsername()).getId() || message.getMemberFrom() == memberModel.getMemberByUsername(
          memberModel.getMemberUsername()).getId())
      {
        if(message.getMemberTo() == memberModel.getMemberByUsername(memberModel.getLoggedInUsername()).getId() || message.getMemberTo() == memberModel.getMemberByUsername(
            memberModel.getMemberUsername()).getId())
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
    username.setValue(memberModel.getMemberUsername());
  }

  public StringProperty getMessages() {
    return messages;
  }
  public void loadLogs()
  {
    messages.setValue("Welcome to chat!");
    ArrayList<Message> listOfMessages = messageModel.getMessagesFromUser(memberModel.getMemberByUsername(memberModel.getLoggedInUsername()).getId(), memberModel.getMemberByUsername(
        memberModel.getMemberUsername()).getId());
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
    int idFrom = memberModel.getMemberByUsername(memberModel.getLoggedInUsername()).getId();
    int idTo = memberModel.getMemberByUsername(memberModel.getMemberUsername()).getId();
    Message message = new Message(idFrom, idTo, inputTextChat.getValue(), null);
    messageModel.sendMessage(message);
  }
  public void loadAllReceivedMessages(){
    messageModel.setAllReceivedMessages(memberModel.getLoggedInUsername());
  }

}
