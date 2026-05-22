package db;

import kg.db.dbutils.Db_Connection;
import kg.db.dbutils.beans.Actors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ActorTest {
    @BeforeEach
    void openConnection() throws SQLException {
        Db_Connection.openConnection("dvdRental1");
    }

    @AfterEach
    void closeConnection() {
        Db_Connection.closeConnection();
    }

    @Test
    void shouldReturnAllTheActors() throws SQLException {
        Actors.getAllActors().forEach(
                System.out::println
        );

        Actors actors = Actors.getBy("actor_id",1);
        System.out.println(actors);
    }
    @Test
    void shouldInsertNewActor () throws SQLException {
        Actors.insert("Aliya","Sultan");
    }
    @Test
    void shouldUpdateActor () throws SQLException {
        Actors.update(203,"Nasim","Sultanova");

        Actors actor = Actors.getBy("first_name", "Nasim");
                Assertions.assertThat(actor)
                .as("should exist in DB")
                .isNotNull();
    }
    @Test
    void shouldDeleteActor () throws SQLException {
        Actors.delete(202);
    }
}
