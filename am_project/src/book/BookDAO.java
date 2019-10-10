package book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;

	// MySQL 연결정보 
	String jdbc_driver = "com.mysql.jdbc.Driver";
	
	String jdbc_url = "jdbc:mysql://127.0.0.1/jspdb?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";

	/**
	 * 	@fn			void connect()
	 *	@brief		DB 연결
	 *	@details
	 *
	 *	@author		한예나
	 *	@date		2019-09-19
	 *
	 *	@param		
	 *  
	 *	@remark	
	 */
	void connect() {
		try {
			Class.forName(jdbc_driver);

			conn = DriverManager.getConnection(jdbc_url,"jspbook","1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	@fn			void disconnect()
	 *	@brief		DB 연결 해제
	 *	@details
	 *
	 *	@author		한예나
	 *	@date		2019-09-19
	 *
	 *	@param		
	 *  
	 *	@remark	
	 */
	void disconnect() {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 	@fn			public List<BookDTO> getDBList(String searchBookName)
	 *	@brief		검색 시 결과 목록 조회
	 *	@details
	 *
	 *	@author		한예나
	 *	@date		2019-09-26
	 *
	 *	@param		String searchBookName 데이터베이스에 저장된 값
	 *  
	 *	@remark		데이터베이스의 값을 저장하기 위해 booklist 선언
	 *				데이터베이스에서 select하기 위해 sql 선언
	 *				sql문을 실행하기 위해 rs 선언
	 *				select 결과를 저장하기 위해 bookDTO 선언			[2019-09-26; 한예나]
	 */
	public List<BookDTO> getDBList(String searchBookName) {
		connect();
		List<BookDTO> bookList = new ArrayList<BookDTO>();
		String sql = "select bookNumber, bookName, author, publishingHouse, category, symbol, status from book where bookName like ?";
		
		try {	
			pstmt = conn.prepareStatement(sql);
			// SQL에 변수 입력
			pstmt.setString(1,searchBookName+"%");
			//SQL문 실행
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				// DTO 객체 생성
				BookDTO bookDTO = new BookDTO();
				// DB Select결과를 DO 객체에 저장
				bookDTO.setBookNumber(rs.getInt("booknumber"));
				bookDTO.setBookName(rs.getString("bookName"));
				bookDTO.setAuthor(rs.getString("author"));
				bookDTO.setPublishingHouse(rs.getString("publishingHouse"));
				bookDTO.setCategory(rs.getString("category"));
				bookDTO.setSymbol(rs.getString("symbol"));
				bookDTO.setStatus(rs.getString("status"));
							
				bookList.add(bookDTO);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			disconnect();
		}
		return bookList;
	}
	
	/**
	 * 	@fn			public boolean insertDB(BookDTO bookDTO)
	 *	@brief		값 입력
	 *	@details
	 *
	 *	@author		한예나
	 *	@date		2019-10-10
	 *
	 *	@param		BookDTO bookDTO DTO 값 저장
	 *  
	 *	@remark		데이터베이스에서 insert하기 위해 sql 선언		[2019-10-10; 한예나]
	 */
	// 입력
	public boolean insertDB(BookDTO bookDTO) {
		
		connect();
		try {
			conn.setAutoCommit(false);		
			String sql1 ="insert into book(bookName, author, publishingHouse, category, symbol, status) values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1,bookDTO.getBookName());
			pstmt.setString(2,bookDTO.getAuthor());
			pstmt.setString(3,bookDTO.getPublishingHouse());
			pstmt.setString(4,bookDTO.getCategory());
			pstmt.setString(5,bookDTO.getSymbol());
			pstmt.setString(6,bookDTO.getStatus());
			if(pstmt.executeUpdate() < 1) {
			
				conn.rollback();
				System.out.println("insert 시 DB insert 오류로 Rollback");
			}
			conn.setAutoCommit(true);
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			disconnect();
		}
		return true;
	}
}
