package client.core;

import client.viewmodel.chat_received_messages.ChatReceivedMessagesViewModel;
import client.viewmodel.chat_write_message.ChatWriteMessageViewModel;
import client.viewmodel.log_in.LogInViewModel;

public class ViewModelFactory
{
  private LogInViewModel logInViewModel;
  private ChatReceivedMessagesViewModel chatReceivedMessagesViewModel;
  private ChatWriteMessageViewModel chatWriteMessageViewModel;

  public ViewModelFactory(){
    chatReceivedMessagesViewModel = new ChatReceivedMessagesViewModel();
    chatWriteMessageViewModel = new ChatWriteMessageViewModel();
  }
  public LogInViewModel getLogInViewModel()
  {
    if(logInViewModel == null)
    {
      logInViewModel = new LogInViewModel();
    }
    return logInViewModel;
  }

  public ChatReceivedMessagesViewModel getChatReceivedMessagesViewModel()
  {
    return chatReceivedMessagesViewModel;
  }

  public ChatWriteMessageViewModel getChatWriteMessagesViewModel()
  {
    return chatWriteMessageViewModel;
  }
}
