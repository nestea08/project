package com.company.model.services;

import com.company.model.entities.Activity;
import com.company.model.entities.Request;
import com.company.model.entities.TimeTrackingItem;
import com.company.model.entities.TrackerUser;
import com.company.model.entities.interfaces.TimeTracking;
import com.company.model.exceptions.DuplicateRequestException;
import com.company.model.exceptions.InvalidSpentTimeException;
import com.company.model.services.users.RequestsSaverService;
import com.company.model.services.users.TimeTrackingService;
import com.company.model.services.users.UsersUtils;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by Asus on 30.08.2018.
 */
public class UserServicesTests {
    private UsersUtils usersUtils = mock(UsersUtils.class);
    private TrackerUser testingUser;
    private Activity testingActivity;
    private TimeTracking testingTimeTracking;

    @Before
    public void init() throws InvalidSpentTimeException {
        testingUser = new TrackerUser.Builder("user123", "user@mail.ru", "1111").build();
        testingActivity = new Activity(1, "activity1", "......");
        testingTimeTracking = new TimeTrackingItem(testingActivity, 5);
        when(usersUtils.getTrackerUserById(1)).thenReturn(testingUser);
        when(usersUtils.getActivityById(1)).thenReturn(testingActivity);
    }

    @Test
    public void timeTrackingServiceTrackTimeTest() throws InvalidSpentTimeException {
        TimeTrackingService service = new TimeTrackingService(usersUtils);
        testingUser.addTracking(testingTimeTracking);
        service.trackTime(1, 1, 10);
        verify(usersUtils, times(1)).
                updateSpentTime(testingUser, new TimeTrackingItem(testingActivity, 15));
    }

    @Test(expected = InvalidSpentTimeException.class)
    public void timeTrackingServiceTrackTimeSpentTimeExceptionTest()
            throws InvalidSpentTimeException {
        TimeTrackingService service = new TimeTrackingService(usersUtils);
        testingUser.addTracking(testingTimeTracking);
        service.trackTime(1, 1, -100);
    }

    @Test
    public void timeTrackingServiceFinishActivityTest() {
        TimeTrackingService service = new TimeTrackingService(usersUtils);
        testingUser.addTracking(testingTimeTracking);
        service.finishTracking(1,1);
        verify(usersUtils, times(1)).
                transformTrackingIntoHistoryItem(testingUser, testingTimeTracking);
    }

    @Test(expected = IllegalStateException.class)
    public void timeTrackingServiceFinishActivityStateExceptionTest()
            throws InvalidSpentTimeException {
        TimeTrackingService service = new TimeTrackingService(usersUtils);
        testingUser.addTracking(new TimeTrackingItem(testingActivity, 0));
        service.finishTracking(1,1);
    }

    @Test
    public void RequestsSaverServiceSaveTrackerService()
            throws DuplicateRequestException {
        RequestsSaverService service = new RequestsSaverService(usersUtils);
        service.saveTrackerRequest(1 , 1, Request.RequestType.ADD);
        verify(usersUtils, times(1)).
                createUserRequest(new Request(testingUser, testingActivity, Request.RequestType.ADD));
    }
}
