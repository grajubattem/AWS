package com.raju.lamba.demo;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class AmazonSESSample2 {

	static final String FROM = "grajubattem1011@gmail.com";
	static final String FROMNAME = "Raju";
	static final String TO = "kishorenand59@gmail.com";
	static final String SMTP_USERNAME = "AKIARHBDVUAKWHDWIV2L";
	static final String SMTP_PASSWORD = "BAxDXcMnpndxZk6B5GSBPib1Q8t0QUpCYMn2fUwcYxq5";
	static final String CONFIGSET = "rajuemail";
	static final String HOST = "email-smtp.ap-south-1.amazonaws.com";
	static final int PORT = 587;
	static final String SUBJECT = "Amazon SES test (SMTP interface accessed using Java)";
	static final String BODY = String.join(System.getProperty("line.separator"), "<h1>Amazon SES SMTP Email Test</h1>",
			"<p>This email was sent with Amazon SES using the ",
			"<a href='https://github.com/javaee/javamail'>Javamail Package</a>",
			" for <a href='https://www.java.com'>Java</a>.");

	public static void main(String[] args) throws Exception {
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);

		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(FROM, FROMNAME));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
		msg.setSubject(SUBJECT);
		msg.setContent(BODY, "text/html");

		BodyPart messageBodyPart = new MimeBodyPart();

		messageBodyPart.setText("This is message body");

		Multipart multipart = new MimeMultipart();

		messageBodyPart = new MimeBodyPart();
		String filename = "C:/Users/Raju/Downloads/Oauth11.png";
		DataSource source = new FileDataSource(filename);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(filename);
		multipart.addBodyPart(messageBodyPart);

		msg.setContent(multipart);

		msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);

		Transport transport = session.getTransport();

		try {
			System.out.println("Sending...");

			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Email sent!");
		} catch (Exception ex) {
			System.out.println("The email was not sent.");
			System.out.println("Error message: " + ex.getMessage());
		} finally {
			transport.close();
		}
	}
}