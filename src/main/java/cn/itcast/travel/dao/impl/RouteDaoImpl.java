package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(int cid, String rname) {
        String sql = "select count(*) from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid);

        }
        if (rname != null && rname.length() != 0) {
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        sql = sb.toString();

        Integer totalCount = template.queryForObject(sql, Integer.class, params.toArray());
        return totalCount;
    }

    @Override
    public List<Route> findList(int cid, int start, int pageSize, String rname) {
        String sql = "select * from tab_route where 1=1 ";

        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if (rname != null && rname.length() != 0) {
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        sb.append(" limit ?,?");
        params.add(start);
        params.add(pageSize);
        sql = sb.toString();
        List<Route> list = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
        return list;
    }
}