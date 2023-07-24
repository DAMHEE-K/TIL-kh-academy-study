package com.sh.mvc.board.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.sh.mvc.board.model.exception.BoardException;
import com.sh.mvc.board.model.vo.Attachment;
import com.sh.mvc.board.model.vo.Board;
import com.sh.mvc.board.model.vo.BoardComment;
import com.sh.mvc.board.model.vo.BoardEntity;
import com.sh.mvc.member.model.exception.MemberException;

public class BoardDao {
	Properties prop = new Properties();

	public BoardDao() {
		String filename = BoardDao.class.getResource("/sql/board/board-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Board> findAll(Connection conn, int start, int end) {
		List<Board> boards = new ArrayList<>();
		String sql = prop.getProperty("findAll");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					Board board = handleBoardResultSet(rset);
					board.setAttachCnt(rset.getInt("attach_cnt"));
					board.setCommentCnt(rset.getInt("comment_cnt"));
					boards.add(board);
				}
			}
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		return boards;
	}


	private Board handleBoardResultSet(ResultSet rset) throws SQLException {
		Board board = new Board();
		board.setNo(rset.getInt("no"));
		board.setTitle(rset.getString("title"));
		board.setWriter(rset.getString("writer"));
		board.setContent(rset.getString("content"));
		board.setReadCount(rset.getInt("read_count"));
		board.setRegDate(rset.getDate("reg_date"));
		return board;
	}


	/**
	 * DQL 
	 */
	public int getTotalContent(Connection conn) {
		int totalCnt = 0;
		String sql = prop.getProperty("getTotalContent");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			 try (ResultSet rset = pstmt.executeQuery()) {
				 while(rset.next()) {
					 totalCnt = rset.getInt("count(*)");
					 //rset.getInt(1); // 컬럼 순서 명시해도 좋음
				 }
			 } 
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		return totalCnt;
	}


	public int insertBoard(Connection conn, BoardEntity board) {
		int result = 0;
		// insertBoard = insert into board values(?, ?, ?, ?, default, default)
		String sql = prop.getProperty("insertBoard");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException("게시글 작성 오류", e);
		}
		return result;
	}


	public int getLastBoardNo(Connection conn) {
		int boardNo = 0;
		
		//select seq_board_no.currval from dual
		String sql = prop.getProperty("getLastBoardNo");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			try(ResultSet rset = pstmt.executeQuery()) {
				if(rset.next()) {
					boardNo = rset.getInt(1); // 첫 번째 컬럼값 가져오기
				}
			}
			
		} catch(SQLException e) {
			throw new BoardException(e);
		}
		return boardNo;
	}


	public int insertAttachment(Connection conn, Attachment attach) {
		int result = 0;
		String sql = prop.getProperty("insertAttachment");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, attach.getBoardNo());
			pstmt.setString(2, attach.getOriginalFilename());
			pstmt.setString(3, attach.getRenamedFilename());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		return result;
	}



	public Board findById(Connection conn, int no) {
		Board board = null;
		String sql = prop.getProperty("findById");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			try (ResultSet rset = pstmt.executeQuery()) {
				if (rset.next())
					board = handleBoardResultSet(rset);
			}
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		return board;
	}


	public List<Attachment> findAttachmentByBoardNo(Connection conn, int boardNo) {
		List<Attachment> attachments = new ArrayList<>();
		String sql = prop.getProperty("findAttachmentByBoardNo");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, boardNo);
			try (ResultSet rset = pstmt.executeQuery()) {
				while (rset.next()) {
					Attachment attach = handleAttachmentResultSet(rset);
					attachments.add(attach);
				}
			}
		} catch (Exception e) {
			throw new BoardException(e);
		}
		return attachments;
	}



	private Attachment handleAttachmentResultSet(ResultSet rset) throws SQLException {
		Attachment attach = new Attachment();
		attach.setNo(rset.getInt("no"));
		attach.setBoardNo(rset.getInt("board_no"));
		attach.setOriginalFilename(rset.getString("original_filename"));
		attach.setRenamedFilename(rset.getString("renamed_filename"));
		attach.setRegDate(rset.getDate("reg_date"));
		return attach;
	}


	public int updateReadCount(Connection conn, int no) {
		int result = 0;
		String sql = prop.getProperty("updateReadCount");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException(e);
		} 
		return result;
	}


	public Attachment findAttachmentById(Connection conn, int no) {
		Attachment attach = null;
		String sql = prop.getProperty("findAttachmentById");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next())
					attach = handleAttachmentResultSet(rset);
			}
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		return attach;
	}


	public int deleteBoard(Connection conn, int no) {
		int result = 0;
		String sql = prop.getProperty("deleteBoard");
		System.out.println(sql);
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, no);
			
			System.out.println("세팅된 :"+sql);
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			throw new BoardException(e);
		}
		return result;
	}


	public int updateBoard(Connection conn, Board board) {
		int result = 0;
		String query = prop.getProperty("updateBoard");

		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException(e);
		} 
		return result;
	}


	public int deleteAttachment(Connection conn, int no) {
		int result = 0;
		String sql = prop.getProperty("deleteAttachment");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		
		return 0;
	}


	public int insertBoardComment(Connection conn, BoardComment boardComment) {
		int result = 0;
		String sql = prop.getProperty("insertBoardComment");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, boardComment.getCommentLevel());
			pstmt.setString(2, boardComment.getWriter());
			pstmt.setString(3, boardComment.getContent());
			pstmt.setInt(4, boardComment.getBoardNo());
			pstmt.setObject(5, boardComment.getCommentRef() != 0 ? boardComment.getCommentRef() : null);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		
		return result;
	}


	public List<BoardComment> findBoardCommentByBoardNo(Connection conn, int no) {
		List<BoardComment> boardComments = new ArrayList<>();
		String sql = prop.getProperty("findBoardCommentByBoardNo");
		
		System.out.println("findBoardCommentByBoardNo = " + sql);
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, no);
				try (ResultSet rset = pstmt.executeQuery()) {
					while(rset.next()) {
						BoardComment boardComment = handleBoardCommentResultSet(rset);
						boardComments.add(boardComment);
						
				}
			}
		} catch(SQLException e) {
			throw new BoardException(e);
		}
		return boardComments;
	}


	private BoardComment handleBoardCommentResultSet(ResultSet rset) throws SQLException {
		int no = rset.getInt("no");
		int commentLevel = rset.getInt("comment_level");
		String writer = rset.getString("writer");
		String content = rset.getString("content");
		int boardNo = rset.getInt("board_no");
		int commentRef = rset.getInt("comment_ref");
		Date regDate = rset.getDate("reg_date");
		return new BoardComment(no, commentLevel, writer, content, boardNo, commentRef, regDate);
	}


	public int deleteBoardComment(Connection conn, int no) {
		int result = 0;
		String sql = prop.getProperty("deleteBoardComment");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		return result;
	}

	/**
	 * 내가 쓴 코드
	 */
//	private Board handleBoardResultSet(ResultSet rset) throws SQLException {
//		int no = rset.getInt("no");
//		String title = rset.getString("title");
//		String writer = rset.getString("writer");
//		Date regDate = rset.getDate("reg_date");
//		int file = rset.getInt("file");
//		int readCount = rset.getInt("read_count");
//		
//		return new Board(no, title, writer, regDate, file, readCount, writer);
//	}
}
