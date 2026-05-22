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

public class City {
    Integer city_id;
    String city;
    Integer country_id;
    String last_update;

    public static List<City> getAllCities () throws SQLException {
        String query = "select * FROM public.city;";
        try(ResultSet resultSet = Db_Connection.makeQuery(query)){
            return new BeanProcessor().toBeanList(resultSet, City.class);
        }

    }
    public static City getBy (String column, Integer value) throws SQLException {
        String query = "select * from city where " + column + " = ?; ";
        ResultSet resultSet = Db_Connection.makeQuery(query, value);
        if (!resultSet.next()){
            return null;
        } else {
            return new BeanProcessor().toBean(resultSet, City.class);
        }
    }
    public static City getBy(String column, String value) throws SQLException {
        String query = "SELECT * FROM city WHERE " + column + " = ?";
        ResultSet resultSet = Db_Connection.makeQuery(query, value);
        if (!resultSet.next()) {
            return null;
        }
        return new BeanProcessor().toBean(resultSet, City.class);
    }
    public static void insert(String city, Integer country_id) throws SQLException {
        String query = "INSERT INTO public.city (city, country_id, last_update) VALUES (?, ?, NOW())";
        Db_Connection.makeUpdate(query, city, country_id);
    }

    public static void update(Integer city_id, String city, Integer country_id) throws SQLException {
        String query = "UPDATE public.city SET city = ?, country_id = ?, last_update = NOW() WHERE city_id = ?";
        Db_Connection.makeUpdate(query, city, country_id, city_id);
    }

    public static void delete(Integer city_id) throws SQLException {
        String query = "DELETE FROM public.city WHERE city_id = ?";
        Db_Connection.makeUpdate(query, city_id);
    }
}
