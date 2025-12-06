package controller;

import dao.PartnerDAO;
import dto.Partner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;

@WebServlet("/partners") 
public class PartnerServlet extends HttpServlet {

    private PartnerDAO partnerDAO = new PartnerDAO(); // DB에서 파트너 정보 가져오는 DAO

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 🔍 검색어 받기 (없으면 빈 문자열)
        String keyword = request.getParameter("keyword");
        if (keyword == null) keyword = "";

        // 📄 현재 페이지 번호 받기 (기본 1페이지)
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int pageSize = 8; // 한 페이지에 8명씩 보기
        int offset = (page - 1) * pageSize; // DB에서 가져올 시작 위치

        // 📌 DB에서 파트너 목록 가져오기 (검색 + 페이징 적용)
        List<Partner> partners = partnerDAO.getPartners(keyword, offset, pageSize);

        // 📌 전체 데이터 개수 → 총 페이지 수 계산
        int totalCount = partnerDAO.getPartnersCount(keyword);
        int totalPage = (int) Math.ceil((double) totalCount / pageSize);

        // ❤️ 찜 성공/실패 메시지 (1회성) — 세션에서 가져와서 request로 이동
        HttpSession session = request.getSession();
        Integer successId = (Integer) session.getAttribute("wishlistSuccessId");  // 찜 성공한 사용자 ID
        Integer errorId = (Integer) session.getAttribute("wishlistErrorPartnerId"); // 이미 찜함 or 실패

        request.setAttribute("wishlistSuccessId", successId); // JSP에서 메시지 표시 가능
        request.setAttribute("wishlistErrorPartnerId", errorId);

        session.removeAttribute("wishlistSuccessId"); // ⭐ 1번만 출력되도록 세션에서 삭제
        session.removeAttribute("wishlistErrorPartnerId");

        // 📌 JSP에서 사용할 데이터 넣기
        request.setAttribute("partners", partners);
        request.setAttribute("keyword", keyword);
        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);

        // 📄 partners.jsp 로 화면 출력 (서버 내부 이동)
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/partners.jsp");
        rd.forward(request, response);
    }
}

