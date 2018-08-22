package com.company.controller.commands.admin;

import com.company.controller.commands.Command;
import com.company.model.services.admins.RequestProcessingService;

import javax.servlet.http.HttpServletRequest;

public class ExecuteUserRequestCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        RequestProcessingService service = new RequestProcessingService();
        int requestId = Integer.parseInt(request.getParameter("id"));
        service.executeUserRequest(requestId);
        return "/admin/request_executed.jsp";
    }
}
