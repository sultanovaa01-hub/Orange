package kg.db.dbutils.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import kg.db.dbutils.Db_Connection;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.dbutils.BeanProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Actors {
    Integer actor_id;
    String first_name;
    String last_name;
    String last_update;

    public static List<Actors> getAllActors () throws SQLException {
        String query = "select * FROM public.actor;";
        try(ResultSet resultSet = Db_Connection.makeQuery(query)){
            return new BeanProcessor().toBeanList(resultSet, Actors.class);
        }

    }
    public static Actors getBy (String column, Integer value) throws SQLException {
        String query = "select * from actor where " + column + " = ?; ";
        ResultSet resultSet = Db_Connection.makeQuery(query, value);
        if (!resultSet.next()){
            return null;
        } else {
            return new BeanProcessor().toBean(resultSet, Actors.class);
        }
    }
    // add this to Actors.java alongside the existing getBy()
    public static Actors getBy(String column, String value) throws SQLException {
        String query = "SELECT * FROM actor WHERE " + column + " = ?";
        ResultSet resultSet = Db_Connection.makeQuery(query, value);
        if (!resultSet.next()) {
            return null;
        }
        return new BeanProcessor().toBean(resultSet, Actors.class);
    }
    public static void insert(String firstName, String lastName) throws SQLException {
        String query = "INSERT INTO public.actor (first_name, last_name, last_update) VALUES (?, ?, NOW())";
        Db_Connection.makeUpdate(query, firstName, lastName);
    }

    public static void update(Integer actorId, String firstName, String lastName) throws SQLException {
        String query = "UPDATE public.actor SET first_name = ?, last_name = ?, last_update = NOW() WHERE actor_id = ?";
        Db_Connection.makeUpdate(query, firstName, lastName, actorId);
    }

    public static void delete(Integer actorId) throws SQLException {
        String query = "DELETE FROM public.actor WHERE actor_id = ?";
        Db_Connection.makeUpdate(query, actorId);
    }
}
