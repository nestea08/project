package com.company.controller;

import com.company.controller.commands.*;
import com.company.controller.commands.admin.*;
import com.company.controller.commands.user.*;

import javax.servlet.ServletConfig;
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
        getServletContext().setAttribute("loggedUsers", new HashSet<String>());
        commands = new HashMap<>();
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("signUp", new SignupCommand());
        commands.put("exception", new ExceptionCommand());
        commands.put("user/activities", new FindActivitiesCommand());
        commands.put("user/activity", new ActivityTrackingCommand());
        commands.put("user/time_tracking", new TimeTrackingCommand());
        commands.put("user/activity_remove", new RemoveActivityCommand());
        commands.put("user/activity_finish", new FinishActivityCommand());
        commands.put("user/possible_activities", new GetPossibleActivitiesCommand());
        commands.put("user/start_activity", new StartActivityCommand());
        commands.put("admin/requests", new ShowUserRequestsCommand());
        commands.put("admin/history", new ShowTrackingHistoryCommand());
        commands.put("admin/execute_request", new ExecuteUserRequestCommand());
        commands.put("admin/refuse_request", new RefuseUserRequestCommand());
        commands.put("admin/create_activity", new CreateActivityCommand());
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
