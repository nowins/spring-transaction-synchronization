package project.demo.spring.transactionsynchronization.service;

public interface UserService {

	/**
	 * @param email
	 * @param name
	 * @return
	 */
	User createUser(String email, String name);
}
