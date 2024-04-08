package com.smartlock.Business.User;

import com.smartlock.Business.entities.User;
import com.smartlock.Infra.Database;
import java.util.List;
import java.util.ArrayList;

public class UserManager {

    Database data;

    public UserManager(Database database) {
        data = database;
    }

    public void registerUserController(User usuario) {

        data.saveUser(usuario);
    }

    public List<User> listUserController() {
        List<User> users = new ArrayList();

        users = data.getUsers();
        return users;
    }

}
