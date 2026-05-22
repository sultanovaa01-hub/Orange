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

public class Country {
    Integer country_id;
    String country;
    String last_update;

    public static List<Country> getAllCountries () throws SQLException {
        String query = "select * FROM public.country;";
        try(ResultSet resultSet = Db_Connection.makeQuery(query)){
            return new BeanProcessor().toBeanList(resultSet, Country.class);
        }

    }
    public static Country getBy (String column, Integer value) throws SQLException {
        String query = "select * from country where " + column + " = ?; ";
        ResultSet resultSet = Db_Connection.makeQuery(query, value);
        if (!resultSet.next()){
            return null;
        } else {
            return new BeanProcessor().toBean(resultSet, Country.class);
        }
    }
    public static Country getBy(String column, String value) throws SQLException {
        String query = "SELECT * FROM country WHERE " + column + " = ?";
        ResultSet resultSet = Db_Connection.makeQuery(query, value);
        if (!resultSet.next()) {
            return null;
        }
        return new BeanProcessor().toBean(resultSet, Country.class);
    }
    public static void insert(String country, Integer country_id) throws SQLException {
        String query = "INSERT INTO public.country (country, country_id, last_update) VALUES (?, ?, NOW())";
        Db_Connection.makeUpdate(query, country, country_id);
    }

    public static void update (String country, Integer country_id) throws SQLException {
        String query = "UPDATE public.country SET country = ?, last_update = NOW() WHERE country_id = ?";
        Db_Connection.makeUpdate(query, country, country_id);
    }

    public static void delete(Integer country_id) throws SQLException {
        String query = "DELETE FROM public.country WHERE country_id = ?";
        Db_Connection.makeUpdate(query, country_id);
    }
}
