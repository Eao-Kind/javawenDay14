package cn.itcast.user.dao;

import cn.itcast.user.domain.User;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class UserDao {
    private String path = "D:/users.xml";

    /**
     * 按用户名查询
     *
     * @param username
     * @return user
     */
    public User findByUsername(String username) {
        /**
         * 得到document xpath查询，若null则返回null
         */
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(path); // 解析器
            Element ele = (Element) doc.selectSingleNode("//user[@username='" + username + "']");
            if (ele == null) return null;
            User user = new User();
            String attrUsername = ele.attributeValue("username");
            String attrPassword = ele.attributeValue("password");
            user.setUsername(attrUsername);
            user.setPassword(attrPassword);
            return user;
        } catch (DocumentException e) {
            throw new RuntimeException();
        }
    }

    /**
     * 添加用户
     */
    public void add(User user) {
        /**
         * 得到document的root元素users，转发乘element对象，添加到root中，保存
         */
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(path);

            Element root = doc.getRootElement();
            Element userEle = root.addElement("user");
            userEle.addAttribute("username", user.getUsername());
            userEle.addAttribute("password", user.getPassword());
            OutputFormat format = new OutputFormat("\t", true);
            format.setTrimText(true); //清除原油的换行和缩减
            XMLWriter writer = new XMLWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(path), "UTF-8"), format);
            writer.write(doc);
            writer.close();
        } catch (DocumentException | IOException e) {
            throw new RuntimeException();
        }
    }
}
