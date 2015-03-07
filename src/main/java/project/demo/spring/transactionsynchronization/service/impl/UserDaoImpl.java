package project.demo.spring.transactionsynchronization.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import project.demo.spring.transactionsynchronization.service.User;
import project.demo.spring.transactionsynchronization.service.UserDao;

@Service
public class UserDaoImpl implements UserDao {
	
	private static Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

	@Override
	public void persist(User user) {
		LOGGER.debug("Persisting a new user: {}", user);
		
        if (user.getEmail() == null || user.getName() == null) {
            throw new IllegalArgumentException("User email and user name are required.");
        }
	}

}
