package com.company.controller;

import com.company.controller.commands.*;

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
    public void init() throws ServletException {
        getServletContext().setAttribute("loggedUsers", new HashSet<String>());
        commands = new HashMap<>();
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("exception", new ExceptionCommand());
        commands.put("data", new DataCommand());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String page = getRequestedPage(req, resp);
        req.getRequestDispatcher(page).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String page = getRequestedPage(req, resp);
        resp.sendRedirect(page);
    }

    private String getRequestedPage(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getRequestURI();
        path = path.replaceAll(".*/tracker/", "");
        Command command = commands.getOrDefault(path, l -> "/index.jsp");
        return command.execute(req);
    }
}
