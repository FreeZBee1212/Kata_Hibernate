package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl udji = new UserDaoJDBCImpl();
    UserDaoHibernateImpl userHiber = new UserDaoHibernateImpl();



    public void createUsersTable() {

        udji.createUsersTable();
    }

    public void dropUsersTable() {

        udji.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {

        udji.saveUser(name, lastName, age);
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {

        udji.removeUserById(id);
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            list = udji.getAllUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void cleanUsersTable() {

        udji.cleanUsersTable();
    }
}
