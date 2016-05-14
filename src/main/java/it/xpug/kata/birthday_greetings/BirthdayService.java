package it.xpug.kata.birthday_greetings;

import org.json.JSONObject;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class BirthdayService {

    public void sendGreetings(String fileName, XDate xDate, String smtpHost, int smtpPort) throws IOException, ParseException, AddressException, MessagingException {
        List<Employee> employees = new ArrayList<Employee>();

        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String str = "";
        str = in.readLine(); // skip header
        if (fileName.contains(".csv")) {
            while ((str = in.readLine()) != null) {
                String[] employeeData = str.split(", ");
                Employee employee = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3], employeeData[4]);

                employees.add(employee);
            }
        }

        if (fileName.contains(".json")) {
            String json = str;
            while ((str = in.readLine()) != null) {
                json += str;
            }
            json = json.replace("[", "").replace("]", "");
            String[] employeeData = json.split("(?<=\\},)");
            for (String employeeJSON:employeeData) {
                JSONObject obj = new JSONObject(employeeJSON.replace("},","}"));
                String surname = obj.getString("last_name");
                String name = obj.getString("first_name");
                String dateOfBirth = obj.getString("date_of_birth");
                String email = obj.getString("email");
                String faxNumber = obj.getString("fax_number");
                Employee employee = new Employee(name,surname,dateOfBirth,email,faxNumber);
                employees.add(employee);
            }
        }

        for (Employee employee:employees) {
            if (employee.isBirthday(xDate)) {
                sendEmail(smtpHost, smtpPort, employee);
                if (employee.hasValidFax()) {
                    sendFax(smtpHost, smtpPort, employee);
                }
            }
            if (xDate.isEaster()) {
                sendEasterEmail(smtpHost, smtpPort, employee);
            }
        }
    }

    private void sendEasterEmail(String smtpHost, int smtpPort, Employee employee) throws MessagingException {
        String recipient = employee.getEmail();
        String body = "%NAME% %SURNAME% ti facciamo un augurio di una Pasqua serena.";
        body = body.replace("%NAME%", employee.getFirstName());
        body = body.replace("%SURNAME%", employee.getLastName());
        String subject = "Auguri di Buona Pasqua!";
        sendMessage(smtpHost, smtpPort, "sender@here.com", subject, body, recipient);
    }

    private void sendFax(String smtpHost, int smtpPort, Employee employee) throws MessagingException {
        String recipient = "inviafax@jugtaa2016.org";
        String body = "[emailaccount]=greetingsKata@jugtaa.org\n[password]=hacksprint2016\n[mittente]=%NAME%\n[emailrisposta]=%EMAIL%\n[numerofax]=%FAX%\n[contenutofax]=Happy Birthday, dear %NAME%!";
        body = body.replace("%NAME%", employee.getFirstName());
        body = body.replace("%EMAIL%", employee.getEmail());
        body = body.replace("%FAX%", employee.getFax());
        String subject = "INVIOFAXDAEMAIL";
        sendMessage(smtpHost, smtpPort, "sender@here.com", subject, body, recipient);
    }

    private void sendEmail(String smtpHost, int smtpPort, Employee employee) throws MessagingException {
        String recipient = employee.getEmail();
        String body = "Happy Birthday, dear %NAME%!".replace("%NAME%", employee.getFirstName());
        String subject = "Happy Birthday!";
        sendMessage(smtpHost, smtpPort, "sender@here.com", subject, body, recipient);
    }

    private void sendMessage(String smtpHost, int smtpPort, String sender, String subject, String body, String recipient) throws AddressException, MessagingException {
        // Create a mail session
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        Session session = Session.getInstance(props, null);

        // Construct the message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(sender));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        msg.setSubject(subject);
        msg.setText(body);

        // Send the message
        Transport.send(msg);
    }
}
