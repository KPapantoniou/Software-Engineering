package myy803.springboot.sb_tutorial_7_signup_signin.model.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import myy803.springboot.sb_tutorial_7_signup_signin.model.Role;
import myy803.springboot.sb_tutorial_7_signup_signin.model.User;

import java.util.Collections;
@SpringBootTest
public class UserTest {

    @Test
    public void testGetAuthorities() {
        User user = new User();
        user.setRole(Role.PROFESSOR);

        Assertions.assertEquals(Collections.singletonList(new SimpleGrantedAuthority(Role.PROFESSOR.name())),
                user.getAuthorities());
    }

    @Test
    public void testIsAccountNonExpired() {
        User user = new User();

        Assertions.assertTrue(user.isAccountNonExpired());
    }

    @Test
    public void testIsAccountNonLocked() {
        User user = new User();

        Assertions.assertTrue(user.isAccountNonLocked());
    }

    @Test
    public void testIsCredentialsNonExpired() {
        User user = new User();

        Assertions.assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        User user = new User();

        Assertions.assertTrue(user.isEnabled());
    }
}
