package com.company.controller.commands.user;

import com.company.controller.Pagination;
import com.company.controller.commands.Command;
import com.company.model.entities.interfaces.TrackedItem;
import com.company.model.services.users.TimeTrackingService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class FindActivitiesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        TimeTrackingService service = new TimeTrackingService();
        int id = UserCommandUtils.getUserIdFromSession(request.getSession());
        List<TrackedItem> trackedItems = service.getTrackedItems(id);
        Pagination<TrackedItem> pagination = new Pagination<>(trackedItems, 5);
        setCurrentPageForPagination(request, pagination);
        request.setAttribute("trackedItems", pagination.getItemsForCurrentPage());
        request.setAttribute("pagesCount", pagination.getPagesCount());
        return "/user/activities.jsp";
    }

    private void setCurrentPageForPagination(HttpServletRequest request, Pagination<TrackedItem> pagination) {
        String pageParameter = request.getParameter("page");
        if (pageParameter != null) {
            pagination.setCurrentPage(Integer.parseInt(pageParameter));
        }
    }

}
