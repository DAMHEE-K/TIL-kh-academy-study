package com.sh.app.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sh.app.board.entity.Attachment;
import com.sh.app.board.entity.Board;
import com.sh.app.board.entity.BoardDetails;
import com.sh.app.board.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardRepository boardRepository;
	
	@Override
	public List<BoardDetails> findAllBoard(Map<String, Object> params) {
		int limit = (int) params.get("limit");
		int page = (int) params.get("page");
		int offset = (page - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		return boardRepository.findAllBoard(rowBounds);
	}


	// 어떤 오류가 발생하면 무조건 rollback
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertBoard(Board board) {
		int result = 0;
		// board 저장
		result = boardRepository.insertBoard(board);

		// attachment 저장
		List<Attachment> attachments = ((BoardDetails) board).getAttachments();
		if(!attachments.isEmpty() && attachments != null) {
			for(Attachment attach : attachments) {
				attach.setBoardId(board.getId());
				result = boardRepository.insertAttachment(attach);
			}
		}
		return result;
	}
	
	@Override
	public BoardDetails findById(int id) {
		return boardRepository.findById(id);
	}

}
