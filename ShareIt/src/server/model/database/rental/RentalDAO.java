package server.model.database.rental;

import shared.transferobjects.Member;
import shared.transferobjects.Rental;

import java.sql.SQLException;
import java.util.List;

public interface RentalDAO {
    Rental create(String name, String pictureLink,  String description, int price, String otherInformation, String stateName, Member member) throws SQLException;
    List<Rental> readByName(String name) throws SQLException;
    List<Rental> readBySearch(String search) throws SQLException;
    void update(Rental rental) throws SQLException;
    void delete(Rental rental) throws SQLException;
    int getNextAvailableId() throws SQLException;
}
