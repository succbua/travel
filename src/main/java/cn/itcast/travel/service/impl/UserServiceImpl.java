package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao dao =  new UserDaoImpl();
    @Override
    public boolean regist(User user) {
        /**
         * 注册用户
         */
        User u = dao.findByUsername(user.getUsername());
        if(u!=null){
            return false;
        }
        dao.save(user);
        return true;
    }
}
