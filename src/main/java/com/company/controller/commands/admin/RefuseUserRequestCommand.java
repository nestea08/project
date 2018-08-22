package com.company.controller.commands.admin;

import com.company.controller.commands.Command;
import com.company.model.services.admins.RequestProcessingService;

import javax.servlet.http.HttpServletRequest;

public class RefuseUserRequestCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        RequestProcessingService service = new RequestProcessingService();
        int requestId = Integer.parseInt(request.getParameter("id"));
        service.refuseUserRequest(requestId);
        return "/admin/request_refused.jsp";
    }
}
