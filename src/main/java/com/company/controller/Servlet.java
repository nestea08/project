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
    public void init() {
        getServletContext().setAttribute("loggedUsers", new HashSet<String>());
        commands = new CommandsInitializer().getCommands();
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
            throws ServletException, IOException {
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
