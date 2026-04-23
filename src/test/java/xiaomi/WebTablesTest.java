package xiaomi;

import com.codeborne.selenide.Selenide;
import kg.xiaomi.models.Employee;
import kg.xiaomi.pages.WebTablesPage;
import org.junit.jupiter.api.Test;

import java.util.List;


public class WebTablesTest extends BaseTest{
    @Test
    void webTablesTest() {

        WebTablesPage webTablesPage = new WebTablesPage();
        Selenide.open("https://demoqa.com/webtables");

        List<Employee> employeeList = webTablesPage.getEmployeesFromTable();
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }
}