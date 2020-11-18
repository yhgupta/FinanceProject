package com.oracle.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.oracle.model.customer.Customer;
public class Email {

	public void sendEmailToUser(Customer customer, String applicationId) {
		String to = customer.getEmail();
		final String username = "";
		final String password = "";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("Hello " + customer.getName());
			message.setText("Dear ," + customer.getName() + "\n\n Your applicationId is: " + applicationId
					+ "\n Track it on: http://localhost:8080/FinanceProject/applicationId.html");
			Transport.send(message);

			to = username;
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setText("Loan Application Wating " + applicationId
					+ "Track it on: http://localhost:8080/FinanceProject/applicationId.html");
			Transport.send(message);
			System.out.println("Done");
		}

		catch (MessagingException e) {
			System.out.println("Username or Password are incorrect ... exiting !");
			e.printStackTrace();
		}
	}
}