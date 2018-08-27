package com.company.controller.commands.user;

import com.company.controller.Pagination;
import com.company.controller.commands.Command;
import com.company.controller.commands.CommandUtils;
import com.company.model.entities.Activity;
import com.company.model.entities.interfaces.TrackedItem;
import com.company.model.services.users.PossibleActivitiesService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetPossibleActivitiesCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        int id = UserCommandUtils.getUserIdFromSession(request.getSession());
        PossibleActivitiesService service = new PossibleActivitiesService();
        List<Activity> activities = service.getPossibleActivities(id);
        Pagination<Activity> pagination = new Pagination<>(activities, 7);
        CommandUtils.setCurrentPageForPagination(request, pagination);
        request.setAttribute("activities", pagination.getItemsForCurrentPage());
        request.setAttribute("pagesCount", pagination.getPagesCount());
        return "/user/possible_activities.jsp";
    }

}
