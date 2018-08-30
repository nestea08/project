package com.company.model.tests;

import com.company.model.entities.*;
import com.company.model.entities.interfaces.TrackedItem;
import com.company.model.exceptions.DuplicateRequestException;
import com.company.model.exceptions.InvalidSpentTimeException;
import com.company.model.exceptions.NotUniqueNicknameException;
import com.company.model.exceptions.UnknownRequestException;
import com.company.model.services.admins.AdminsUtils;
import com.company.model.services.admins.RequestProcessingService;
import com.company.model.services.users.*;
import com.company.model.services.guests.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class AdminServicesTests {
    private AdminsUtils adminsUtils = mock(AdminsUtils.class);
    private TrackerUser testingUser;
    private Activity testingActivity;
    private Request testingRequest;

    @Before
    public void init() throws InvalidSpentTimeException, UnknownRequestException {
        testingUser = new TrackerUser.Builder("user123", "user@mail.ru", "1111").build();
        testingActivity = new Activity(1, "activity1", "......");
        testingRequest = new Request(testingUser, testingActivity, Request.RequestType.ADD);
        when(adminsUtils.getUserRequestById(1)).thenReturn(testingRequest);
    }

    @Test
    public void requestProcessingServiceExecuteUserRequestTest() throws UnknownRequestException {
        RequestProcessingService service = new RequestProcessingService(adminsUtils);
        service.executeUserRequest(1);
        verify(adminsUtils,times(1)).executeAdditionRequest(testingRequest);
    }

    @Test
    public void requestProcessingServiceRefuseUserRequestTest() throws UnknownRequestException {
        RequestProcessingService service = new RequestProcessingService(adminsUtils);
        service.refuseUserRequest(1);
        verify(adminsUtils,times(1)).deleteUserRequest(testingRequest);
    }

    @Test
    public void activityCreationServiceCreateActivityTest() throws Exception {
        SignInService service = new SignInService();
        service.findUser("nestea08@yandex.ru", "1111");
    }
}