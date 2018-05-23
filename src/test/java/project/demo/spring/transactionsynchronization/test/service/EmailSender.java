package project.demo.spring.transactionsynchronization.test.service;

public interface EmailSender {

	void sendEmail(String recipient, String subject, String body);
}
