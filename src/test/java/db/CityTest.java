package db;

import kg.db.dbutils.Db_Connection;
import kg.db.dbutils.beans.City;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class CityTest {
    @BeforeEach
    void openConnection() throws SQLException {
        Db_Connection.openConnection("dvdRental1");
    }

    @AfterEach
    void closeConnection() {
        Db_Connection.closeConnection();
    }

    @Test
    void shouldReturnAllCities() throws SQLException {
        City.getAllCities().forEach(
                System.out::println);
    }
    @Test
    void shouldInsertNewCity () throws SQLException {
        City.insert("Aliya",20);
    }
    @Test
    void shouldUpdateCity () throws SQLException {
        City.update(20,"Nasim",20);

    }
    @Test
    void shouldDeleteActor () throws SQLException {
        City.delete(603);
    }
}
