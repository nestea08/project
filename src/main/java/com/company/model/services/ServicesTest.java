package com.company.model.services;

import com.company.model.entities.*;
import com.company.model.entities.interfaces.TrackedItem;
import com.company.model.exceptions.NotUniqueNicknameException;
import com.company.model.services.admins.AdminsUtils;
import com.company.model.services.admins.LogsHolderService;
import com.company.model.services.admins.RequestProcessingService;
import com.company.model.services.users.*;
import com.company.model.services.guests.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class ServicesTest {


    private UsersUtils usersUtils = mock(UsersUtils.class);
    private TrackersUtils trackersUtils = mock(TrackersUtils.class);
    private AdminsUtils adminsUtils = mock(AdminsUtils.class);
    private TrackerUser testingUser;
    private Activity testingActivity;
    private TrackedItem trackedItem;

    @Before
    public void init() {
        testingUser = new TrackerUser.Builder("user123", "user@mail.ru", "1111").build();
        testingActivity = new Activity(1, "activity1", "......");
        trackedItem = new TimeTrackedItem(testingActivity, 0);
    }

    @Test
    public void signInServiceFindUserTest() throws Exception {
        when(usersUtils.getUserByLoginAndPassword("user@mail.ru", "1111")).
                thenReturn(testingUser);
        SignInService service = new SignInService(usersUtils);
        User user = service.findUser("user@mail.ru", "1111");
        Assert.assertEquals("user123", user.getNickname());
    }

    @Test
    public void signUpServiceCreateUserTest() throws Exception {
        SignUpService service = new SignUpService(usersUtils);
        service.createUser("user123", "user@mail.ru", "1111");
        verify(usersUtils, times(1)).createUser(testingUser);
    }

    @Test(expected = NotUniqueNicknameException.class)
    public void signUpServiceCreateUserExceptionTest() throws Exception {
        when(usersUtils.getAllUsers()).thenReturn(Arrays.asList(
                new User.Builder("firstUser", "user1@mail.ru", "user1", User.Role.USER).build(),
                new User.Builder("secondUser", "user2@mail.ru", "user2", User.Role.USER).build()
        ));
        SignUpService service = new SignUpService(usersUtils);
        service.createUser("firstUser", "user", "1111");
    }

    @Test
    public void timeTrackingServiceTrackTimeTest() {
        TimeTrackingService service = new TimeTrackingService(trackersUtils);
        testingUser.addTrackedItem(trackedItem);
        when(trackersUtils.getTrackerUserById(1)).thenReturn(testingUser);
        service.trackTime(1, 1, 10);
        verify(trackersUtils, times(1)).updateSpentTime(testingUser, trackedItem.plusSpentTime(10));
    }

    @Test
    public void userRequestsServiceAddUserRequestTest() {
        RequestsSaverService service = new RequestsSaverService(trackersUtils);
        when(trackersUtils.getTrackerUserById(1)).thenReturn(testingUser);
        when(trackersUtils.getActivityById(1)).thenReturn(testingActivity);
        service.saveTrackerUserRequest(1,1, Request.RequestType.ADD);
        verify(trackersUtils, times(1)).
                createUserRequest(new Request(testingUser, testingActivity, Request.RequestType.ADD));
    }

    @Test
    public void requestProcessingServiceExecuteUserRequestTest() {
        RequestProcessingService service = new RequestProcessingService(adminsUtils);
        when(adminsUtils.getUserRequestById(1)).thenReturn(new Request(testingUser, testingActivity, Request.RequestType.ADD));
        service.executeUserRequest(1);
        verify(adminsUtils,times(1)).addActivityToTracker(testingUser, testingActivity);
    }

    @Test
    public void requestProcessingServiceRefuseUserRequestTest() {
        RequestProcessingService service = new RequestProcessingService(adminsUtils);
        Request request = new Request(testingUser, testingActivity, Request.RequestType.ADD);
        when(adminsUtils.getUserRequestById(1)).thenReturn(request);
        service.refuseUserRequest(1);
        verify(adminsUtils,times(1)).deleteUserRequest(request);
    }

    @Test
    public void test() throws Exception {
        SignInService service = new SignInService();
        service.findUser("nestea08@yandex.ru", "1111");
    }
}