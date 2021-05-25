package server.model.database.warning;

import shared.transferobjects.Warning;

import java.util.ArrayList;

public interface WarningDAO {
    ArrayList<Warning> getWarnings(String administrator, int memberTo);
    Warning sendWarning(Warning warning);
}
