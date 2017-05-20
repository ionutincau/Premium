package Login;

import Employees.Employee;
import Employees.EmployeesProvider;

import java.util.Observable;

/**
 * Created by Incau Ionut on 14-Mar-17.
 * Contact: ionut.incau@gmail.com
 */

public class LoginController extends Observable {

    private static LoginController instance;
    private EmployeesProvider provider;
    private Employee loggedUser;
    private Employee selectedUser;

    private LoginController() {
        provider = new EmployeesProvider();
    }

    public static LoginController getInstance() {
        if (instance == null){
            instance = new LoginController();
        }
        return instance;
    }

    public void login (String username, String password) throws Exception {
        if (username.equals("") || password.equals("")) {
            throw new Exception("Va rugam completati toate campurile!");
        }
        Employee user = provider.getUser(username);
        if (user == null) {
            throw new Exception("Acest utilizator nu exista!");
        }
        else if (!user.getPassword().equals(password)) {
            throw new Exception("Parola incorecta!");
        }
        this.loggedUser = user;
        if (user.getRole().equals("user") || user.getRole().equals("hr")) this.selectedUser = user;
    }

    public void setLoggedUser(Employee loggedUser) {
        this.loggedUser = loggedUser;
    }

    public Employee getLoggedUser() {
        return this.loggedUser;
    }

    public void setSelectedUser(Employee selectedUser) {
        this.selectedUser = selectedUser;
    }

    public Employee getSelectedUser() {
        return this.selectedUser;
    }

    public void setSelectedUserChanged() {
        setChanged();
        notifyObservers();
    }
}
