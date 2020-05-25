package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends baseServlet {
    private RouteService service = new RouteServiceImpl();

    public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        String rname = request.getParameter("rname");
        rname = new String(rname.getBytes("iso-8859-1"), "utf-8");
        System.out.println(rname);
        int currentPage = 0;
        if (currentPageStr == null || currentPageStr.length() == 0) {
            currentPage = 1;
        } else {
            currentPage = Integer.parseInt(currentPageStr);
        }
        int pageSize = 0;
        if (pageSizeStr == null || pageSizeStr.length() == 0) {
            pageSize = 5;
        } else {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        int cid = 0;
        if (cidStr != null && cidStr.length() != 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }

        PageBean<Route> page = service.pageQuery(cid, currentPage, pageSize, rname);
        writeValue(page, response);


    }
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        System.out.println(rid);
        Route route = service.findOne(rid);
        writeValue(route, response);

    }

}
