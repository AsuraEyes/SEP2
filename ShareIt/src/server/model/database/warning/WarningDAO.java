package server.model.database.warning;

import shared.transferobjects.Warning;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The interface of Warning Data Access Object.
 */
public interface WarningDAO {
    /**
     * Gets all the Warnings connected to the administrator that gave it and member that received it
     *
     * @param administrator Administrator that is filling a warning
     * @param memberTo      Member that is getting warned
     * @return returns a list of all warnings that are matched with given data
     */
    ArrayList<Warning> getWarnings(String administrator, int memberTo);
    /**
     * Creates new warning by putting data provided by administrator
     * @param warning Warning object that will be sent
     * @return returns new object of Warning with data which was provided by Administrator while creating new warning
     */
    Warning sendWarning(Warning warning);
}
