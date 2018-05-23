package project.demo.spring.transactionsynchronization.test.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import project.demo.spring.transactionsynchronization.test.service.EmailSender;

@Service
public class EmailSenderImpl implements EmailSender {

	private static Logger LOGGER = LoggerFactory.getLogger(EmailSenderImpl.class);
	
	@Override
	public void sendEmail(String recipient, String subject, String body) {
		LOGGER.info("Sending email to {}", recipient);
	}

}
