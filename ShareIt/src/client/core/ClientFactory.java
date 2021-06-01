package client.core;

import client.network.Client;
import client.network.RMIClient;

/**
 * A class that creates a client object without having to specify the exact class of the object,
 */
public class ClientFactory {
  private Client client;

  /**
   * Creates client when it needs to be accessed.
   *
   * @return returns Client
   */
  public Client getClient() {
    if(client == null){
      client = new RMIClient();
    }
    return client;
  }
}
