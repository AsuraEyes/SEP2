package server;

import server.model.ServerModelImpl;
import server.model.database.administrator.AdministratorDAOImpl;
import server.model.database.category.CategoryDAOImpl;
import server.model.database.city.CityDAOImpl;
import server.model.database.member.MemberDAOImpl;
import server.model.database.message.MessageDAOImpl;
import server.model.database.rating.RatingDAOImpl;
import server.model.database.rental.RentalDAOImpl;
import server.model.database.rental_category.RentalCategoryDAOImpl;
import server.model.database.report.ReportDAOImpl;
import server.model.database.state.StateDAOImpl;
import server.model.database.warning.WarningDAOImpl;
import server.networking.RMIServerImpl;
import shared.networking.RMIServer;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.sql.SQLException;

/**
 * A class that is starting the server
 */
public class RunServer
{
  public static void main(String[] args)
          throws IOException, AlreadyBoundException, SQLException {
    RMIServer ss = new RMIServerImpl(new ServerModelImpl());
    String timothyPassword = "CoDex21";
    String maggiePassword = "SQLdatabaze";
    String bartoszPassword = "hehe2137";
    String claudiuPassword = "ohno...anyway";
    String currentPassword = bartoszPassword;
    CityDAOImpl.getInstance().setPassword(currentPassword);
    RentalDAOImpl.getInstance().setPassword(currentPassword);
    CategoryDAOImpl.getInstance().setPassword(currentPassword);
    StateDAOImpl.getInstance().setPassword(currentPassword);
    AdministratorDAOImpl.getInstance().setPassword(currentPassword);
    RatingDAOImpl.getInstance().setPassword(currentPassword);
    MemberDAOImpl.getInstance().setPassword(currentPassword);
    RentalCategoryDAOImpl.getInstance().setPassword(currentPassword);
    ReportDAOImpl.getInstance().setPassword(currentPassword);
    MessageDAOImpl.getInstance().setPassword(currentPassword);
    WarningDAOImpl.getInstance().setPassword(currentPassword);
    ss.startServer();
  }
}
