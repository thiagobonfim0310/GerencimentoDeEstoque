package com.smartlock;

import com.smartlock.Business.Admin.AdminManager;
import com.smartlock.Business.Enviroment.EnviromentManager;
import com.smartlock.Business.Lock.LockManager;
import com.smartlock.Business.User.UserManager;
import com.smartlock.Business.adapters.ValidateEmailAdapter;
import com.smartlock.Business.validators.ValidateEmail;
import com.smartlock.Business.reports.ReportManager;

import com.smartlock.Infra.database.Database;
import com.smartlock.Infra.database.Memory;
import com.smartlock.Infra.database.SqLite;

import com.smartlock.View.LoginPage;
import com.smartlock.View.StartUpPage;
import com.smartlock.View.Enviroment.EnviromentMenu;
import com.smartlock.View.Enviroment.EnviromentPage;
import com.smartlock.View.Lock.LockMenu;
import com.smartlock.View.Lock.LockPage;
import com.smartlock.View.User.UserMenu;
import com.smartlock.View.User.UserPage;
import com.smartlock.View.Reports.ReportMenu;
import com.smartlock.View.Reports.ReportPage;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Database database = new SqLite();
        // Managers
        ValidateEmail validateEmail = new ValidateEmailAdapter();
        AdminManager adminManager = new AdminManager(database);
        UserManager userManager = new UserManager(database, validateEmail);
        LockManager lockManager = new LockManager(database);
        EnviromentManager enviromentManager = new EnviromentManager(database);
        ReportManager reportManager = new ReportManager();
        // Pages
        UserPage userPage = new UserPage(userManager);
        LockPage lockPage = new LockPage(lockManager, enviromentManager);
        EnviromentPage enviromentPage = new EnviromentPage(enviromentManager);
        ReportPage reportPage = new ReportPage(reportManager);
        // Menu
        UserMenu userMenu = new UserMenu(userPage);
        LockMenu lockMenu = new LockMenu(lockPage);
        EnviromentMenu enviromentMenu = new EnviromentMenu(enviromentPage);
        ReportMenu reportMenu = new ReportMenu(reportPage);

        StartUpPage menu = new StartUpPage(userMenu, lockMenu, enviromentMenu, reportMenu);

        LoginPage login = new LoginPage(adminManager, menu);

        login.login();
    }
}
