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

	// MySQL �뿰寃곗젙蹂� 
	String jdbc_driver = "com.mysql.jdbc.Driver";
	
	String jdbc_url = "jdbc:mysql://127.0.0.1/jspdb?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";

	/**
	 * 	@fn			void connect()
	 *	@brief		DB �뿰寃�
	 *	@details
	 *
	 *	@author		�븳�삁�굹
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
	 *	@brief		DB �뿰寃� �빐�젣
	 *	@details
	 *
	 *	@author		�븳�삁�굹
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
	 *	@brief		寃��깋 �떆 寃곌낵 紐⑸줉 議고쉶
	 *	@details
	 *
	 *	@author		�븳�삁�굹
	 *	@date		2019-09-26
	 *
	 *	@param		String searchBookName �뜲�씠�꽣踰좎씠�뒪�뿉 ���옣�맂 媛�
	 *  
	 *	@remark		�뜲�씠�꽣踰좎씠�뒪�쓽 媛믪쓣 ���옣�븯湲� �쐞�빐 booklist �꽑�뼵
	 *				�뜲�씠�꽣踰좎씠�뒪�뿉�꽌 select�븯湲� �쐞�빐 sql �꽑�뼵
	 *				sql臾몄쓣 �떎�뻾�븯湲� �쐞�빐 rs �꽑�뼵
	 *				select 寃곌낵瑜� ���옣�븯湲� �쐞�빐 bookDTO �꽑�뼵			[2019-09-26; �븳�삁�굹]
	 */
	public List<BookDTO> getDBList(String searchBookName) {
		connect();
		List<BookDTO> bookList = new ArrayList<BookDTO>();
		String sql = "select bookNumber, bookName, author, publishingHouse, category, symbol, status from book where bookName like ?";
		
		try {	
			pstmt = conn.prepareStatement(sql);
			// SQL�뿉 蹂��닔 �엯�젰
			pstmt.setString(1,searchBookName+"%");
			//SQL臾� �떎�뻾
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				// DTO 媛앹껜 �깮�꽦
				BookDTO bookDTO = new BookDTO();
				// DB Select寃곌낵瑜� DO 媛앹껜�뿉 ���옣
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
	 *	@brief		媛� �엯�젰
	 *	@details
	 *
	 *	@author		�븳�삁�굹
	 *	@date		2019-10-10
	 *
	 *	@param		BookDTO bookDTO DTO 媛� ���옣
	 *  
	 *	@remark		�뜲�씠�꽣踰좎씠�뒪�뿉�꽌 insert�븯湲� �쐞�빐 sql �꽑�뼵		[2019-10-10; �븳�삁�굹]
	 */
	// �엯�젰
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
				System.out.println("insert �떆 DB insert �삤瑜섎줈 Rollback");
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
	
	/**
	 * 	@fn			public boolean insertDB(BookDTO bookDTO)
	 *	@brief		媛� �엯�젰
	 *	@details
	 *
	 *	@author		�븳�삁�굹
	 *	@date		2019-10-10
	 *
	 *	@param		BookDTO bookDTO DTO 媛� ���옣
	 *  
	 *	@remark		�뜲�씠�꽣踰좎씠�뒪�뿉�꽌 insert�븯湲� �쐞�빐 sql �꽑�뼵		[2019-10-10; ]
	 */
	
public BookDTO getDB(int id) {
		
		connect();
		
		BookDTO bookDTO = new BookDTO();
		
		String sql = "select * from AccountTransfer where id = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			// SQL문에 조회조건 입력
			pstmt.setInt(1,id);

			//SQL문 실행
			ResultSet rs = pstmt.executeQuery();

			// 데이터가 하나만 있으므로 rs.next()를 한번만 실행 한다.
			rs.next();
			
			// DB Select결과를 DO 객체에 저장
			bookDTO.setBookNumber(rs.getInt("booknumber"));
			bookDTO.setBookName(rs.getString("bookName"));
			bookDTO.setAuthor(rs.getString("author"));
			bookDTO.setPublishingHouse(rs.getString("publishingHouse"));
			bookDTO.setCategory(rs.getString("category"));
			bookDTO.setSymbol(rs.getString("symbol"));
			bookDTO.setStatus(rs.getString("status"));
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			disconnect();
		}
		return bookDTO;
	}

/**
 * 	@fn			public boolean insertDB(BookDTO bookDTO)
 *	@brief		媛� �엯�젰
 *	@details
 *
 *	@author		�븳�삁�굹
 *	@date		2019-10-10
 *
 *	@param		BookDTO bookDTO DTO 媛� ���옣
 *  
 *	@remark		�뜲�씠�꽣踰좎씠�뒪�뿉�꽌 insert�븯湲� �쐞�빐 sql �꽑�뼵		[2019-10-10; ]
 */

	
public boolean updateDB(BookDTO bookDTO) {
	
	// update 이전 수량
	int transferAmmount = getTransferAmmount(bookDTO.getId());
	
	connect();
	
	
	try {
		
		// 트랜젝션 Non Autocommit시작
		conn.setAutoCommit(false);

		// id로 매칭하여 update				
		String sql1 ="update AccountTransfer set transferDate=?, transferAmmount=? where id=?";

		pstmt = conn.prepareStatement(sql1);

		// SQL문에 변수 입력
		pstmt.setString(1,bookDTO.getBookName());
		pstmt.setString(2,bookDTO.getAuthor());
		pstmt.setString(3,bookDTO.getPublishingHouse());
		pstmt.setString(4,bookDTO.getCategory());
		pstmt.setString(5,bookDTO.getSymbol());
		pstmt.setString(6,bookDTO.getStatus());
		
		//SQL문 실행, 실패 시 roollback
		if(pstmt.executeUpdate() < 1) {
		
			conn.rollback();
			System.out.println("update 시 DB update1 오류로 Rollback");
		}
		
		// from account update(변경 후 수량 -, 변경 전 수량 +)
		String sql2 ="update Account set accountBalance = (accountBalance - ?) + ? where  accountNumber=?";
		
		pstmt = conn.prepareStatement(sql2);

		// SQL문에 변수 입력
		pstmt.setString(1,bookDTO.getBookName());
		pstmt.setString(2,bookDTO.getAuthor());
		pstmt.setString(3,bookDTO.getPublishingHouse());
		pstmt.setString(4,bookDTO.getCategory());
		pstmt.setString(5,bookDTO.getSymbol());
		pstmt.setString(6,bookDTO.getStatus());

		//SQL문 실행, 실패 시 roollback
		if(pstmt.executeUpdate() < 1) {
		
			conn.rollback();
			System.out.println("update 시 DB update2 오류로 Rollback");
		}


		// to account update(변경 후 수량 +, 변경 전 수량 -)
		String sql3 ="update Account set accountBalance = (accountBalance + ?) - ? where  accountNumber=?";
		
		pstmt = conn.prepareStatement(sql3);

		// SQL문에 변수 입력
		pstmt.setString(1,bookDTO.getBookName());
		pstmt.setString(2,bookDTO.getAuthor());
		pstmt.setString(3,bookDTO.getPublishingHouse());
		pstmt.setString(4,bookDTO.getCategory());
		pstmt.setString(5,bookDTO.getSymbol());
		pstmt.setString(6,bookDTO.getStatus());

		
		//SQL문 실행, 정상 시 commit, 실패 시 rollback
		if(pstmt.executeUpdate() > 0) {
			conn.commit();
		} else {
			conn.rollback();
			System.out.println("update 시 DB update2 오류로 Rollback");
		}
		
		// 트랜젝션 Non Auto 종료
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

/**
 * 	@fn			public boolean insertDB(BookDTO bookDTO)
 *	@brief		媛� �엯�젰
 *	@details
 *
 *	@author		�븳�삁�굹
 *	@date		2019-10-10
 *
 *	@param		BookDTO bookDTO DTO 媛� ���옣
 *  
 *	@remark		�뜲�씠�꽣踰좎씠�뒪�뿉�꽌 insert�븯湲� �쐞�빐 sql �꽑�뼵		[2019-10-10; ]
 */

public boolean deleteDB(BookDTO bookDTO) {
	
	
	connect();
	
	try {
		
		// 트랜젝션 Non Autocommit시작
		conn.setAutoCommit(false);
		
		// id로 매칭하여 delete				
		String sql1 ="delete from AccountTransfer where id=?";
		
		pstmt = conn.prepareStatement(sql1);

		// SQL문에 변수 입력
		pstmt.setInt(1,bookDTO.getId());
					
		//SQL문 실행, 실패 시 roollback
		if(pstmt.executeUpdate() < 1) {
		
			conn.rollback();
			System.out.println("delete 시 DB delete 오류로 Rollback");
		}
		
		// from account update(수량 +)
		String sql2 ="update Account set accountBalance = accountBalance + ? where  accountNumber=?";
		
		pstmt = conn.prepareStatement(sql2);

		// SQL문에 변수 입력
		pstmt.setString(1,bookDTO.getBookName());
		pstmt.setString(2,bookDTO.getAuthor());
		pstmt.setString(3,bookDTO.getPublishingHouse());
		pstmt.setString(4,bookDTO.getCategory());
		pstmt.setString(5,bookDTO.getSymbol());
		pstmt.setString(6,bookDTO.getStatus());

		//SQL문 실행, 실패 시 roollback
		if(pstmt.executeUpdate() < 1) {
		
			conn.rollback();
			System.out.println("update 시 DB update2 오류로 Rollback");
		}


		// to account update(수량 -)
		String sql3 ="update Account set accountBalance = accountBalance - ? where  accountNumber=?";
		
		pstmt = conn.prepareStatement(sql3);

		// SQL문에 변수 입력
		pstmt.setString(1,bookDTO.getBookName());
		pstmt.setString(2,bookDTO.getAuthor());
		pstmt.setString(3,bookDTO.getPublishingHouse());
		pstmt.setString(4,bookDTO.getCategory());
		pstmt.setString(5,bookDTO.getSymbol());
		pstmt.setString(6,bookDTO.getStatus());

		//SQL문 실행, 정상 시 commit, 실패 시 rollback
		if(pstmt.executeUpdate() > 0) {
			conn.commit();
		} else {
			conn.rollback();
			System.out.println("delete 시 DB update2 오류로 Rollback");
		}
		
		// 트랜젝션 Non Auto 종료
		conn.setAutoCommit(true);
	
	
	} catch (SQLException e) {
		e.printStackTrace();
		return false;
	}
	finally {
	}
	return true;
}
}
