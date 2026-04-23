package kg.xiaomi.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import kg.xiaomi.models.Employee;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WebTablesPage extends BasePage {

    public SelenideElement addNewBtn = $("#addNewRecordButton");
    private SelenideElement firstName = $("#firstName");
    private SelenideElement lastName = $("#lastName");
    private SelenideElement email = $("#userEmail");
    private SelenideElement age = $("#age");
    private SelenideElement salary = $("#salary");
    private SelenideElement department = $("#department");
    private ElementsCollection rowsList = $$(".tbody tr");
    private ElementsCollection cellsList = $$(".tbody tr td");
    private SelenideElement submitBtn = $("#submit");

    public WebTablesPage clickAddBtn() {
        addNewBtn.shouldBe(visible).click();
        return this;
    }

    public WebTablesPage fillFirstName(String firstNameInput) {
        firstName.shouldBe(visible).sendKeys(firstNameInput);
        return this;
    }

    public WebTablesPage fillLastName(String lastNameInput) {
        lastName.shouldBe(visible).sendKeys(lastNameInput);
        return this;
    }

    public WebTablesPage fillLAge(Integer ageInput) {
        age.shouldBe(visible).sendKeys(ageInput.toString());
        return this;
    }

    public WebTablesPage fillEmail(String emailInput) {
        email.shouldBe(visible).sendKeys(emailInput);
        return this;
    }

    public WebTablesPage fillSalary(Integer salaryInput) {
        salary.shouldBe(visible).sendKeys(salaryInput.toString());
        return this;
    }

    public WebTablesPage fillDept(String departmentInput) {
        department.shouldBe(visible).sendKeys(departmentInput);
        return this;
    }
    public WebTablesPage fillDept() {
        submitBtn.shouldBe(visible).click();
        return this;
    }

//        elementActions.clickBtn(firstName).inputTxt(firstName, employee.getFirstName())
//                .clickBtn(lastName).inputTxt(lastName, employee.getLastName())
//                .clickBtn(age).inputTxt(age, String.valueOf(employee.getAge()))
//                .clickBtn(email).inputTxt(email, employee.getEmail())
//                .clickBtn(salary).inputTxt(salary, String.valueOf(employee.getSalary()))
//                .clickBtn(department).inputTxt(department, employee.getDepartment())
//                .clickBtn(submitBtn);
//
//        return this;

    public ArrayList<Employee> getEmployeesFromTable() {

        ArrayList<Employee> employees = new ArrayList<>();

        for (SelenideElement row : rowsList) {
            ElementsCollection cells = row.$$("td");
            String firstName = cells.get(0).getText();
            String lastName = cells.get(1).getText();
            String ageTxt = cells.get(2).getText().replaceAll("[^0-9]", "");
            String email = cells.get(3).getText();
            String salaryTxt = cells.get(4).getText().replaceAll("[^0-9]", "");
            String department = cells.get(5).getText();

            if (firstName.isEmpty() || lastName.isEmpty() || ageTxt.isEmpty()
                    || email.isEmpty() || salaryTxt.isEmpty() || department.isEmpty()) {
                continue;
            }
            int age = Integer.parseInt(ageTxt.trim());
            int salary = Integer.parseInt(salaryTxt.trim());

            employees.add(new Employee(firstName, lastName, age, email, salary, department));
        }
        return employees;
    }

    @Override
    public BasePage waitForPageToBeLoaded() {
        return null;
    }
}

