package it.xpug.kata.birthday_greetings;

import static org.junit.Assert.*;

import org.junit.*;

import com.dumbster.smtp.*;

import java.util.Iterator;


public class AcceptanceTest {

	private static final int NONSTANDARD_PORT = 9978;
	private GreetingService greetingService;
	private SimpleSmtpServer mailServer;

	@Before
	public void setUp() throws Exception {
		mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT);
		greetingService = new GreetingService();
	}

	@After
	public void tearDown() throws Exception {
		mailServer.stop();
		Thread.sleep(200);
	}

	@Test
	public void willSendGreetings_whenItsSomebodysBirthday() throws Exception {

		greetingService.sendGreetings("employee_data.csv", new XDate("2008/10/08"), "localhost", NONSTANDARD_PORT);

		assertEquals("message not sent?", 1, mailServer.getReceivedEmailSize());
		SmtpMessage message = (SmtpMessage) mailServer.getReceivedEmail().next();
		assertEquals("Happy Birthday, dear John!", message.getBody());
		assertEquals("Happy Birthday!", message.getHeaderValue("Subject"));
		String[] recipients = message.getHeaderValues("To");
		assertEquals(1, recipients.length);
		assertEquals("john.doe@foobar.com", recipients[0].toString());
	}

	@Test
	public void willSendGreetingsByFax_whenItsSomebodysBirthday() throws Exception {
		greetingService.sendGreetings("employee_data.csv", new XDate("2008/03/11"), "localhost", NONSTANDARD_PORT);
		assertEquals("message not sent?", 2, mailServer.getReceivedEmailSize());
		SmtpMessage message = new SmtpMessage();
		Iterator receivedEmail = mailServer.getReceivedEmail();
		while (receivedEmail.hasNext()) {
			message = (SmtpMessage) receivedEmail.next();
		}
		String body = "[emailaccount]=greetingsKata@jugtaa.org[password]=hacksprint2016[mittente]=Mary[emailrisposta]=mary.ann@foobar.com[numerofax]=+390102172011[contenutofax]=Happy Birthday, dear Mary!";
		assertEquals(body, message.getBody());
		assertEquals("INVIOFAXDAEMAIL", message.getHeaderValue("Subject"));
		String[] recipients = message.getHeaderValues("To");
		assertEquals(1, recipients.length);
		assertEquals("inviafax@jugtaa2016.org", recipients[0].toString());
	}

	@Test
	public void willSendGreetings_whenItsEaster() throws Exception {

		greetingService.sendGreetings("employee_data.csv", new XDate("2008/03/23"), "localhost", NONSTANDARD_PORT);

		assertEquals("message not sent?", 4, mailServer.getReceivedEmailSize());
		SmtpMessage message = (SmtpMessage) mailServer.getReceivedEmail().next();
		assertEquals("Harry Brown ti facciamo un augurio di una Pasqua serena.", message.getBody());
		assertEquals("Auguri di Buona Pasqua!", message.getHeaderValue("Subject"));
		String[] recipients = message.getHeaderValues("To");
		assertEquals(1, recipients.length);
		assertEquals("harry.b@mycorp.com", recipients[0].toString());
	}

	@Test
	public void willNotSendEmailsWhenNobodysBirthday() throws Exception {
		greetingService.sendGreetings("employee_data.csv", new XDate("2008/01/01"), "localhost", NONSTANDARD_PORT);

		assertEquals("what? messages?", 0, mailServer.getReceivedEmailSize());
	}

	@Test
	public void willSendGreetings_whenItsSomebodysBirthdayReadingFromJSON() throws Exception {

		greetingService.sendGreetings("employee_data.json", new XDate("2008/10/08"), "localhost", NONSTANDARD_PORT);

		assertEquals("message not sent?", 1, mailServer.getReceivedEmailSize());
		SmtpMessage message = (SmtpMessage) mailServer.getReceivedEmail().next();
		assertEquals("Happy Birthday, dear John!", message.getBody());
		assertEquals("Happy Birthday!", message.getHeaderValue("Subject"));
		String[] recipients = message.getHeaderValues("To");
		assertEquals(1, recipients.length);
		assertEquals("john.doe@foobar.com", recipients[0].toString());
	}
}
