package com.company.controller.commands;

import com.company.controller.Pagination;
import com.company.model.entities.User;
import com.company.model.entities.interfaces.TrackedItem;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Locale;

public class CommandUtils {
    static void saveUserInSession(HttpSession session, User user) {
        session.setAttribute("user", user.getNickname());
        session.setAttribute("role", user.getRole());
        session.setAttribute("userId", user.getId());
    }

    static void removeUserFromSession(HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("role");
        session.removeAttribute("userId");
    }

    static boolean isLogged(ServletContext context, String nickname) {
        return getLoggedUsers(context).contains(nickname);
    }

    static void logUser(ServletContext context, User user) {
        HashSet<String> loggedUsers = getLoggedUsers(context);
        loggedUsers.add(user.getNickname());
    }

    static void removeLoggedUser(ServletContext context, String nickname) {
        HashSet<String> loggedUsers = getLoggedUsers(context);
        loggedUsers.remove(nickname);
    }

    public static void setCurrentPageForPagination(HttpServletRequest request,
                                                   Pagination<?> pagination) {
        String pageParameter = request.getParameter("page");
        if (pageParameter != null) {
            pagination.setCurrentPage(Integer.parseInt(pageParameter));
        }
    }

    public static Locale getLocale(HttpServletRequest request) {
        return new Locale(request.getSession().getAttribute("language").toString());
    }

    private static HashSet<String> getLoggedUsers(ServletContext context) {
        Object obj = context.getAttribute("loggedUsers");
        if (!(obj instanceof HashSet<?>)) {
            throw new IllegalArgumentException("Logged guests are not initialized");
        }
        return (HashSet<String>) obj;
    }

}
