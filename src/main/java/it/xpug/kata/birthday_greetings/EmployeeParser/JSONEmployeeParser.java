package it.xpug.kata.birthday_greetings.EmployeeParser;

import it.xpug.kata.birthday_greetings.Employee;
import it.xpug.kata.birthday_greetings.JSONUtilites;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class JSONEmployeeParser {
    public static List<Employee> parse(String fileName) throws IOException, ParseException {
        List<Employee> employees = new ArrayList<Employee>();

        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String str = "";
        str = in.readLine(); // skip header

            String json = str;
            while ((str = in.readLine()) != null) {
                json += str;
            }
            json = json.replace("[", "").replace("]", "");
            String[] employeeData = json.split("(?<=\\},)");
            for (String employeeJSON:employeeData) {
                employeeJSON = employeeJSON.replace("},","}");
                JSONUtilites jsonUtilites = new JSONUtilites(employeeJSON);
                String surname = jsonUtilites.extractValue("last_name");
                String name = jsonUtilites.extractValue("first_name");
                String dateOfBirth = jsonUtilites.extractValue("date_of_birth");
                String email = jsonUtilites.extractValue("email");
                String faxNumber = jsonUtilites.extractValue("fax_number");
                Employee employee = new Employee(name,surname,dateOfBirth,email,faxNumber);
                employees.add(employee);
        }

        return employees;
    }
}
