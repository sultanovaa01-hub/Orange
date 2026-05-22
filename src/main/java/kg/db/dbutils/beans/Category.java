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

public class Category {
    Integer category_id;
    String name;
    String last_update;

    public static List<Category> getAllCategories () throws SQLException {
        String query = "select * FROM public.category;";
        try(ResultSet resultSet = Db_Connection.makeQuery(query)){
            return new BeanProcessor().toBeanList(resultSet, Category.class);
        }

    }
    public static Category getBy (String column, Integer value) throws SQLException {
        String query = "select * from category where " + column + " = ?; ";
        ResultSet resultSet = Db_Connection.makeQuery(query, value);
        if (!resultSet.next()){
            return null;
        } else {
            return new BeanProcessor().toBean(resultSet, Category.class);
        }
    }
    public static Category getBy(String column, String value) throws SQLException {
        String query = "SELECT * FROM category WHERE " + column + " = ?";
        ResultSet resultSet = Db_Connection.makeQuery(query, value);
        if (!resultSet.next()) {
            return null;
        }
        return new BeanProcessor().toBean(resultSet, Category.class);
    }
    public static void insert(String name) throws SQLException {
        String query = "INSERT INTO public.category (name, last_update) VALUES (?, NOW())";
        Db_Connection.makeUpdate(query, name);
    }

    public static void update(Integer category_id, String name) throws SQLException {
        String query = "UPDATE public.category SET name = ?, last_update = NOW() WHERE category_id = ?";
        Db_Connection.makeUpdate(query, name, category_id);
    }

    public static void delete(Integer category_id) throws SQLException {
        String query = "DELETE FROM public.category WHERE category_id = ?";
        Db_Connection.makeUpdate(query, category_id);
    }
}
