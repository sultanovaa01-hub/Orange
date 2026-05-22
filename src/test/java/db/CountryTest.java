package db;

import kg.db.dbutils.Db_Connection;
import kg.db.dbutils.beans.Country;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class CountryTest {
    @BeforeEach
    void openConnection() throws SQLException {
        Db_Connection.openConnection("dvdRental1");
    }

    @AfterEach
    void closeConnection() {
        Db_Connection.closeConnection();
    }

    @Test
    void shouldReturnAllCountries() throws SQLException {
        Country.getAllCountries().forEach(
                System.out::println);
    }
    @Test
    void shouldInsertNewCountry () throws SQLException {
        Country.insert("Aliya",200);
    }
    @Test
    void shouldUpdateCountry () throws SQLException {
        Country.update("Nasim",200);

    }
    @Test
    void shouldDeleteActor () throws SQLException {
        Country.delete(300);
    }
}
