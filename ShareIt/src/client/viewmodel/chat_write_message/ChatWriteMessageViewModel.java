package client.viewmodel.chat_write_message;

import client.model.member.MemberModel;
import client.model.message.MessageModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Message;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

/**
 * A class that holds and manages data from the ChatWriteMessage view.
 */
public class ChatWriteMessageViewModel
{
  private final MemberModel memberModel;
  private final MessageModel messageModel;

  private final StringProperty username;
  private final StringProperty messages;
  private final StringProperty inputTextChat;
  /**
   * Instantiates a new ChatWriteMessageViewModel.
   *
   * @param model The model that this ViewModel uses
   */
  public ChatWriteMessageViewModel(MemberModel memberModel,
      MessageModel messageModel)
  {
    this.memberModel = memberModel;
    this.messageModel = messageModel;

    username = new SimpleStringProperty();
    messages = new SimpleStringProperty();
    inputTextChat = new SimpleStringProperty();
    messageModel.addListener("newMessage", this::onNewMessage);
  }

  /**
   * (?)
   * @param evt an event that is happening
   */
  private void onNewMessage(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      Message message = (Message) evt.getNewValue();
      if (message.getMemberFrom() == memberModel
          .getMemberByUsername(memberModel.getLoggedInUsername()).getId()
          || message.getMemberFrom() == memberModel
          .getMemberByUsername(memberModel.getMemberUsername()).getId())
      {
        if (message.getMemberTo() == memberModel
            .getMemberByUsername(memberModel.getLoggedInUsername()).getId()
            || message.getMemberTo() == memberModel
            .getMemberByUsername(memberModel.getMemberUsername()).getId())
        {
          messages.setValue(
              messages.getValue() + "\n" + evt.getNewValue().toString());
        }
      }
    });
  }
  /**
   * Gets username.
   *
   * @return returns Member's username
   */
  public StringProperty getUsername()
  {
    return username;
  }
  /**
   * Sets username for a Member.
   */
  public void getMember()
  {
    username.setValue(memberModel.getMemberUsername());
  }
  /**
   * Gets all the messages.
   *
   * @return the messages
   */
  public StringProperty getMessages()
  {
    return messages;
  }
  /**
   * Load logs. (?)
   */
  public void loadLogs()
  {
    messages.setValue("Welcome to chat!");
    ArrayList<Message> listOfMessages = messageModel.getMessagesFromUser(
        memberModel.getMemberByUsername(memberModel.getLoggedInUsername())
            .getId(),
        memberModel.getMemberByUsername(memberModel.getMemberUsername())
            .getId());
    if (listOfMessages != null)
    {
      for (int i = 0; i < listOfMessages.size(); i++)
      {
        messages.setValue(
            messages.getValue() + "\n" + listOfMessages.get(i).toString());
      }
    }

  }

  /**
   * Gets input text from the chat.
   *
   * @return returns the input text
   */
  public StringProperty getInputTextChat()
  {
    return inputTextChat;
  }
  /**
   * Sends a message to another Member.
   */
  public void sendMessage()
  {
    int idFrom = memberModel
        .getMemberByUsername(memberModel.getLoggedInUsername()).getId();
    int idTo = memberModel.getMemberByUsername(memberModel.getMemberUsername())
        .getId();
    Message message = new Message(idFrom, idTo, inputTextChat.getValue(), null);
    messageModel.sendMessage(message);
  }
  /**
   * Loads all received messages.
   */
  public void loadAllReceivedMessages()
  {
    messageModel.setAllReceivedMessages(memberModel.getLoggedInUsername());
  }

}
