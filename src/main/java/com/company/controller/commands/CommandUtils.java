package com.company.controller.commands;

import com.company.model.entities.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class CommandUtils {
    static void saveUserInSession(HttpSession session, String login, User.Role role) {
        session.setAttribute("user", login);
        session.setAttribute("role", role);
    }

    static boolean isLogged(ServletContext context, String login) {
        return getLoggedUsers(context).contains(login);
    }

    static void logUser(ServletContext context, String login) {
        HashSet<String> loggedUsers = getLoggedUsers(context);
        loggedUsers.add(login);
    }

    private static HashSet<String> getLoggedUsers(ServletContext context) {
        Object obj = context.getAttribute("loggedUsers");
        if (!(obj instanceof HashSet<?>)) {
            throw new IllegalArgumentException("Logged users are not initialized");
        }
        return (HashSet<String>) obj;
    }
}
