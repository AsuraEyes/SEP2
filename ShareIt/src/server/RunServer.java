package server;

import server.model.ServerModelImpl;
import server.networking.RMIServerImpl;
import shared.networking.RMIServer;

import java.io.IOException;
import java.rmi.AlreadyBoundException;

public class RunServer
{
  public static void main(String[] args)
      throws IOException, AlreadyBoundException
  {
    RMIServer ss = new RMIServerImpl(new ServerModelImpl());
    ss.startServer();
  }
}
