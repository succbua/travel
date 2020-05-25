package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();

    @Override
    public PageBean<Route> pageQuery(int cid, int current, int pageSize, String rname) {
        PageBean<Route> page = new PageBean<Route>();
        int totalCount = routeDao.findTotalCount(cid,rname);

        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        int start = (current-1)*pageSize;
        List<Route> list = routeDao.findList(cid, start, pageSize,rname);

        page.setCurrentPage(current);
        page.setList(list);
        page.setTotalCount(totalCount);
        page.setPageSize(pageSize);
        page.setTotalPage(totalPage);

        return page;

    }
    @Override
    public Route findOne(String rid){
        Route route = routeDao.findOne(rid);
        List<RouteImg> imgList = routeImgDao.findList(rid);
        Seller seller = sellerDao.findSeller(route.getSid());
        route.setRouteImgList(imgList);
        route.setSeller(seller);


        return route;
    }
}
