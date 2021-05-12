package client.views.chat_received_messages;

import client.core.ViewHandler;
import client.core.ViewModelFactory;

public class ChatReceivedMessagesController
{
  private ViewHandler viewHandler;
  private ViewModelFactory viewModelFactory;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
    this.viewHandler = viewHandler;
    this.viewModelFactory = viewModelFactory;
  }
}
