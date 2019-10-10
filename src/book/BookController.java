package book;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

@WebServlet(description = "book Controller ����", urlPatterns = { "/book/BookController" })
public class BookController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final javax.servlet.jsp.JspFactory _jspxFactory = javax.servlet.jsp.JspFactory.getDefaultFactory();
	
	// GET
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// doPost()�� ������.
		doPost(request, response);
	}

	// POST
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageContext pageContext = null;
		
		try {
			pageContext = _jspxFactory.getPageContext(this, request, response, null, true, 8192, true);
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
	
			String action = request.getParameter("action");
			System.out.println("Controller action = " + action);

			PrintWriter out = response.getWriter();

			BookDTO bookDTO = new BookDTO();

			if((action.equals("insert"))) {

				bookDTO.setBookName(request.getParameter("bookName"));
				bookDTO.setAuthor(request.getParameter("author"));
				bookDTO.setPublishingHouse(request.getParameter("publishingHouse"));
				bookDTO.setCategory(request.getParameter("category"));
				bookDTO.setSymbol(request.getParameter("symbol"));
				bookDTO.setStatus(request.getParameter("status"));
			}
			
			String searchBookName = request.getParameter("searchBookName");
				
			if(searchBookName == null) {
				searchBookName = "";
			}
			
			bookDTO.setSearchBookName(searchBookName);


			BookDAO bookDAO = new BookDAO();
	
			if(action.equals("add")) {

				request.setAttribute("action", action);
				
				pageContext.forward("book_view.jsp");
			
			} else if(action.equals("insert")) {
				

				if(bookDAO.insertDB(bookDTO)) {

					List<BookDTO> bookList = bookDAO.getDBList(bookDTO.getSearchBookName());

					request.setAttribute("bookList", bookList);

					pageContext.forward("book_list.jsp");
					
				} else {
					throw new Exception("DB 입력 오류");
				}
				
			} else if(action.equals("list")) {

				List<BookDTO> bookList = bookDAO.getDBList(bookDTO.getSearchBookName());

				request.setAttribute("bookList", bookList);

				pageContext.forward("book_list.jsp");

			} else {
				out.println("<script>alert('action 파라미터 확인')</script>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}