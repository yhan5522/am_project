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

//어너테이션 서블릿과 URL 정의
@WebServlet(description = "book Controller 서블릿", urlPatterns = { "/book/BookController" })
public class BookController extends HttpServlet {

	// 객체 직렬화(Serializable), 이클립스에서 자동 생성은 클래스 이름에서 Ctrl + 1
	private static final long serialVersionUID = 1L;

	// _jspxFactory 생성
	private static final javax.servlet.jsp.JspFactory _jspxFactory = javax.servlet.jsp.JspFactory.getDefaultFactory();
	
	/**
	 * 	@fn			public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	 *	@brief		GET 요청을 처리
	 *	@details
	 *
	 *	@author		한예나
	 *	@date		2019-09-26
	 *
	 *	@param		HttpServletRequest request 		request 정보를 서블릿에 전달
	 *				HttpServletResponse response	응답 메세지 서블릿에 전달
	 *  
	 *	@remark		
	 */
	// GET 요청을 처리하기 위한 메서드
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doPost()로 포워딩.
		doPost(request, response);
	}
	
	/**
	 * 	@fn			public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	 *	@brief		POST 요청 처리
	 *	@details
	 *
	 *	@author		한예나
	 *	@date		2019-10-10
	 *
	 *	@param		HttpServletRequest request 		request 정보를 서블릿에 전달
	 *				HttpServletResponse response	응답 메세지 서블릿에 전달
	 *  
	 *	@remark		페이지 연결을 위해 pageContext 선언
	 *				구분을 위해 action 선언
	 *				DTO 참조 위해 bookDTO 선언
	 *				DAO 참조 위해 bookDAO 선언
	 *				조회 조건 설정 위해 searchBookName 선언
	 *				조회 결과 저장을 위해 bookList 선언
	 *		[2019-09-26; 한예나]
	 */
	// POST 요청을 처리하기 위한 메서드
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// pageContext 참조변수 선언
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
			
			BookDTO bookDTO = new BookDTO();
			BookDAO bookDAO = new BookDAO();

			//조회조건 값 설정		
			String searchBookName = request.getParameter("searchBookName");
			if(searchBookName == null) {
				searchBookName = "";
			}
			bookDTO.setSearchBookName(searchBookName);
	
			if(action.equals("list")) {
				// 조회결과
				List<BookDTO> bookList = bookDAO.getDBList(bookDTO.getSearchBookName());
				request.setAttribute("bookList", bookList);
				pageContext.forward("serch_results.jsp");
			} else {	
				out.println("<script>alert('action 파라미터를 확인해 주세요')</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}