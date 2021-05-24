package server.model.data_check;

import server.model.database.rating.RatingDAOImpl;

import java.sql.SQLException;
/**
 * Class that checks data before running an instance(Rating data in this case)
 */
public class DataCheckRating
{
  private double starValue;
  private String feedback;

  public String addFeedback(double starValue, String feedback, String username1, String username2){
    this.feedback = feedback;
    this.starValue = starValue;

    if(FeedbackGiven())
    {
      try
      {
        RatingDAOImpl.getInstance().create(starValue, feedback, username1, username2);
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
    return true;
  }
}
