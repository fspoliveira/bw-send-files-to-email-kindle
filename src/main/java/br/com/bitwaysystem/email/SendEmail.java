package br.com.bitwaysystem.email;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import br.com.bitwaysystem.bean.Email;

public class SendEmail {

	public void sendEmailWithAttachements(Email email) {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", email.getHost().getServer());
		props.put("mail.smtp.port", email.getHost().getPort());

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email.getHost().getUsername(), email.getHost().getPassword());
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(email.getFrom()));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));

			// Set Subject: header field
			message.setSubject(email.getSubject());

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Now set the actual message
			messageBodyPart.setText(email.getMessageBodyPart());

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();

			// Attachemnts
			if (email.getAttachementsFile() != null && !email.getAttachementsFile().isEmpty()) {

				for (File file : email.getAttachementsFile()) {
					addAttachment(multipart, file);
				}
			}

			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			// throw new RuntimeException(e);
			System.out.println(e);
		}
	}

	private static void addAttachment(Multipart multipart, File file) throws MessagingException {
		DataSource source = new FileDataSource(file.getAbsolutePath());
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(file.getName());
		multipart.addBodyPart(messageBodyPart);
	}
}