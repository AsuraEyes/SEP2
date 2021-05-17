package client.network;


import shared.util.Subject;
import java.io.IOException;

public interface Client extends Subject
{
  void startClient() throws IOException;
}
