package client.core;

import client.network.Client;
import client.network.RMIClient;

import java.io.IOException;

public class ClientFactory {
  private Client client;

  public Client getClient()
  {
    if(client == null){
      client = new RMIClient();
      client.startClient();
    }
    return client;
  }
}
