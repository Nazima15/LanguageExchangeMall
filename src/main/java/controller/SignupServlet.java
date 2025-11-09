package controller;

import dao.UserDAO;
import dto.User;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/signup")
@MultipartConfig(
    fileSizeThreshold = 1024*1024,
    maxFileSize = 5*1024*1024,
    maxRequestSize = 10*1024*1024
)
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String nickname = request.getParameter("nickname");

            if (userDAO.findByUsername(username) != null) {
                request.setAttribute("errorMsg", "이미 존재하는 아이디입니다.");
                request.getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
                return;
            }

            // 비밀번호 SHA-256 해시
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : md.digest()) sb.append(String.format("%02x", b));
            String hashedPassword = sb.toString();

            // 프로필 사진 처리 (선택 사항)
            Part filePart = request.getPart("profile_img");
            String fileName = null;
            if (filePart != null && filePart.getSize() > 0) {
                String uploadPath = request.getServletContext().getRealPath("/uploads");
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdirs();
                fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();
                filePart.write(uploadPath + File.separator + fileName);
            }

            User user = new User();
            user.setUsername(username);
            user.setPasswordHash(hashedPassword);
            user.setNickname(nickname);
            user.setProfileImg(fileName);
            user.setRole("USER");

            userDAO.insertUser(user);

           

            // 세션에 사용자 저장 (바로 로그인 상태로 만들기)
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // 회원가입 후 바로 메인 페이지로 이동
            response.sendRedirect("main.jsp");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

