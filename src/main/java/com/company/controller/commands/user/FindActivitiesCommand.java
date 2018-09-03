package com.company.controller.commands.user;

import com.company.controller.Pagination;
import com.company.controller.commands.Command;
import com.company.controller.commands.CommandUtils;
import com.company.model.entities.interfaces.TimeTracking;
import com.company.model.services.users.TimeTrackingService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class FindActivitiesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        TimeTrackingService service = new TimeTrackingService();
        int id = UserCommandUtils.getUserIdFromSession(request.getSession());
        List<TimeTracking> timeTrackings = service.getTrackings(id);
        Pagination<TimeTracking> pagination = new Pagination<>(timeTrackings, 4);
        CommandUtils.setCurrentPageForPagination(request, pagination);
        request.setAttribute("timeTrackings", pagination.getItemsForCurrentPage());
        request.setAttribute("pagesCount", pagination.getPagesCount());
        return "/user/activities.jsp";
    }

}
