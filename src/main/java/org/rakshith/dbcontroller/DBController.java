package org.rakshith.dbcontroller;

import org.rakshith.useractions.mysqluseractions.MysqlUserActions;
import org.rakshith.useractions.UserActions;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class DBController implements UserActions {

    UserActions ua = new MysqlUserActions();

    @Override @ShellMethod("To create user ex: create-user [10-digit phone number][name][age][location]")
    public String createUser(String phoneNumber, String name, int age, String location) {
        return ua.createUser(phoneNumber, name, age, location);
    }

    @Override @ShellMethod("To update user ex: create-user [10-digit phone number][name][age][location]")
    public String updateUser(String phoneNumber, String name, int age, String location) {
        return ua.updateUser(phoneNumber, name, age, location);
    }

    @Override @ShellMethod("To delete user ex: delete-user [10-digit phone number]")
    public String deleteUser(String phoneNumber) {
        return ua.deleteUser(phoneNumber);
    }

    @Override @ShellMethod("To show user details ex: show-user [10-digit phone number]")
    public String showUser(String phoneNumber) {
        return ua.showUser(phoneNumber);
    }
}
