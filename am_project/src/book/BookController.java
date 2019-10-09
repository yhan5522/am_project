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

@WebServlet(description = "book Controller 서블릿", urlPatterns = { "/book/BookController" })
public class BookController extends HttpServlet {

	// 객체 직렬화(Serializable), 이클립스에서 자동 생성은 클래스 이름에서 Ctrl + 1
	private static final long serialVersionUID = 1L;

	// _jspxFactory 생성
	private static final javax.servlet.jsp.JspFactory _jspxFactory = javax.servlet.jsp.JspFactory.getDefaultFactory();
	
	
	/******************************************************************************************/
	// GET 요청을 처리하기 위한 메서드
	/******************************************************************************************/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// doPost()로 포워딩.
		doPost(request, response);
	}

	
	/******************************************************************************************/
	// POST 요청을 처리하기 위한 메서드
	/******************************************************************************************/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    // pageContext 참조변수 선언
		// javax.servlet.jsp.PageContext pageContext = null;
		PageContext pageContext = null;
		
		try {

		   // pageContext 참조변수 객체 생성
			pageContext = _jspxFactory.getPageContext(this, request, response, null, true, 8192, true);
		
			
			// 한글 인코딩
			request.setCharacterEncoding("UTF-8");
			
			// 클라이언트 응답시 전달될 컨텐트에 대한 타잎 설정과 캐릿터셋 지정
			response.setContentType("text/html; charset=UTF-8");
	
			// action구분 등 파라메터
			String action = request.getParameter("action");
			System.out.println("Controller action = " + action);


			//클라이언트 응답을 위한 출력 스트림 확보(alert 메세지용)
			PrintWriter out = response.getWriter();
			
			// accountTransferDTO 변수 정의
			BookDTO bookDTO = new BookDTO();
			
			/* 조회조건 값 설정            */			
			String searchBookName = request.getParameter("searchBookName");
				
			if(searchBookName == null) {
				searchBookName = "";
			}
			
			bookDTO.setSearchBookName(searchBookName);

			// accountTransferDAO 변수 정의
			BookDAO bookDAO = new BookDAO();

			// action에 따라 모델 호출		
			if(action.equals("list")) {

				// 계좌이체 조회결과
				List<BookDTO> bookList = bookDAO.getDBList(bookDTO.getSearchBookName());

				// List를 setAttribute
				request.setAttribute("bookList", bookList);

				pageContext.forward("book_list.jsp");

			} else {
				
				out.println("<script>alert('action 파라미터를 확인해 주세요!!!')</script>");
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}