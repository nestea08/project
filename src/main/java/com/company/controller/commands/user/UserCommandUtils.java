package com.company.controller.commands.user;

import com.company.model.entities.interfaces.TrackedItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


class UserCommandUtils {
    static int getUserIdFromSession(HttpSession session) {
        return Integer.parseInt(session.getAttribute("userId").toString());
    }

    static TrackedItem getTrackedItemFromSession(HttpSession session) {
        Object obj = session.getAttribute("trackedItem");
        if (!(obj instanceof TrackedItem)) {
            throw new RuntimeException();
        }
        return (TrackedItem)obj;
    }

    static void saveTrackedItemInSession(HttpSession session, TrackedItem item) {
        session.setAttribute("trackedItem", item);
    }

    static String setExceptionAttributeAndGetRedirectPath(Throwable throwable,
                                                          HttpServletRequest request) {
        request.getSession().setAttribute("exception", throwable.getLocalizedMessage());
        return request.getContextPath() + "/redirect/user/user_exception.jsp";
    }
}
