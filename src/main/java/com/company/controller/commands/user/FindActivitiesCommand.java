package com.company.controller.commands.user;

import com.company.controller.Paginator;
import com.company.controller.commands.Command;
import com.company.model.entities.interfaces.Tracked;
import com.company.model.services.users.TimeTrackingService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class FindActivitiesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        TimeTrackingService service = new TimeTrackingService();
        int id = Integer.parseInt(request.getSession().getAttribute("userId").toString());
        Map<Tracked, Integer> trackedItems = service.getTrackedItems(id);
        Paginator<Tracked> paginator = new Paginator<>(new ArrayList<>(trackedItems.keySet()), 5);
        String pageParameter = request.getParameter("page");
        if (pageParameter != null) {
            paginator.setCurrentPage(Integer.parseInt(pageParameter));
        }
        request.setAttribute("activities", zipToMap(paginator.getItemsForCurrentPage(), trackedItems));
        request.setAttribute("pagesCount", paginator.getPagesCount());
        return "/user/activities.jsp";
    }

    private static Map<Tracked, Integer> zipToMap(List<Tracked> list, Map<Tracked,Integer> map) {
        Map<Tracked, Integer> result = new HashMap<>();
        for (Tracked tracked: map.keySet()) {
            if(list.contains(tracked)) {
                result.put(tracked, map.get(tracked));
            }
        }
        return result;
    }
}
