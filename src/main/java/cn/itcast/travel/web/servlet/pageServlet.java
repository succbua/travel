package cn.itcast.travel.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pageServlet/*")
public class pageServlet extends baseServlet {
    public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");

        int currentPage = 0;
        if(currentPageStr==null || currentPageStr.length()==0){
            currentPage=1;
        }else {
            currentPage=Integer.parseInt(currentPageStr);
        }
        int pageSize = 0;
        if(pageSizeStr==null || pageSizeStr.length()==0){
            pageSize=1;
        }else {
            pageSize=Integer.parseInt(pageSizeStr);
        }

        int cid = 0;
        if(cidStr==null || cidStr.length()==0){
            cid=1;
        }else {
            cid=Integer.parseInt(cidStr);
        }


    }

}
