package cn.itcast.user.service;

import cn.itcast.user.dao.UserDao;
import cn.itcast.user.domain.User;

public class UserService {
    private UserDao userDao = new UserDao();

    /**
     * 注册功能
     */
    public void regist(User user) throws UserException {
        /*
         * 使用用户名查询若null则完成添加否则throw异常
         */
        User __user = userDao.findByUsername(user.getUsername());
        if (__user != null) throw new UserException("用户名" + user.getUsername() + "已被添加");
        userDao.add(user); // 添加新用户
    }

    public User login(User form) throws UserException {
        User user = userDao.findByUsername(form.getUsername());
        if (user == null) {
            throw new UserException("用户名不存在");
        }
        if (!form.getPassword().equals(user.getPassword())) {
            throw new UserException("密码错误");
        }
        return user;
    }
}
