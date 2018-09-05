package com.company.controller;


import com.company.controller.commands.Command;
import com.company.controller.commands.ExceptionCommand;
import com.company.controller.commands.GetPageCommand;
import com.company.controller.commands.admin.*;
import com.company.controller.commands.guest.*;
import com.company.controller.commands.user.*;

import java.util.HashMap;
import java.util.Map;

class CommandsInitializer {
    private Map<String, Command> commands;

    CommandsInitializer(){
        commands = new HashMap<>();
        commands.put("exception", new ExceptionCommand());
        commands.put("guest/login_page", new GetPageCommand(PagesPaths.LOGIN));
        commands.put("guest/login", new LoginCommand());
        commands.put("guest/logout", new LogoutCommand());
        commands.put("guest/sign_up_page", new GetPageCommand(PagesPaths.SIGN_UP));
        commands.put("guest/sign_up", new SignUpCommand());
        commands.put("user/activities", new FindActivitiesCommand());
        commands.put("user/activity", new ActivityTrackingCommand());
        commands.put("user/time_tracking", new TimeTrackingCommand());
        commands.put("user/activity_remove", new RemoveActivityCommand());
        commands.put("user/activity_finish", new FinishActivityCommand());
        commands.put("user/possible_activities", new GetPossibleActivitiesCommand());
        commands.put("user/user_page", new GetPageCommand(PagesPaths.USER_HOME));
        commands.put("user/start_activity", new StartActivityCommand());
        commands.put("user/activity_started_page", new GetPageCommand(PagesPaths.ACTIVITY_STARTED));
        commands.put("user/activity_removed_page", new GetPageCommand(PagesPaths.ACTIVITY_REMOVED));
        commands.put("user/user_exception_page", new GetPageCommand(PagesPaths.USER_EXCEPTION));
        commands.put("admin/admin_page", new GetPageCommand(PagesPaths.ADMIN_HOME));
        commands.put("admin/create_activity_page", new GetPageCommand(PagesPaths.CREATE_ACTIVITY));
        commands.put("admin/activity_created_page", new GetPageCommand(PagesPaths.ACTIVITY_CREATED));
        commands.put("admin/request_executed_page", new GetPageCommand(PagesPaths.REQUEST_EXECUTED));
        commands.put("admin/request_refused_page", new GetPageCommand(PagesPaths.REQUEST_REFUSED));
        commands.put("admin/requests", new ShowUserRequestsCommand());
        commands.put("admin/history", new ShowTrackingHistoryCommand());
        commands.put("admin/execute_request", new ExecuteUserRequestCommand());
        commands.put("admin/refuse_request", new RefuseUserRequestCommand());
        commands.put("admin/create_activity", new CreateActivityCommand());
        commands.put("admin/admin_exception_page", new GetPageCommand(PagesPaths.ADMIN_EXCEPTION));
    }

    Map<String, Command> getCommands() {
        return commands;
    }
}
