package controller;

import dto.User;
import dao.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/PartnerServlet")
public class PartnerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();
            User loginUser = (User) session.getAttribute("user");
            UserDAO userDAO = new UserDAO();

            // 모든 사용자 목록 가져오기
            List<User> partnerList = userDAO.getAllPartners();

            // 로그인한 사용자 본인 제외
            if (loginUser != null) {
                partnerList.removeIf(u -> u.getUserId() == loginUser.getUserId());
            }

            // 필터 (언어)
            String nativeLang = request.getParameter("native_lang");
            String learnLang = request.getParameter("learn_lang");

            if ((nativeLang != null && !nativeLang.isEmpty()) ||
                (learnLang != null && !learnLang.isEmpty())) {

                partnerList = partnerList.stream()
                        .filter(u -> nativeLang == null || nativeLang.isEmpty() ||
                                (u.getNativeLang() != null && u.getNativeLang().equals(nativeLang)))
                        .filter(u -> learnLang == null || learnLang.isEmpty() ||
                                (u.getLearnLang() != null && u.getLearnLang().equals(learnLang)))
                        .collect(Collectors.toList());
            }

            // JSP 전달 데이터 설정
            request.setAttribute("partnerList", partnerList);
            request.setAttribute("selectedNative", nativeLang);
            request.setAttribute("selectedLearn", learnLang);

            // 페이지 이동
            RequestDispatcher dispatcher = request.getRequestDispatcher("/partners.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("파트너 목록 불러오기 오류: " + e.getMessage());
        }
    }
}


