package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;


public class Main {
    public static void main(String[] args) {

        // Hibernate подключение

        UserDaoHibernateImpl userHiber = new UserDaoHibernateImpl();


        userHiber.dropUsersTable();                     // 1) удаление таблицы

        userHiber.createUsersTable();                   // 2) создание таблицы

        userHiber.saveUser("Artur", "Pirogov", (byte) 15);
        userHiber.saveUser("Dmitriy", "Dench", (byte) 27);    // 3) добавление данных в таблицу
        userHiber.saveUser("Petya", "Gaish", (byte) 32);
        userHiber.saveUser("Boris", "Nadeshdin", (byte) 60);

        System.out.println(userHiber.getAllUsers().toString());                 // 4) вывод на экран всех данных

//      userHiber.cleanUsersTable();                    //   5) удаление данных таблицы
//      userHiber.removeUserById(1);                    //   6) удаление данных по id


        // JDBC подключение

//        UserServiceImpl userService = new UserServiceImpl();
//
//        userService.dropUsersTable();           // 1) удаление таблицы
//        userService.createUsersTable();         // 2) создание таблицы
//        userService.saveUser("'Ivan'", "'Ivanov'", (byte) 22);
//        userService.saveUser("'gfdfh'", "'Gres'", (byte) 25);
//        userService.saveUser("'Petya'", "'Gipopotam'", (byte) 22);
//        userService.saveUser("'Johny'", "'Tree'", (byte) 25);
//        System.out.println(userService.getAllUsers());             // 3) вывод в консоль всех юзеров
//        userService.cleanUsersTable();         // 4) удаление данных в таблице
//        userService.dropUsersTable();          // 5) удаление таблицы


    }
}
