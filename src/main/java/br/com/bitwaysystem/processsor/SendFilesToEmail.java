package br.com.bitwaysystem.processsor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import br.com.bitwaysystem.bean.Email;
import br.com.bitwaysystem.bean.Host;
import br.com.bitwaysystem.email.SendEmail;

public class SendFilesToEmail {

	private static final int MAX_ATTACHEMENTS = 10;
	private static final String FROM = "fspo1@hotmail.com";
	private static final String PASSWORD = "************";
	private static final String SERVER_NAME = "smtp.live.com";
	private static final String SERVER_PORT = "587";
	private static final String TO = "fspoliveira@Kindle.com";

	@SuppressWarnings("unchecked")
	public void sendFilesToEmail(Map.Entry pairs) {

		if ("AZW3".equals((String) pairs.getKey().toString().toUpperCase())
				|| "EPUB".equals(pairs.getKey().toString().toUpperCase())
				|| "MOBI".equals(pairs.getKey().toString().toUpperCase())
				|| "PDF".equals(pairs.getKey().toString().toUpperCase())) {

			// System.out.println(pairs.getKey() + " = " + pairs.getValue());
			System.out.println("Sending to email files with extension " + pairs.getKey());

			List<File> tempAttachments = new ArrayList<File>();

			for (File file : (List<File>) pairs.getValue()) {

				System.out.println(file.getAbsolutePath());

				tempAttachments.add(file);

				//TODO Tamanho de anexo permitido na Amazon é de 25, porém o Hotmail tem um limite em MB que eu não sei quanto é
				//É necessário descobrir este tamanho máximo de anexos para quebrar os anexos em mais e-mails
				if (tempAttachments.size() == MAX_ATTACHEMENTS) {
					System.out.println("Sending " + tempAttachments.size() + " files");

					try {

						SendEmail sendEmail = new SendEmail();
						sendEmail.sendEmailWithAttachements(this.createEmail((String) pairs.getKey(), tempAttachments));

						tempAttachments = new ArrayList<File>();

						System.out.println("Waiting 5 minutes to send a new email ");
						// Wait for 5 minutos to send a new email
						Thread.sleep(300001);

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.println(e);
					}
				}
			}

			System.out.println("Sending " + tempAttachments.size() + " files");
			SendEmail sendEmail = new SendEmail();
			sendEmail.sendEmailWithAttachements(this.createEmail((String) pairs.getKey(), tempAttachments));

		}
	}

	public Email createEmail(String fileType, List<File> attachementsFile) {

		Email email = new Email();
		Host host = new Host();

		// Create Host
		host.setUsername(SendFilesToEmail.FROM);
		host.setPassword(SendFilesToEmail.PASSWORD);
		host.setServer(SendFilesToEmail.SERVER_NAME);
		host.setPort(SendFilesToEmail.SERVER_PORT);

		// Create email
		email.setFrom(SendFilesToEmail.FROM);
		email.setTo(SendFilesToEmail.TO);
		email.setHost(host);
		email.setMessageBodyPart("Body Part");
		email.setSubject(!"MOBI".equals(fileType.toUpperCase()) ? "convert" : "");
		email.setAttachementsFile(attachementsFile);

		return email;
	}
}
