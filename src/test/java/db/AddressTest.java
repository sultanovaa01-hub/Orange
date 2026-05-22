package db;

import kg.db.dbutils.Db_Connection;
import kg.db.dbutils.beans.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class AddressTest {
    @BeforeEach
    void openConnection() throws SQLException {
        Db_Connection.openConnection("dvdRental1");
    }
    @AfterEach
    void closeConnection() {
        Db_Connection.closeConnection();
    }
    @Test
    void shouldReturnAllAddresses () throws SQLException {
        List<Address> address = Address.getAllAddresses();
        System.out.println(address);
    }
    @Test
    void shouldInsertNewAddress () throws SQLException {
        Address.insert("Voroshilova","California", 303,  720000, 23456);
    }
    @Test
    void shouldUpdateAddressInfo () throws SQLException {
        Address.update(1,"Kolbaeva",72000);
    }
    @Test
    void shouldDeleteAddress () throws SQLException {
        Address.delete(607);
    }
}
