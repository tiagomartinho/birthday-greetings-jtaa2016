package it.xpug.kata.birthday_greetings.EmployeeParser;

import it.xpug.kata.birthday_greetings.Employee;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class EmployeeParser {
    public static List<Employee> parse(String fileName) throws IOException, ParseException {
        if(fileName.contains(".csv"))
            return CSVEmployeeParser.parse(fileName);
        if(fileName.contains(".json"))
            return JSONEmployeeParser.parse(fileName);
        return null;
    }
}
