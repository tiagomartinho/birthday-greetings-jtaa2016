package it.xpug.kata.birthday_greetings.EmployeeParser;


import it.xpug.kata.birthday_greetings.Employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CSVEmployeeParser {
    public static List<Employee> parse(String fileName) throws IOException, ParseException {
        List<Employee> employees = new ArrayList<Employee>();
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String str = "";
        str = in.readLine(); // skip header
        while ((str = in.readLine()) != null) {
            String[] employeeData = str.split(", ");
            Employee employee = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3], employeeData[4]);
            employees.add(employee);
        }
        return employees;
    }
}
