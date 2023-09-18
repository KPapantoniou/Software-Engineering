package myy803.springboot.sb_tutorial_7_signup_signin.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.mockito.Mockito.verify;



import myy803.springboot.sb_tutorial_7_signup_signin.dao.UserDAO;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Role;
import myy803.springboot.sb_tutorial_7_signup_signin.model.User;
import myy803.springboot.sb_tutorial_7_signup_signin.service.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@Mock
    private UserDAO userDAO;
	  
	@Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIsUserPresent() {
        User user = new User();
        user.setUsername("john");

        when(userDAO.findByUsername("john")).thenReturn(Optional.of(user));

        boolean isPresent = userService.isUserPresent(user);

        assertTrue(isPresent);
    }

    @Test
    public void testIsUserNotPresent() {
        User user = new User();
        user.setUsername("john");

        when(userDAO.findByUsername("john")).thenReturn(Optional.empty());

        boolean isPresent = userService.isUserPresent(user);

        assertFalse(isPresent);
    }

    @Test
    public void testFindById() {
        User user = new User();
        user.setId(1);
        user.setUsername("john");

        when(userDAO.findById("1")).thenReturn(Optional.of(user));

        Optional<User> result = userService.findById("1");

        assertTrue(result.isPresent());
        assertEquals(user.getId(), result.get().getId());
        assertEquals(user.getUsername(), result.get().getUsername());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameUserNotFound() {
        when(userDAO.findByUsername("john")).thenReturn(Optional.empty());

        userService.loadUserByUsername("john");
    }

    @Test
    public void testLoadUserByUsername() {
        User user = new User();
        user.setUsername("john");
        user.setPassword("password");
        user.setRole(Role.STUDENT);

        when(userDAO.findByUsername("john")).thenReturn(Optional.of(user));

        User result = (User) userService.loadUserByUsername("john");

        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getPassword(), result.getPassword());
        assertEquals(user.getRole(), result.getRole());
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("john");

        userService.updateUser(user);

    }
}

