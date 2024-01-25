package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    User user = new User();

    Statement state;


    {
        try {
            state = Util.connect().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserDaoHibernateImpl() {


    }


    @Override
    public void createUsersTable() {

        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS USER (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(64), lastName VARCHAR (65), age int (3))";
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS USER";
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void saveUser(String name, String lastName, byte age) {


        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("rollback!");
            }
            e.printStackTrace();
            System.out.println("print now");
        }


    }

    @Override
    public void removeUserById(long id) {

        Session session = Util.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String hql = "DELETE FROM User u WHERE u.id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();

    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM User";
        Query query = session.createQuery(hql);
        List<User> users = query.list();
        transaction.commit();
        return users;
    }

    @Override
    public void cleanUsersTable() {

        Session session = Util.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String hql = "DELETE FROM User";
        Query query = session.createQuery(hql);
        query.executeUpdate();
        transaction.commit();
    }
}
