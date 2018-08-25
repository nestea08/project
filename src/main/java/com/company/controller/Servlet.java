package com.company.controller;

import com.company.controller.commands.*;
import com.company.controller.commands.admin.ExecuteUserRequestCommand;
import com.company.controller.commands.admin.RefuseUserRequestCommand;
import com.company.controller.commands.admin.ShowTrackingHistoryCommand;
import com.company.controller.commands.admin.ShowUserRequestsCommand;
import com.company.controller.commands.user.ActivityTrackingCommand;
import com.company.controller.commands.user.FindActivitiesCommand;
import com.company.controller.commands.user.FinishActivityCommand;
import com.company.controller.commands.user.RemoveActivityCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Servlet extends HttpServlet {
    private Map<String, Command> commands;

    @Override
    public void init() {
        commands = new HashMap<>();
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("signUp", new SignupCommand());
        commands.put("exception", new ExceptionCommand());
        commands.put("user/activities", new FindActivitiesCommand());
        commands.put("user/activity", new ActivityTrackingCommand());
        commands.put("user/activity_remove", new RemoveActivityCommand());
        commands.put("user/activity_finish", new FinishActivityCommand());
        commands.put("admin/requests", new ShowUserRequestsCommand());
        commands.put("admin/history", new ShowTrackingHistoryCommand());
        commands.put("admin/execute_request", new ExecuteUserRequestCommand());
        commands.put("admin/refuse_request", new RefuseUserRequestCommand());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException { ;
        String path = req.getRequestURI();
        path = path.replaceAll(".*/tracker/", "");
        Command command = commands.getOrDefault(path, l -> "/index.jsp");
        String page = command.execute(req);
        if (page.contains("redirect/")) {
            page = page.replaceAll("redirect/", "");
            resp.sendRedirect(page);
        } else {
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }
}
