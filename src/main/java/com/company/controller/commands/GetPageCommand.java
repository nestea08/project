package com.company.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class GetPageCommand implements Command {
    private String page;

    public GetPageCommand(String page) {
        this.page = page;
    }

    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/" + page;
    }
}
