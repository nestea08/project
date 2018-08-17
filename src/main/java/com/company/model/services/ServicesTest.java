package com.company.model.services;

import com.company.model.entities.Activity;
import com.company.model.entities.TrackerUser;
import com.company.model.entities.User;
import com.company.model.entities.UserRequest;
import com.company.model.exceptions.NotUniqueNicknameException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class ServicesTest {

    private ServiceUtils utils;
    private TrackerUser testingUser;
    private Activity testingActivity;

    @Before
    public void init() {
        utils = mock(ServiceUtils.class);
        testingUser = new TrackerUser.Builder("user123", "user@mail.ru", "1111").build();
        testingActivity = new Activity("activity1", "......");
    }

    @Test
    public void signInServiceFindUserTest() {
        when(utils.getUserByLoginAndPassword("user@mail.ru", "1111")).
                thenReturn(testingUser);
        SignInService service = new SignInService(utils);
        User user = service.findUser("user@mail.ru", "1111");
        Assert.assertEquals("user123", user.getNickname());
    }

    @Test
    public void signUpServiceCreateUserTest() throws Exception {
        SignUpService service = new SignUpService(utils);
        service.createUser("user123", "user@mail.ru", "1111");
        verify(utils, times(1)).createUser(testingUser);
    }

    @Test(expected = NotUniqueNicknameException.class)
    public void signUpServiceCreateUserExceptionTest() throws Exception {
        when(utils.getAllUsers()).thenReturn(Arrays.asList(
                new User.Builder("firstUser", "user1@mail.ru", "user1", User.Role.USER).build(),
                new User.Builder("secondUser", "user2@mail.ru", "user2", User.Role.USER).build()
        ));
        SignUpService service = new SignUpService(utils);
        service.createUser("firstUser", "user", "1111");
    }

    @Test
    public void timeTrackingServiceTrackTimeTest() {
        TimeTrackingService service = new TimeTrackingService(utils);
        testingUser.addActivity(testingActivity);
        when(utils.getTrackerUserById(1)).thenReturn(testingUser);
        service.trackTime(1, "activity1", 10);
        Assert.assertEquals(testingUser.getSpentTimeOnActivity(testingActivity).intValue(), 10);
        verify(utils, times(1)).updateSpentTime(testingUser, testingActivity);
    }

    @Test
    public void userRequestsServiceAddUserRequestTest() {
        UserRequestsService service = new UserRequestsService(utils);
        when(utils.getTrackerUserById(1)).thenReturn(testingUser);
        when(utils.getActivityById(1)).thenReturn(testingActivity);
        service.sendUserRequest(1,1, UserRequest.RequestType.ADD);
        verify(utils, times(1)).
                createUserRequest(new UserRequest(testingUser, testingActivity, UserRequest.RequestType.ADD));
    }

    @Test
    public void requestProcessingServiceExecuteUserRequestTest() {
        RequestProcessingService service = new RequestProcessingService(utils);
        when(utils.getUserRequestById(1)).thenReturn(new UserRequest(testingUser, testingActivity, UserRequest.RequestType.ADD));
        service.executeUserRequest(1);
        verify(utils,times(1)).addActivityToTrackerUser(testingUser, testingActivity);
    }

    @Test
    public void requestProcessingServiceRefuseUserRequestTest() {
        RequestProcessingService service = new RequestProcessingService(utils);
        UserRequest request = new UserRequest(testingUser, testingActivity, UserRequest.RequestType.ADD);
        when(utils.getUserRequestById(1)).thenReturn(request);
        service.refuseUserRequest(1);
        verify(utils,times(1)).deleteUserRequest(request);
    }

}