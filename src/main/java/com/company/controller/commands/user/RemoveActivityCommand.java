package com.company.controller.commands.user;

import com.company.controller.commands.Command;
import com.company.model.entities.Request;
import com.company.model.services.users.RequestsSaverService;

import javax.servlet.http.HttpServletRequest;

public class RemoveActivityCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        RequestsSaverService service = new RequestsSaverService();
        int activityId = Integer.parseInt(request.getParameter("id"));
        int userId = UserCommandUtils.getUserIdFromSession(request.getSession());
        service.saveTrackerUserRequest(userId, activityId, Request.RequestType.REMOVE);
        return "/user/remove.jsp";
    }
}
