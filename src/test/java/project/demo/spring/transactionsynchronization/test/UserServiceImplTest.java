package project.demo.spring.transactionsynchronization.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import project.demo.spring.transactionsynchronization.SpringConfiguration;
import project.demo.spring.transactionsynchronization.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class UserServiceImplTest {
 
    @Autowired
    private UserService userService;
 
    @BeforeClass
    public static void beforeClass() throws ClassNotFoundException {
        Class.forName("org.hsqldb.jdbcDriver");
    }
 
    @Test
    public void createUser() {
        userService.createUser("test_email", "test_name");
    }
 
    @Test
    public void createUserFailure() {
        userService.createUser(null, "test_name");
    }
 
}