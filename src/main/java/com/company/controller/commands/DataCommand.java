package com.company.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class DataCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        /*List<User> guests = new ArrayList<>();
        Collections.addAll(guests,
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
        Pagination<User> paginator = new Pagination<>(guests, 3);
        String pageParameter = request.getParameter("page");
        if (pageParameter != null) {
            paginator.setCurrentPage(Integer.parseInt(pageParameter));
        }
        request.setAttribute("guests", paginator.getItemsForCurrentPage());
        request.setAttribute("pagesCount", paginator.getPagesCount());*/
        return "/data.jsp";
    }
}
