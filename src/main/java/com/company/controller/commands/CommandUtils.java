package com.company.controller.commands;

import com.company.controller.Pagination;
import com.company.model.entities.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class CommandUtils {
    static void saveUserInSession(HttpSession session, User user) {
        session.setAttribute("user", user.getNickname());
        session.setAttribute("role", user.getRole());
        session.setAttribute("userId", user.getId());
        session.setAttribute("email", user.getLogin());
    }

    static void removeUserFromSession(HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("role");
        session.removeAttribute("userId");
        session.removeAttribute("email");
    }

    static boolean isLogged(ServletContext context, String email) {
        return getLoggedUsers(context).contains(email);
    }

    static void logUser(ServletContext context, User user) {
        HashSet<String> loggedUsers = getLoggedUsers(context);
        loggedUsers.add(user.getLogin());
    }

    static void removeLoggedUser(ServletContext context, String email) {
        HashSet<String> loggedUsers = getLoggedUsers(context);
        loggedUsers.remove(email);
    }

    public static void setCurrentPageForPagination(HttpServletRequest request,
                                                   Pagination<?> pagination) {
        String pageParameter = request.getParameter("page");
        if (pageParameter != null) {
            pagination.setCurrentPage(Integer.parseInt(pageParameter));
        }
    }

    private static HashSet<String> getLoggedUsers(ServletContext context) {
        Object obj = context.getAttribute("loggedUsers");
        if (!(obj instanceof HashSet)) {
            throw new IllegalArgumentException("Logged users are not initialized");
        }
        return (HashSet<String>) obj;
    }

}
