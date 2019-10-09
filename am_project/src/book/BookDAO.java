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

	// DB 연결
	void connect() {
		try {
			Class.forName(jdbc_driver);

			conn = DriverManager.getConnection(jdbc_url,"jspbook","1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// DB 연결해제
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
	
	// 목록 조회
	public List<BookDTO> getDBList(String searchBookName) {
		
		connect();
		
		List<BookDTO> bookList = new ArrayList<BookDTO>();
		
		String sql = "select bookNumber, bookName, author, publishingHouse, category, symbol, status from book where bookName like ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			

			// SQL문에 변수 입력
			
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
}
