package com.company.model.tests;

import com.company.model.entities.Activity;
import com.company.model.entities.TimeTrackedItem;
import com.company.model.entities.TrackerUser;
import com.company.model.entities.User;
import com.company.model.exceptions.InvalidSpentTimeException;
import com.company.model.exceptions.NotUniqueNicknameException;
import com.company.model.exceptions.UnknownUserException;
import com.company.model.services.guests.GuestsUtils;
import com.company.model.services.guests.SignInService;
import com.company.model.services.guests.SignUpService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;

/**
 * Created by Asus on 30.08.2018.
 */
public class GuestServicesTests {
    private GuestsUtils guestsUtils = mock(GuestsUtils.class);
    private TrackerUser testingUser;

    @Before
    public void init() throws InvalidSpentTimeException {
        testingUser = new TrackerUser.
                Builder("user123", "user@mail.ru", "1111").build();
        when(guestsUtils.getUserByLoginAndPassword("user@mail.ru", "1111")).
                thenReturn(testingUser);
        when(guestsUtils.getAllUsers()).thenReturn(Arrays.asList(
                new User.Builder("firstUser", "user1@mail.ru", "user1", User.Role.USER).build(),
                new User.Builder("secondUser", "user2@mail.ru", "user2", User.Role.USER).build()
        ));
    }

    @Test
    public void signInServiceFindUserTest() throws Exception {
        SignInService service = new SignInService(guestsUtils);
        User user = service.findUser("user@mail.ru", "1111");
        Assert.assertEquals("user123", user.getNickname());
    }

    @Test(expected = UnknownUserException.class)
    public void signInServiceFindUserUnknownUserExceptionTest()
            throws UnknownUserException {
        SignInService service = new SignInService(guestsUtils);
        service.findUser("unknown", "unknown");
    }

    @Test
    public void signUpServiceCreateUserTest() throws Exception {
        SignUpService service = new SignUpService(guestsUtils);
        service.createUser("user123", "user@mail.ru", "1111");
        verify(guestsUtils, times(1)).createUser(testingUser);
    }

    @Test(expected = NotUniqueNicknameException.class)
    public void signUpServiceCreateUserExceptionTest() throws Exception {
        SignUpService service = new SignUpService(guestsUtils);
        service.createUser("firstUser", "user", "1111");
    }
}
