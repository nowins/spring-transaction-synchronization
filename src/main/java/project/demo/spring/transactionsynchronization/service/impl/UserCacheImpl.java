package project.demo.spring.transactionsynchronization.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import project.demo.spring.transactionsynchronization.annotation.AfterCommit;
import project.demo.spring.transactionsynchronization.service.User;
import project.demo.spring.transactionsynchronization.service.UserCache;

@Service
public class UserCacheImpl implements UserCache {

	private static Logger LOGGER = LoggerFactory.getLogger(UserCacheImpl.class);
	
	@Override
	@AfterCommit
	public void add(User user) {
		LOGGER.debug("Inserting into cache: {}", user);
	}

}
