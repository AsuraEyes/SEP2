package server.model.database.rating;

import shared.transferobjects.Rating;

import java.sql.SQLException;
import java.util.List;

/**
 * The interface of Rating Data Access Object.
 */
public interface RatingDAO
{
  /**
   * Creates new rating feedback of Member
   * @param starValue value of rating (from 1.0 to 5.0)
   * @param feedback optionally a written feedback that rating user can leave
   * @param username1 username of user that is rating
   * @param username2 username of user that is rated
   * @return returns new object of Rating
   * @throws SQLException
   */
  Rating create(double starValue, String feedback, String username1, String username2)throws SQLException;
  /**
   * Get all ratings that member has
   * @param username username of the user that method will get all ratings for
   * @return returns an arrayList of all user's ratings
   * @throws SQLException
   */
  List<Rating> getAllRatingsOnMember(String username) throws SQLException;
  /**
   * Gets user's rating based on from which to which user was it
   * @param fromUsername User that feedback rating was from
   * @param toUsername User that got rated
   * @return returns rating object that has usernames matching
   * @throws SQLException
   */
  Rating getRating(String fromUsername, String toUsername) throws SQLException;
  /**
   * Updates rating feedback whenever user decides to change it
   * @param rating new value of rating
   */
  void updateRating(Rating rating);
}
