package com.company.controller.commands;

import com.company.controller.Pagination;

import javax.servlet.http.HttpServletRequest;

public class CommandUtils {

    public static void setCurrentPageForPagination(HttpServletRequest request,
                                                   Pagination<?> pagination) {
        String pageParameter = request.getParameter("page");
        if (pageParameter != null) {
            pagination.setCurrentPage(Integer.parseInt(pageParameter));
        }
    }
}
