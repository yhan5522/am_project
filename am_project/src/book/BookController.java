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

	// ��ü ����ȭ(Serializable), ��Ŭ�������� �ڵ� ������ Ŭ���� �̸����� Ctrl + 1
	private static final long serialVersionUID = 1L;

	// _jspxFactory ����
	private static final javax.servlet.jsp.JspFactory _jspxFactory = javax.servlet.jsp.JspFactory.getDefaultFactory();
	
	
	/******************************************************************************************/
	// GET ��û�� ó���ϱ� ���� �޼���
	/******************************************************************************************/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// doPost()�� ������.
		doPost(request, response);
	}

	
	/******************************************************************************************/
	// POST ��û�� ó���ϱ� ���� �޼���
	/******************************************************************************************/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    // pageContext �������� ����
		// javax.servlet.jsp.PageContext pageContext = null;
		PageContext pageContext = null;
		
		try {

		   // pageContext �������� ��ü ����
			pageContext = _jspxFactory.getPageContext(this, request, response, null, true, 8192, true);
		
			
			// �ѱ� ���ڵ�
			request.setCharacterEncoding("UTF-8");
			
			// Ŭ���̾�Ʈ ����� ���޵� ����Ʈ�� ���� Ÿ�� ������ ĳ���ͼ� ����
			response.setContentType("text/html; charset=UTF-8");
	
			// action���� �� �Ķ����
			String action = request.getParameter("action");
			System.out.println("Controller action = " + action);


			//Ŭ���̾�Ʈ ������ ���� ��� ��Ʈ�� Ȯ��(alert �޼�����)
			PrintWriter out = response.getWriter();
			
			// accountTransferDTO ���� ����
			BookDTO bookDTO = new BookDTO();
			
			/* ��ȸ���� �� ����            */			
			String searchBookName = request.getParameter("searchBookName");
				
			if(searchBookName == null) {
				searchBookName = "";
			}
			
			bookDTO.setSearchBookName(searchBookName);

			// accountTransferDAO ���� ����
			BookDAO bookDAO = new BookDAO();

			// action�� ���� �� ȣ��		
			if(action.equals("list")) {

				// ������ü ��ȸ���
				List<BookDTO> bookList = bookDAO.getDBList(bookDTO.getSearchBookName());

				// List�� setAttribute
				request.setAttribute("bookList", bookList);

				pageContext.forward("serch_results.jsp");

			} else {
				
				out.println("<script>alert('action �Ķ���͸� Ȯ���� �ּ���!!!')</script>");
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}