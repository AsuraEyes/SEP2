package client.model.database.state;

import shared.transferobjects.State;

import java.sql.SQLException;
import java.util.List;

public interface StateDAO
{
  List<State> readState() throws SQLException;
}
