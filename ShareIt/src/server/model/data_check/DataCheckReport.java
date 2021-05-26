package server.model.data_check;

import server.model.database.report.ReportDAOImpl;

import java.sql.SQLException;
/**
 * Class that checks data before running an instance(Report data in this case)
 */
public class DataCheckReport
{

  private String feedback;

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
        //
      }
    }

    return "Ooops, something went wrong!!";
  }

  private boolean FeedbackGiven(){
    if (feedback != null){
      if (!(feedback.trim().equals("") && feedback.isBlank() && feedback.isEmpty())){
        return true;
      }
    }
    return false;
  }
}