package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {

    public User findByUsername(String name);

    public void save(User user);

    public User findByUsercode(String code);

    void updataStatus(User user);
}
