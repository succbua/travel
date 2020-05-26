package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/favorite/*")
public class FavoriteServlet extends baseServlet {
    private FavoriteService favoriteService = new FavoriteServiceImpl();
    public void isfavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int uid = 0;
        if (user == null) {
            uid = 0;
        } else {
            uid = user.getUid();

        }
        boolean isfavorite = favoriteService.findFavorite(rid, uid);

    }
}
