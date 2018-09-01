package com.company.model.tests;

import com.company.model.entities.Activity;
import com.company.model.entities.Request;
import com.company.model.entities.TrackerUser;
import com.company.model.exceptions.InvalidSpentTimeException;
import com.company.model.exceptions.UnknownRequestException;
import com.company.model.services.admins.AdminsUtils;
import com.company.model.services.admins.RequestProcessingService;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class AdminServicesTests {
    private AdminsUtils adminsUtils = mock(AdminsUtils.class);
    private Request testingRequest;

    @Before
    public void init() throws InvalidSpentTimeException, UnknownRequestException {
        TrackerUser testingUser = new TrackerUser.Builder("user123", "user@mail.ru", "1111").build();
        Activity testingActivity = new Activity(1, "activity1", "......");
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

}