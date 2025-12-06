package controller;

import dao.PartnerDAO;
import dto.Partner;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    private PartnerDAO partnerDAO = new PartnerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // DB에서 최근 등록된 3명 불러오기
        List<Partner> partners = partnerDAO.getRecentPartners();
        request.setAttribute("partners", partners);

        // ✅ JSP는 WEB-INF 안에서 포워딩만 가능 (직접 접근 불가)
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/main.jsp");
        rd.forward(request, response);
    }
}

