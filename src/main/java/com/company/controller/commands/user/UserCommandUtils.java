package com.company.controller.commands.user;

import com.company.model.entities.interfaces.TimeTracking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


class UserCommandUtils {
    static int getUserIdFromSession(HttpSession session) {
        return Integer.parseInt(session.getAttribute("userId").toString());
    }

    static TimeTracking getTrackedItemFromSession(HttpSession session) {
        Object obj = session.getAttribute("trackedItem");
        if (!(obj instanceof TimeTracking)) {
            throw new RuntimeException();
        }
        return (TimeTracking)obj;
    }

    static void saveTrackedItemInSession(HttpSession session, TimeTracking item) {
        session.setAttribute("trackedItem", item);
    }

    static String setExceptionAttributeAndGetRedirectPath(Throwable throwable,
                                                          HttpServletRequest request) {
        request.getSession().setAttribute("exception", throwable.getLocalizedMessage());
        return request.getContextPath() + "/redirect/user/user_exception_page";
    }
}
