package server.model.database.category;

import shared.transferobjects.Category;
import java.sql.SQLException;
import java.util.List;

public interface CategoryDAO
{
  /**
   * Reads all categories from database
   * @return returns all category names in a arraylist
   */
  List<Category> readCategory();

}
