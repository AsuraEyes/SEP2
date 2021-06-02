package server.model.data_check;

import server.model.database.rating.RatingDAOImpl;

/**
 * Class that checks data from ServerModelManager before running an instance(Rating data in this case)
 */
public class DataCheckRating
{
  private String feedback;
  /**
   * Check of the Rating data before passing it into the DAO.
   *
   * @param starValue     Rating that was chosen while rating a member.
   * @param feedback      Commentary that was added while rating a member.
   * @param username1     Member's ID that rated another member.
   * @param username2     Member's ID that was rated.
   * @return returns String type dependable on the result.
   */
  public String addFeedback(double starValue, String feedback, String username1,
      String username2)
  {
    this.feedback = feedback;

    if (FeedbackGiven())
    {
      RatingDAOImpl.getInstance()
          .create(starValue, feedback, username1, username2);
      return "Added";
    }
    return "Ooops, something went wrong!!";
  }

  private boolean FeedbackGiven()
  {
    if (feedback != null)
    {
      if (!(feedback.trim().equals("") && feedback.isBlank() && feedback
          .isEmpty()))
      {
        return true;
      }
    }
    return true;
  }
}
