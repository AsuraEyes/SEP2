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
   * Instantiates StringProperties used for binding with the fields in the controller
   *
   * @param memberModel The model that this ViewModel uses
   * @param messageModel The model that this ViewModel uses
   */
  public ChatReceivedMessagesViewModel(MemberModel memberModel, MessageModel messageModel)
  {
    this.memberModel = memberModel;
    this.messageModel = messageModel;
  }

  public ArrayList<Message> getAllReceivedMessages()
  {
    return messageModel.getAllReceivedMessages();
  }

  public ArrayList<Warning> getAllReceivedWarnings()
  {
    return messageModel.getAllWarnings();
  }
  /**
   * On mouse click, the clicked object is retrieved and analyzed. The purpose
   * is to get the username that is being hold by a label and store it in the
   * MemberModelManager.
   *
   * @param object
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
