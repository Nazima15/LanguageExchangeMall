package controller;

import dao.NoticeDAO;
import dto.Notice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/notice")
public class NoticeListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private NoticeDAO noticeDAO = new NoticeDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 공지 전체 목록 가져오기
        List<Notice> list = noticeDAO.getAllNotices();

        // JSP로 전달
        req.setAttribute("list", list);

        // 화면 출력
        req.getRequestDispatcher("/WEB-INF/views/notice/list.jsp").forward(req, resp);
    }
}
