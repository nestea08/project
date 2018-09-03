package com.company.controller.commands.user;

import com.company.controller.commands.Command;
import com.company.controller.InputValidator;
import com.company.model.entities.interfaces.TimeTracking;
import com.company.model.exceptions.InvalidSpentTimeException;
import com.company.model.exceptions.UnknownTrackingException;
import com.company.model.services.users.TimeTrackingService;

import javax.servlet.http.HttpServletRequest;

public class TimeTrackingCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String language = request.getParameter("language");
        if(language != null) {
            return request.getContextPath() + "/redirect/user/activities?language=" + language;
        }
        int userId = UserCommandUtils.getUserIdFromSession(request.getSession());
        TimeTracking timeTracking = UserCommandUtils.getTrackedItemFromSession(request.getSession());
        request.setAttribute("timeTracking", timeTracking);
        String spentTime = request.getParameter("spentTime");
        if (spentTime != null) {
            return handleTimeTracking(request, timeTracking.getId(), userId, spentTime);
        }
        return "/user/activity.jsp";
    }

    private String handleTimeTracking(HttpServletRequest request,
                                      int activityId, int userId, String spentTime) {
        try {
            checkTimeTracking(request, userId, activityId, spentTime);
        }
        catch (UnknownTrackingException e) {
            return UserCommandUtils.setExceptionAttributeAndGetRedirectPath(e, request);
        }
        catch (Exception e) {
            request.setAttribute("exception", e.getLocalizedMessage());
            return "/user/activity.jsp";
        }
        return request.getContextPath() + "/redirect/user/time_tracking";
    }

    private void checkTimeTracking(HttpServletRequest request, int userId, int activityId,
                                   String spentTime) throws InvalidSpentTimeException {
        TimeTrackingService service = new TimeTrackingService();
        checkSpentTimeValidity(spentTime);
        TimeTracking timeTracking = service.trackTime(userId, activityId, Integer.parseInt(spentTime));
        UserCommandUtils.saveTrackedItemInSession(request.getSession(), timeTracking);
    }

    private void checkSpentTimeValidity(String spentTime) throws InvalidSpentTimeException {
        if (InputValidator.strNotMatchesRegex(spentTime, InputValidator.SPENT_TIME_REGEX )) {
            throw new InvalidSpentTimeException();
        }
    }
}
