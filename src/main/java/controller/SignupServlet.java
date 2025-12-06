package controller;

import dao.UserDAO;
import dto.User;
import java.io.IOException;
import java.security.MessageDigest;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/signup") // 회원가입 URL
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO(); // DB 접근 객체

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 📄 회원가입 페이지 보여주기
        request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");

            // 📌 입력값 받기
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String passwordConfirm = request.getParameter("passwordConfirm");
            String nickname = username;     // 기본 닉네임 = 아이디
            String email = request.getParameter("email");
            String level = "초보";          // 기본 레벨

            // ❗ 아이디 중복 체크
            if (userDAO.findByUsername(username) != null) {
                request.setAttribute("errorMsg", "이미 존재하는 아이디입니다.");
                request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);
                return; // 더 이상 진행 X
            }

            // ❗ 비밀번호 일치 체크
            if (!password.equals(passwordConfirm)) {
                request.setAttribute("errorMsg", "비밀번호가 일치하지 않습니다.");
                request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);
                return;
            }

            // 🔐 비밀번호 SHA-256 암호화
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : md.digest()) sb.append(String.format("%02x", b));
            String hashedPassword = sb.toString();

            // 📸 기본 프로필 이미지
            String profileImg = "default.png";

            // 📝 User 객체에 값 넣기
            User user = new User();
            user.setUsername(username);
            user.setPasswordHash(hashedPassword);
            user.setNickname(nickname);
            user.setEmail(email);
            user.setLevel(level);
            user.setProfileImg(profileImg);

            // 💾 DB에 저장
            userDAO.insertUser(user);

            // 🔑 회원가입 성공 후 → 자동 로그인 효과
            HttpSession session = request.getSession();
            session.setAttribute("user", user); // 세션에 회원 정보 저장

            // 메인 페이지로 이동
            response.sendRedirect(request.getContextPath() + "/main");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

