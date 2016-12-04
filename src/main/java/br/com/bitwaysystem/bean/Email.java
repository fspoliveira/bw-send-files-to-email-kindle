package br.com.bitwaysystem.bean;

import java.io.File;
import java.util.List;

public class Email {

	private String to;

	private String from;

	private String subject;

	private String messageBodyPart;

	private Host host;

	List<File> attachementsFile;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessageBodyPart() {
		return messageBodyPart;
	}

	public void setMessageBodyPart(String messageBodyPart) {
		this.messageBodyPart = messageBodyPart;
	}

	public Host getHost() {
		return host;
	}

	public void setHost(Host host) {
		this.host = host;
	}

	public List<File> getAttachementsFile() {
		return attachementsFile;
	}

	public void setAttachementsFile(List<File> attachementsFile) {
		this.attachementsFile = attachementsFile;
	}
}