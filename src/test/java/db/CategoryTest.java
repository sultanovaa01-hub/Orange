package db;

import kg.db.dbutils.Db_Connection;
import kg.db.dbutils.beans.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class CategoryTest {
    @BeforeEach
    void openConnection() throws SQLException {
        Db_Connection.openConnection("dvdRental1");
    }

    @AfterEach
    void closeConnection() {
        Db_Connection.closeConnection();
    }

    @Test
    void shouldReturnAllCategories() throws SQLException {
        Category.getAllCategories().forEach(
                System.out::println);
    }

    @Test
    void shouldInsertNewCategory() throws SQLException {
        Category.insert("Hudojestvennyi");
    }

    @Test
    void shouldUpdateNewCategory() throws SQLException {
        Category.update(22, "Klassnyi");
    }

    @Test
    void getBy() throws SQLException {
        Category.getBy("name", "Klassnyi");
    }

    @Test
    void deleteCategory() throws SQLException {
        Category.delete(22);
    }

}