package client.views.chat_write_message;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.chat_write_message.ChatWriteMessageViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ChatWriteMessageController
{
  @FXML
  private Label toUsernameLabel;
  @FXML
  private TextArea textChatArea;
  @FXML
  private TextField inputTextChatField;

  private ViewHandler viewHandler;
  private ChatWriteMessageViewModel chatWriteMessageViewModel;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
      throws IOException
  {
    this.viewHandler = viewHandler;
    textChatArea.clear();
    chatWriteMessageViewModel = viewModelFactory.getChatWriteMessagesViewModel();
    toUsernameLabel.textProperty().bind(chatWriteMessageViewModel.getUsername());
    inputTextChatField.textProperty().bindBidirectional(chatWriteMessageViewModel.getInputTextChat());
    chatWriteMessageViewModel.getMember();
    textChatArea.textProperty().bind(chatWriteMessageViewModel.getMessages());
    chatWriteMessageViewModel.loadLogs();
  }

  public void onGoBack(ActionEvent actionEvent) throws IOException
  {
    viewHandler.setView(viewHandler.menu(), viewHandler.chatReceived());
  }

  public void onSend(ActionEvent actionEvent)
  {
    chatWriteMessageViewModel.sendMessage();
    inputTextChatField.clear();
  }
}
