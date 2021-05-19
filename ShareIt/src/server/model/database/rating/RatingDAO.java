package server.model.database.rating;

import shared.transferobjects.Rating;

import java.sql.SQLException;

public interface RatingDAO
{
  Rating create(double starValue, String feedback, String username1, String username2)throws SQLException;
}
