package com.sh.mvc.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.mvc.board.model.service.BoardService;
import com.sh.mvc.board.model.vo.Board;
import com.sh.mvc.common.util.HelloMvcUtils;

/**
 * 페이징 처리
 * 1. 컨텐츠 영역
 *   - cpage 현재 페이지
 *   - limit 한 페이지 당 게시물 수

 * 2. 페이지바 영역
 *   - totalContent 전체 게시물 수
 *   - totalPage 전체 페이지 수
 *   - pagebarSize 10개
 *   - pageNo 페이지바의 숫자 (반복처리)
 *   - pageStart 페이지바 시작 번호
 *   - pageEnd 페이지바 끝 번호
 *   - url 요청 url
 */
@WebServlet("/board/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	private final int LIMIT = 10; // 한 페이지 당 게시물 수
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리

		// http://localhost:8080/mvc/board/boardList?cpage=2
		int cpage = 1;
		
		try {
			cpage = Integer.parseInt(request.getParameter("cpage"));
		} catch (NumberFormatException e) {
			// 예외처리외에 아무것도 하지 않음
			// cpage가 null 이라면 try에서 값 처리를 하지 못하고 catch절로 왔기 때문에
			// cpage가 계속 기본 값인 1이 유지가 됨
		}
		
		// cpage = 1 -> start = 1, end = 10;
		// cpage = 2 -> start = 11, end = 20;
		// cpage = 3 -> start = 21, end = 30;
		
		int start = (cpage - 1) * LIMIT + 1 ;
		int end = cpage * LIMIT;
		
		// 2. 업무 로직
		// 첨부파일이 1개 이상이라면 파일 이미지 띄우기
		// sql은 서브쿼리 사용해서 날리는게 간단, 쿼리 프로퍼티즈 새로 만들기
		List<Board> boards = boardService.findAll(start, end); // 모든 게시글 조회
//		System.out.println("boards = " + boards);
		
		// xss 공격대비 처리
		for(Board board : boards) {
			board.setTitle(HelloMvcUtils.escapeHtml(board.getTitle()));
		}
		

		// 페이지바 영역 처리
		int totalContent = boardService.getTotalContent();
//		System.out.println("boardContent = " + totalContent);
		String url = request.getRequestURI(); // /mvc/board/boardList
		String pagebar = HelloMvcUtils.getPagebar(cpage, LIMIT, totalContent, url);
		
//		System.out.println("pagebar = " + pagebar);
		
		request.setAttribute("boards", boards);
		request.setAttribute("pagebar", pagebar);
		
		
		// 3. 응답 처리
		request.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp")
			.forward(request, response);
	}

}
