package client.views.chat_received_messages;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.chat_received_messages.ChatReceivedMessagesViewModel;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class ChatReceivedMessagesController
{
  @FXML
  private VBox vBoxRight;
  @FXML private VBox vBoxLeft;

  private ViewHandler viewHandler;
  private ChatReceivedMessagesViewModel chatReceivedMessagesViewModel;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
    this.viewHandler = viewHandler;
    chatReceivedMessagesViewModel = viewModelFactory.getChatReceivedMessagesViewModel();
    displayMessages();
    displayWarnings();
  }

  public void displayMessages(){
    if (chatReceivedMessagesViewModel.getAllReceivedMessages() != null && !chatReceivedMessagesViewModel.getAllReceivedMessages().isEmpty()) {
      for (int i = 0; i < chatReceivedMessagesViewModel.getAllReceivedMessages().size(); i++) {
        VBox messageBox = new VBox();
        Label usernameLabel = new Label(chatReceivedMessagesViewModel.getAllReceivedMessages().get(i).getUsernameFrom());
        usernameLabel.getStyleClass().add("username");
        Text message = new Text("Received on: "  + chatReceivedMessagesViewModel.getAllReceivedMessages().get(i).getTimeStamp() + "\n" + chatReceivedMessagesViewModel.getAllReceivedMessages().get(i).getText());
        message.getStyleClass().add("messageText");
        TextFlow textFlow = new TextFlow();
        textFlow.getChildren().addAll(message);
        messageBox.getChildren().addAll(usernameLabel,textFlow);
        messageBox.getStyleClass().add("message");
        messageBox.setPadding(new Insets(20,20,20,20));
        vBoxRight.getChildren().add(messageBox);
        vBoxRight.getStyleClass().add("vbox");
        vBoxRight.getChildren().get(i)
            .addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
              chatReceivedMessagesViewModel.getUsername(event.getSource());
              viewHandler.setView(viewHandler.menu(), viewHandler.chatWrite());
            });
      }
    }
  }

  public void displayWarnings(){
    if (chatReceivedMessagesViewModel.getAllReceivedWarnings() != null && !chatReceivedMessagesViewModel.getAllReceivedWarnings().isEmpty()) {
      for (int i = 0; i < chatReceivedMessagesViewModel.getAllReceivedWarnings().size(); i++) {
        VBox warningBox = new VBox();
        Label administratorLabel = new Label(chatReceivedMessagesViewModel.getAllReceivedWarnings().get(i).getAdministratorFrom());
        administratorLabel.getStyleClass().add("administrator");
        Text warning = new Text("Received on: "  + chatReceivedMessagesViewModel.getAllReceivedWarnings().get(i).getTimeStamp() + "\n" + chatReceivedMessagesViewModel.getAllReceivedWarnings().get(i).getText());
        TextFlow textFlow = new TextFlow();
        textFlow.getChildren().addAll(warning);
        warningBox.getChildren().addAll(administratorLabel,textFlow);
        warningBox.setPadding(new Insets(20,20,20,20));
        warningBox.getStyleClass().add("warning");
        warning.getStyleClass().add("warningText");
        vBoxLeft.getChildren().add(warningBox);
      }
    }
  }

}
