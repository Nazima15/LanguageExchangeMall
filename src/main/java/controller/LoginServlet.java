package controller;

import dao.UserDAO;
import dto.User;
import java.io.IOException;
import java.security.MessageDigest;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            User user = userDAO.findByUsername(username);
            if(user == null) {
                request.setAttribute("errorMsg", "존재하지 않는 아이디입니다.");
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            }

            // 비밀번호 체크
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for(byte b: md.digest()) sb.append(String.format("%02x", b));
            String hashedPassword = sb.toString();

            if(!hashedPassword.equals(user.getPasswordHash())) {
                request.setAttribute("errorMsg", "비밀번호가 틀렸습니다.");
                request.getRequestDispatcher("/WEB-INF/common/login.jsp").forward(request, response);
                return;
            }

            // 로그인 성공
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("main.jsp");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

