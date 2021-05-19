package server;

import server.model.ServerModelImpl;
import server.model.database.category.CategoryDAOImpl;
import server.model.database.city.CityDAOImpl;
import server.model.database.member.MemberDAOImpl;
import server.model.database.rental.RentalDAOImpl;
import server.model.database.state.StateDAOImpl;
import server.networking.RMIServerImpl;
import shared.networking.RMIServer;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.sql.SQLException;

public class RunServer
{
  public static void main(String[] args)
          throws IOException, AlreadyBoundException, SQLException {
    RMIServer ss = new RMIServerImpl(new ServerModelImpl());
    String timothyPassword = "CoDex21";
    String maggiePassword = "SQLdatabaze";
    String bartoszPassword = "hehe2137";
    String currentPassword = maggiePassword;
    MemberDAOImpl.getInstance().setPassword(currentPassword);
    CityDAOImpl.getInstance().setPassword(currentPassword);
    RentalDAOImpl.getInstance().setPassword(currentPassword);
    CategoryDAOImpl.getInstance().setPassword(currentPassword);
    StateDAOImpl.getInstance().setPassword(currentPassword);
    ss.startServer();
  }
}
