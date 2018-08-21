package com.company.controller.commands.user;

import com.company.controller.commands.Command;
import com.company.model.entities.Activity;
import com.company.model.entities.interfaces.Tracked;
import com.company.model.services.users.TimeTrackingService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ActivityTrackingCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        TimeTrackingService service = new TimeTrackingService();
        int activityId = Integer.parseInt(request.getParameter("id"));
        int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
        String spentTime = request.getParameter("spentTime");
        if (spentTime != null) {
            service.trackTime(userId, activityId, Integer.parseInt(spentTime));
            return request.getContextPath() + "/redirect/user/activity?id=" + activityId;
        }
        Map.Entry<Tracked, Integer> tracked =  service.getTrackedItemById(userId, activityId);
        Activity activity = (Activity)tracked.getKey();
        request.setAttribute("activity", activity);
        request.setAttribute("timeSpent", tracked.getValue());
        return "/user/activity.jsp";
    }
}
