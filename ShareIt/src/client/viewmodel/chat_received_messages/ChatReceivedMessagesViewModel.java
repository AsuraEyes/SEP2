package client.viewmodel.chat_received_messages;

import client.model.member.MemberModel;
import client.model.message.MessageModel;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import shared.transferobjects.Message;
import shared.transferobjects.Warning;

import java.util.ArrayList;
/**
 * A class that holds and manages data from the ChatReceivedMessages view.
 */
public class ChatReceivedMessagesViewModel
{
  private final MemberModel memberModel;
  private final MessageModel messageModel;
  /**
   * Instantiates a new ChatReceivedMessagesViewModel.
   *
   * @param model The model that this ViewModel uses
   */
  public ChatReceivedMessagesViewModel(MemberModel memberModel, MessageModel messageModel)
  {
    this.memberModel = memberModel;
    this.messageModel = messageModel;
  }
  /**
   * Get all the messages in a list.
   *
   * @return returns a list of messages
   */
  public ArrayList<Message> getAllReceivedMessages()
  {
    return messageModel.getAllReceivedMessages();
  }
  /**
   * Get all the warnings in a list.
   *
   * @return returns a list of all warnings
   */
  public ArrayList<Warning> getAllReceivedWarnings()
  {
    return messageModel.getAllWarnings();
  }
  /**
   * Gets username.
   *
   * @param object (?)
   */
  public void getUsername(Object object)
  {
    if (object instanceof VBox)
    {
      VBox vBox = (VBox) object;
      if (vBox.getChildren().get(0) instanceof Label)
      {
        Label label = (Label) vBox.getChildren().get(0);
        memberModel.setMemberUsername(label.getText());
      }
    }
  }
}
