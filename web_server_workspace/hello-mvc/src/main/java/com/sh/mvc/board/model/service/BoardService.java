package com.sh.mvc.board.model.service;

import java.sql.Connection;
import java.util.List;

import static com.sh.mvc.common.JdbcTemplate.*;

import com.sh.mvc.board.model.dao.BoardDao;
import com.sh.mvc.board.model.vo.Attachment;
import com.sh.mvc.board.model.vo.Board;
import com.sh.mvc.board.model.vo.BoardComment;
import com.sh.mvc.board.model.vo.BoardEntity;

public class BoardService {
	private BoardDao boardDao = new BoardDao();
	
	public List<Board> findAll(int start, int end) {
		Connection conn = getConnection();
		List<Board> boardList = boardDao.findAll(conn, start, end);
		close(conn);
		return boardList;
	}

	public int getTotalContent() {
		Connection conn = getConnection();
		int total = boardDao.getTotalContent(conn);
		close(conn);
		return total;
	}

	public int insertBoard(Board board) {
		int result = 0;
		Connection conn = getConnection();
		try {
			// board 테이블 추가
			result = boardDao.insertBoard(conn, board);
			
			// 발급된 board.no를 조회
			int boardNo = boardDao.getLastBoardNo(conn); // 마지막 게시글 번호(시퀀스)를 가져와서
			board.setNo(boardNo); // servlet에서 redirect시 사용
			// attachment 테이블 추가
			List<Attachment> attachments = board.getAttachments();
			if (attachments != null && !attachments.isEmpty()) {
				for(Attachment attach : attachments) {
					attach.setBoardNo(boardNo); // fk컬럼값 세팅
					result = boardDao.insertAttachment(conn, attach);					
				}
			}
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	
	public Board findById(int no) {
		Connection conn = getConnection();
		Board board = boardDao.findById(conn, no);
		List<Attachment> attachments = boardDao.findAttachmentByBoardNo(conn, no);
		board.setAttachments(attachments);
		close(conn);
		return board;
	}

	public int updateReadCount(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = boardDao.updateReadCount(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public Attachment findAttachmentById(int no) {
		Connection conn = getConnection();
		Attachment attach = boardDao.findAttachmentById(conn, no);
		close(conn);
		return attach;
	}

	public int deleteBoard(int no) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = boardDao.deleteBoard(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}  finally {
			close(conn);
		}
		return result;
	}

	public int updateBoard(Board board) {
		int result = 0;
		Connection conn = getConnection();
		try {
			
			// board 테이블 추가
			result = boardDao.updateBoard(conn, board);
			
			// attachment 테이블 추가
			List<Attachment> attachments = board.getAttachments();
			if (attachments != null && !attachments.isEmpty()) {
				for(Attachment attach : attachments) {
					result = boardDao.insertAttachment(conn, attach);					
				}
			}
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}

	public int deleteAttachment(int no) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = boardDao.deleteAttachment(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int insertBoardComment(BoardComment boardComment) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = boardDao.insertBoardComment(conn, boardComment);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public List<BoardComment> findBoardCommentByBoardNo(int no) {
		Connection conn = getConnection();
		List<BoardComment> boardComments = boardDao.findBoardCommentByBoardNo(conn, no);
		close(conn);
		return boardComments;
	}

	public int deleteBoardComment(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = boardDao.deleteBoardComment(conn, no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

}
