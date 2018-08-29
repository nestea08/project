package com.company.controller.commands.admin;

import com.company.controller.commands.Command;
import com.company.model.exceptions.UnknownRequestException;
import com.company.model.services.admins.RequestProcessingService;

import javax.servlet.http.HttpServletRequest;

public class ExecuteUserRequestCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        RequestProcessingService service = new RequestProcessingService();
        int requestId = Integer.parseInt(request.getParameter("id"));
        try {
            service.executeUserRequest(requestId);
        } catch (UnknownRequestException e) {
            request.getSession().setAttribute("exception", e.getLocalizedMessage());
            return request.getContextPath() + "/redirect/admin/admin_exception.jsp";
        }
        return request.getContextPath() + "/redirect/admin/request_executed.jsp";
    }
}
