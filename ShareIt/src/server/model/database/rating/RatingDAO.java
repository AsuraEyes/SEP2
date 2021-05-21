package server.model.database.rating;

import shared.transferobjects.Rating;

import java.sql.SQLException;
import java.util.List;

public interface RatingDAO
{
  Rating create(double starValue, String feedback, String username1, String username2)throws SQLException;
  List<Rating> getAllRatingsOnMember(String username) throws SQLException;
}
