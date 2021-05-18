package server.model.database.category;

import shared.transferobjects.Category;
import java.sql.SQLException;
import java.util.List;

public interface CategoryDAO
{

  List<Category> readCategory() throws SQLException;

}
