package controller;

import dao.NoticeDAO;
import dto.Notice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/notice/detail")
public class NoticeDetailServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private NoticeDAO noticeDAO = new NoticeDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 요청 파라미터에서 notice ID 가져오기
        int id = Integer.parseInt(req.getParameter("id"));

        // 해당 ID의 공지사항 가져오기
        Notice notice = noticeDAO.getNoticeById(id);

        // JSP로 데이터 전달
        req.setAttribute("notice", notice);

        // 상세보기 페이지로 이동
        req.getRequestDispatcher("/WEB-INF/views/notice/detail.jsp").forward(req, resp);
    }
}
