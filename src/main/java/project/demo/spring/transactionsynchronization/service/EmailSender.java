package project.demo.spring.transactionsynchronization.service;

public interface EmailSender {

	void sendEmail(String recipient, String subject, String body);
}
