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

    public class Address {
        Integer address_id;
        String address;
        String address2;
        String district;
        String city_id;
        String postal_code;
        String phone;
        String last_update;

        public static List<Address> getAllAddresses () throws SQLException {
            String query = "select * FROM public.address;";
            try(ResultSet resultSet = Db_Connection.makeQuery(query)){
                return new BeanProcessor().toBeanList(resultSet, Address.class);
            }

        }
        public static Address getBy (String column, Integer value) throws SQLException {
            String query = "select * from address where " + column + " = ?; ";
            ResultSet resultSet = Db_Connection.makeQuery(query, value);
            if (!resultSet.next()){
                return null;
            } else {
                return new BeanProcessor().toBean(resultSet, Address.class);
            }
        }
        public static Address getBy(String column, String value) throws SQLException {
            String query = "SELECT * FROM address WHERE " + column + " = ?";
            ResultSet resultSet = Db_Connection.makeQuery(query, value);
            if (!resultSet.next()) {
                return null;
            }
            return new BeanProcessor().toBean(resultSet, Address.class);
        }

        public static void insert(String address, String district, Integer city_id,Integer postal_code,
                                  Integer phone) throws SQLException {
            String query = "INSERT INTO public.address (address, district, city_id, postal_code, phone, last_update) VALUES (?, ?, ?, ?, ?, NOW())";
            Db_Connection.makeUpdate(query, address,district, city_id, postal_code, phone);
        }

        public static void update(Integer address_id, String address, int postal_code) throws SQLException {
            String query = "UPDATE public.address SET address = ?, postal_code = ?, last_update = NOW() WHERE address_id = ?";
            Db_Connection.makeUpdate(query, address, postal_code, address_id);
        }

        public static void delete(Integer address_id) throws SQLException {
            String query = "DELETE FROM public.address WHERE address_id = ?";
            Db_Connection.makeUpdate(query, address_id);
        }
    }

