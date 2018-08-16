package com.company.controller.commands;

import com.company.controller.Paginator;
import com.company.model.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        List<User> users = new ArrayList<>();
        Collections.addAll(users,
                new User("qqqqq@gmail.com", "qqqqqq", User.Role.USER),
                new User("wwwww@gmail.com", "wwwwww", User.Role.USER),
                new User("eeeee@gmail.com", "eeeeee", User.Role.USER),
                new User("rrrrr@gmail.com", "rrrrrr", User.Role.USER),
                new User("qqqqq@gmail.com", "qqqqqq", User.Role.USER),
                new User("wwwww@gmail.com", "wwwwww", User.Role.USER),
                new User("eeeee@gmail.com", "eeeeee", User.Role.USER),
                new User("rrrrr@gmail.com", "rrrrrr", User.Role.USER),
                new User("qqqqq@gmail.com", "qqqqqq", User.Role.USER),
                new User("qqqqq@gmail.com", "qqqqqq", User.Role.USER)
        );
        Paginator<User> paginator = new Paginator<>(users, 3);
        String pageParameter = request.getParameter("page");
        if (pageParameter != null) {
            paginator.setCurrentPage(Integer.parseInt(pageParameter));
        }
        request.setAttribute("users", paginator.getItemsForCurrentPage());
        request.setAttribute("pagesCount", paginator.getPagesCount());
        return "/data.jsp";
    }
}
