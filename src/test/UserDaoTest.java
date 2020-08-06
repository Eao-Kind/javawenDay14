package test;

import cn.itcast.user.dao.UserDao;
import cn.itcast.user.domain.User;

public class UserDaoTest {
    public static void main(String[] args) {
        UserDaoTest userDaoTest = new UserDaoTest();
        // userDaoTest.testFindByUsername();
        userDaoTest.testAdd();
    }

    public void testFindByUsername() {
        UserDao userDao = new UserDao();
        User user = userDao.findByUsername("张三");
        System.out.println(user);
    }

    public void testAdd() {
        UserDao userDao = new UserDao();
        User user = new User();
        user.setUsername("王六");
        user.setPassword("liqiang");
        userDao.add(user);
    }

}
