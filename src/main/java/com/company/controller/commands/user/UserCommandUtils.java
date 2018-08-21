package com.company.controller.commands.user;

import javax.servlet.http.HttpSession;


class UserCommandUtils {
    static int getUserIdFromSession(HttpSession session) {
        return Integer.parseInt(session.getAttribute("userId").toString());
    }
}
