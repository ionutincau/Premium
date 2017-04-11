package Login;

import Employees.Employee;
import Employees.EmployeesProvider;

/**
 * Created by Incau Ionut on 14-Mar-17.
 * Contact: ionut.incau@gmail.com
 */

public class LoginController {

    private EmployeesProvider provider;
    private Employee user;

    public LoginController() {
        provider = new EmployeesProvider();
    }

    public void login (String username, String password) throws Exception {
        if (username.equals("") || password.equals("")) {
            throw new Exception("Please fill required fields!");
        }
        Employee user = provider.getUser(username);
        if (user == null) {
            throw new Exception("This username doesn't exist!");
        }
        else if (!user.getPassword().equals(password)) {
            throw new Exception("Wrong password!");
        }
        this.user = user;
    }

    public Employee getUser() {
        return this.user;
    }
}
