package kg.rest.utils;

import kg.rest.models.User;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Slf4j
public class CsvUtils {

    private static final String DELIMITER = ",";
    private static final String HEADER = "id,name,email,gender,status";
    private static final String DEFAULT_PATH = "src/test/resources/users.csv";

    public static void writeUsersToCsv(List<User> users, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

            writer.println(HEADER);

            for (User user : users) {
                writer.println(buildRow(user));
            }

            log.info("CSV written successfully to {}", filePath);

        } catch (IOException e) {
            log.error("Failed to write CSV file at {}: {}", filePath, e.getMessage());
            throw new RuntimeException("CSV writing failed", e);
        }
    }

    // Overload with default path
    public static void writeUsersToCsv(List<User> users) {
        writeUsersToCsv(users, DEFAULT_PATH);
    }

    private static String buildRow(User user) {
        return String.join(DELIMITER,
                sanitize(String.valueOf(user.getId())),
                sanitize(user.getName()),
                sanitize(user.getEmail()),
                sanitize(user.getGender()),
                sanitize(user.getStatus())
        );
    }

    // Wraps fields in quotes if they contain commas or quotes
    private static String sanitize(String value) {
        if (value == null) return "";
        if (value.contains(DELIMITER) || value.contains("\"")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}

