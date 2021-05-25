package server.model.database.warning;

import shared.transferobjects.Warning;

public interface WarningDAO {
    Warning sendWarning(Warning warning);
}
