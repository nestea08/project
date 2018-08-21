package com.company.controller.commands.user;

import com.company.controller.Paginator;
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
        Paginator<TrackedItem> paginator = new Paginator<>(trackedItems, 5);
        setCurrentPageForPaginator(request, paginator);
        request.setAttribute("trackedItems", paginator.getItemsForCurrentPage());
        request.setAttribute("pagesCount", paginator.getPagesCount());
        return "/user/activities.jsp";
    }

    private void setCurrentPageForPaginator(HttpServletRequest request, Paginator<TrackedItem> paginator) {
        String pageParameter = request.getParameter("page");
        if (pageParameter != null) {
            paginator.setCurrentPage(Integer.parseInt(pageParameter));
        }
    }

}
