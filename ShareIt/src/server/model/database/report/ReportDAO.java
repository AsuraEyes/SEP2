package server.model.database.report;

import shared.transferobjects.Report;

import java.sql.SQLException;

/**
 * Interface
 */
public interface ReportDAO
{
  /**
   * Creates new rating feedback of Member
   *
   * @param feedback  optionally a written feedback that rating user can leave
   * @param username1 username of user that is sending report
   * @param username2 username of user that is reported
   * @return returns new object of Report
   * @throws SQLException
   */
  Report create(String feedback, String username1, String username2) throws SQLException;
  /**
   * Gets user's report based on from which to which user was it
   *
   * @param fromUsername User that was reporting
   * @param toUsername   User that got reported
   * @return returns rating object that has usernames matching
   * @throws SQLException
   */
  Report getReport(String fromUsername, String toUsername) throws SQLException;
  /**
   * Updates rating feedback whenever user decides to change it
   *
   * @param report new value of report
   */
  void updateReport(Report report);

}