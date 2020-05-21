package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.ServerException;

public class baseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServerException{
        String uri = req.getRequestURI();
        System.out.println("request:"+uri);
        String methodname = uri.substring(uri.lastIndexOf("/") + 1);
        System.out.println("methodname:"+methodname);
        System.out.println(this); //userServletde 的对象
        try {
            Method method = this.getClass().getDeclaredMethod(methodname, HttpServletRequest.class, HttpServletResponse.class);
            try {
                method.setAccessible(true);
                method.invoke(this,req,resp);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }

    /**
     * object to json
     * @param obj
     */
    public void writeValue(Object obj,HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        mapper.writeValue(resp.getOutputStream(),obj);


    }
    public String writeValueAsString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);

    }
}
