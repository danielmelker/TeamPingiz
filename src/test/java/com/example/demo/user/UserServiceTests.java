package com.example.demo.user;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    public void testRegisterUser() {
        User u = User.of("testUserNNNN", "testPassword");
        Assert.assertEquals(true, userService.register(u));
        Assert.assertEquals(false, userService.register(u));
    }

    @Test
    public void testLogIn() {
        User u = User.of("testUser", "testPassword");
        userService.register(u);

        AuthResponse response = userService.logIn("testUser", "wrongPassword");
        Assert.assertEquals(false, response.isValid());
        Assert.assertEquals(ResponseType.INVALID_PASSWORD, response.getType());

        response = userService.logIn("wrongUser", "wrongPassword");
        Assert.assertEquals(false, response.isValid());
        Assert.assertEquals(ResponseType.INVALID_USER, response.getType());

        response = userService.logIn("testUser", "testPassword");
        Assert.assertEquals(true, response.isValid());
        Assert.assertEquals(ResponseType.VALID, response.getType());

    }

    @Test
    public void testGetUser() {
        User u = User.of("testUserTest", "testPassword");
        userService.register(u);

        Assert.assertEquals(u, userService.getUser("testUserTest"));
        Assert.assertNull(userService.getUser("nonexisting user"));

    }

}


