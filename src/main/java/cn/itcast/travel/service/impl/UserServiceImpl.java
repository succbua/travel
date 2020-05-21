package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.EmailController;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDao dao =  new UserDaoImpl();
    @Override
    public boolean regist(User user) {
        EmailController email = new EmailController();
        /**
         * 注册用户
         */
        User u = dao.findByUsername(user.getUsername());
        if(u != null){
            return false;
        }

        user.setCode(UuidUtil.getUuid());//激活码
        user.setStatus("N");
        dao.save(user);
        String url= "<a href='http://localhost:8080/travel/activeUserServlet?code="+user.getCode()+"'>激活</a>";
        email.setMessage(url);
        email.setSendto(user.getEmail());
        email.setSubject("激活邮件");
        email.sendEmail();
        return true;
    }

    @Override
    public boolean active(String code) {
        User user = dao.findByUsercode(code);
        if (user != null){
            dao.updataStatus(user);
            return true;
        }else {
            return false;
        }


    }

    @Override
    public User login(User user) {
        System.out.println(user.getPassword());
        System.out.println(user.getUsername());
        User u = dao.findByUsername(user.getUsername());
        System.out.println(u);
        if (u!=null){
            if(u.getPassword().equals(user.getPassword())){
                return u;
            }
        }
        return null;
    }
}
