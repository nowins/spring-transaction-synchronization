package project.demo.spring.transactionsynchronization.test.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.demo.spring.transactionsynchronization.executor.CommitExecutor;
import project.demo.spring.transactionsynchronization.test.service.EmailSender;
import project.demo.spring.transactionsynchronization.test.service.User;
import project.demo.spring.transactionsynchronization.test.service.UserCache;
import project.demo.spring.transactionsynchronization.test.service.UserDao;
import project.demo.spring.transactionsynchronization.test.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserCache userCache;
	
	@Autowired
	private EmailSender emailSender;
	
	@Autowired
	private CommitExecutor afterCommitExecutor;

	@Override
	@Transactional
	public User createUser(final String email, final String name) {
		
		LOGGER.info("Creating new user with email={} and name={}", email, name);

		User user = new User(email, name);

		// user will be added to cache after commit, annotation way
		userCache.add(user);

		// email will be sent after commit, programmatic way
		afterCommitExecutor.execute(new Runnable() {
			@Override
			public void run() {
				emailSender.sendEmail(email, "Welcome", "Welcome aboard dear " + name);
			}
		});

		userDao.persist(user);

		return user;
	}

}
