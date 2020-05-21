package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategorServiceImpl implements CategoryService {
    private CategoryDaoImpl categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        Jedis jedis = JedisUtil.getJedis();
        Set<String> categorys = jedis.zrange("category", 0, -1);
        List<Category> cs = null;
        if(categorys==null||categorys.size()==0){
            cs = categoryDao.findAll();
            for (Category c : cs) {
                jedis.zadd("category",c.getCid(),c.getCname());
            }
        }else {
            cs = new ArrayList<Category>();
            for (String c : categorys) {
                Category category = new Category();
                category.setCname(c);
                cs.add(category);
            }
        }

        return cs ;
    }
}
