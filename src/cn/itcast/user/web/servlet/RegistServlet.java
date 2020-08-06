package cn.itcast.user.web.servlet;


import cn.itcast.commons.CommonUtils;
import cn.itcast.user.domain.User;
import cn.itcast.user.service.UserException;
import cn.itcast.user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        User user = new User();
        UserService userService = new UserService();
        User form = CommonUtils.toBean(request.getParameterMap(), User.class);
        /*
        表单校验 Map装置错误信息，若null则无错误
         */
        Map<String, String> errors = new HashMap<String, String>();
        // 用户名校验
        String username = form.getUsername();
        if (username == null || username.trim().isEmpty()) { // 去空格
            errors.put("username", "用户名不能为空！");
        } else if (username.length() < 3) {
            errors.put("username", "用户名长度必须在3-15");
        }
        // 密码校验
        String password = form.getPassword();
        if (password == null || password.trim().isEmpty()) { // 去空格
            errors.put("password", "密码不能为空！");
        } else if (password.length() < 3) {
            errors.put("password", "密码长度必须在3-15");
        }
        // 验证码校验
        String sessionVerifyCode = (String) request.getSession().getAttribute("session_vcode");
        String verifyCode = form.getVerifyCode();
        if (verifyCode == null || verifyCode.trim().isEmpty()) { // 去空格
            errors.put("verifyCode", "验证码不能为空！");
        } else if (verifyCode.length() != 4) {
            errors.put("verifyCode", "验证码长度必须为4");
        } else if (!verifyCode.equalsIgnoreCase(sessionVerifyCode)) {
            errors.put("verifyCode", "验证码错误");
        }
        // 判断map是否为null
        if (errors != null && errors.size() > 0) {
            request.setAttribute("errors", errors);
            request.setAttribute("user", form);
            request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
            return;
        }
        try {
            userService.regist(form);
            response.getWriter().print("<h1>注册成功!</h1><a href= ");
        } catch (UserException e) {
            e.printStackTrace();
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
