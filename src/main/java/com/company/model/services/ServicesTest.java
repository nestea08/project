package com.company.model.services;

import com.company.model.entities.User;
import com.company.model.services.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ServicesTest {

    private ServiceUtils utils;

    @Before
    public void init() {
        utils = mock(ServiceUtils.class);
        when(utils.getUserByLoginAndPassword("user","1111")).
                thenReturn(new User.Builder("user123","user","1111", User.Role.USER).build());
}

    @Test
    public void signInServiceFindUserTest() {
        SignInService service = new SignInService(utils);
        User user = service.findUser("user", "1111");
        Assert.assertEquals("user123", user.getNickname());
    }

    @Test
    public void signUpServiceCreateUserTest() throws Exception {
        SignUpService service = new SignUpService(utils);
        service.createUser("user123","user","1111");
        verify(utils,times(1)).createUser
                (new User.Builder("user123","user","1111", User.Role.USER).build());
    }
}