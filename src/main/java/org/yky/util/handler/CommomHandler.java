package org.yky.util.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.yky.util.exception.CommomException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hp on 2018/7/30.
 */
@ControllerAdvice
public class CommomHandler {

    @ExceptionHandler(CommomException.class)
    public void CommonHandle(HttpServletRequest request, HttpServletResponse response, CommomException ex) {
        System.out.println(request.getParameter("bbs"));
        System.out.println(ex.getA());
        try {
            response.getWriter().write("aa");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
