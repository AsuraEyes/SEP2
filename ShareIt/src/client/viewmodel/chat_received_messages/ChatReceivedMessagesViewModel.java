package client.viewmodel.chat_received_messages;


import client.model.member.MemberModel;
import client.model.message.MessageModel;
import client.model.rental.RentalModel;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import shared.transferobjects.Message;
import shared.transferobjects.Warning;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ChatReceivedMessagesViewModel {
  private RentalModel rentalModel;
  private MemberModel memberModel;
  private MessageModel messageModel;
  public ChatReceivedMessagesViewModel(RentalModel rentalModel, MemberModel memberModel, MessageModel messageModel){
    this.rentalModel = rentalModel;
    this.memberModel = memberModel;
    this.messageModel = messageModel;
  }

  public ArrayList<Message> getAllReceivedMessages(){
    return messageModel.getAllReceivedMessages();
  }
  public ArrayList<Warning> getAllReceivedWarnings(){
    return messageModel.getAllWarnings();
  }
  public void getUsername(Object object){
    if(object instanceof VBox){
       VBox vBox = (VBox) object;
      if(vBox.getChildren().get(0) instanceof Label) {
        Label label = (Label) vBox.getChildren().get(0);
        memberModel.setMemberUsername(label.getText());
      }
    }
  }
}
