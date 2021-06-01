package client.viewmodel.chat_received_messages;

import client.model.ShareItModel;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import shared.transferobjects.Message;
import shared.transferobjects.Warning;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * A class that holds and manages data from the ChatReceivedMessages view.
 */
public class ChatReceivedMessagesViewModel {
  private ShareItModel model;

  /**
   * Instantiates a new ChatReceivedMessagesViewModel.
   *
   * @param model The model that this ViewModel uses
   */
  public ChatReceivedMessagesViewModel(ShareItModel model){
    this.model = model;
  }

  /**
   * Get all the messages in a list.
   *
   * @return returns a list of messages
   */
  public ArrayList<Message> getAllReceivedMessages(){
    return model.getAllReceivedMessages();
  }

  /**
   * Get all the warnings in a list.
   *
   * @return returns a list of all warnings
   */
  public ArrayList<Warning> getAllReceivedWarnings(){
    return model.getAllWarnings();
  }

  /**
   * Gets username.
   *
   * @param object (?)
   */
  public void getUsername(Object object) {
    if(object instanceof VBox){
       VBox vBox = (VBox) object;
      if(vBox.getChildren().get(0) instanceof Label) {
        Label label = (Label) vBox.getChildren().get(0);
        model.setMemberUsername(label.getText());
      }
    }
  }
}
