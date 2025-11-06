package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.UserDAO;
import dto.User;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            UserDAO dao = new UserDAO();
            dao.createTableIfNotExists(); // 테이블 없으면 생성
            User newUser = new User(0, username, password);
            dao.insertUser(newUser);

            // 회원가입 후 바로 로그인
            request.getSession().setAttribute("user", newUser);
            response.sendRedirect("main.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("signupError", "회원가입 중 오류 발생");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("signup.jsp");
    }
}




