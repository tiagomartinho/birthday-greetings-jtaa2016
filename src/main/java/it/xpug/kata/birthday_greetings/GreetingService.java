package it.xpug.kata.birthday_greetings;

import it.xpug.kata.birthday_greetings.EmployeeParser.EmployeeParser;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class GreetingService {

    public void sendGreetings(String fileName, XDate xDate, String smtpHost, int smtpPort) throws IOException, ParseException, MessagingException {
        List<Employee> employees = EmployeeParser.parse(fileName);

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
