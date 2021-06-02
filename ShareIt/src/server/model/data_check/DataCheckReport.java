package server.model.data_check;

import server.model.database.report.ReportDAOImpl;

import java.sql.SQLException;

/**
 * Class that checks data from ServerModelManager before running an instance(Report data in this case)
 */
public class DataCheckReport
{

  private String feedback;

  /**
   * Check of the Rental data before passing it into the DAO.
   *
   * @param feedback  Commentary that was added while reporting a member.
   * @param username1 Member's ID that reported another member.
   * @param username2 Member's ID that was reported.
   * @return returns String type dependable on the result.
   */
  public String addReport(String feedback, String username1, String username2){
    this.feedback = feedback;


    if(FeedbackGiven())
    {
      try
      {
        ReportDAOImpl.getInstance().create(feedback, username1, username2);
        return "Added";
      }
      catch (SQLException e){
        e.printStackTrace();
      }
    }

    return "Ooops, something went wrong!!";
  }
  /**
   * Checks is feedback was given.
   * @return returns true if feedback is not empty, false if it is empty
   */
  private boolean FeedbackGiven(){
    if (feedback != null){
      if (!(feedback.trim().equals("") && feedback.isBlank() && feedback.isEmpty())){
        return true;
      }
    }
    return false;
  }
}