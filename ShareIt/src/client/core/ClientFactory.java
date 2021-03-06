package client.core;

import client.network.Client;
import client.network.RMIClient;
/**
 * A class that creates a client object, uses factory pattern.
 */
public class ClientFactory
{
  private Client client;
  /**
   * Creates a Client if not created, otherwise returns it. Starts the client at creation.
   *
   * @return Client
   */
  public Client getClient()
  {
    if (client == null)
    {
      client = new RMIClient();
      client.startClient();
    }
    return client;
  }
}
