package com.sh.mvc.board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.sh.mvc.board.model.service.BoardService;
import com.sh.mvc.board.model.vo.Attachment;
import com.sh.mvc.board.model.vo.Board;
import com.sh.mvc.board.model.vo.BoardEntity;
import com.sh.mvc.common.HelloMvcFileRenamePolicy;
import com.sh.mvc.common.util.HelloMvcUtils;

/**
 * Servlet implementation class BoardCreateServlet
 */
@WebServlet("/board/boardCreate")
public class BoardCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BoardService boardService = new BoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/board/boardCreate.jsp")
			.forward(request, response);
	}

	/**
	 * 파일업로드
	 * 1. 파일업로드 HttpServletRequest대신 cos.jar의 MultipartRequest 사용(기존 request 객체 사용 불가)
	 * 2. 업로드 파일 정보를 DB attachment에 저장
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 업로드 파일 저장 경로
		ServletContext application = getServletContext();
		String saveDirectory = application.getRealPath("/upload/board");
		System.out.println("saveDirectory = " + saveDirectory);
				
		// 파일 하나 당 최대 크기 10MB
		int maxPostSize = 1024 * 1024 * 10;
		
		// 인코딩
		String encoding = "utf-8";
		
		// 파일명 재지정 정책객체
		// 한글.txt -> 20230629_1604301234_999.txt 
		// DefaultFileRenamePolicy : 똑같은 이름을 가진 파일이 존재한다면 파일명(default)에 1, 2, 3 으로 붙게 해주는 클래스
//		FileRenamePolicy policy = new DefaultFileRenamePolicy(); // 파일명이 동일하더라도 지워지지 않도록 해주는 객체
		FileRenamePolicy policy = new HelloMvcFileRenamePolicy();
		
		MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		// 객체 생성과 동시에 rename이 되어 저장됨
		
		// 사용자 입력값 처리
		String title = multiReq.getParameter("title");
		String writer = multiReq.getParameter("writer");
		String content = multiReq.getParameter("content");
//		BoardEntity board = new BoardEntity(0, title, writer, null, 0, 0, content);
		
		Board board = new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		// Attachment 객체 생성 (Board 추가)
		// getFileNames() : 폼 요소 중 input 태그에서 file 속성으로 지정된 태그의 name 속성 값 
		// (file 속성을 가진 파라미터의 이름을)을 Enumeration 객체 타입으로 반환한다
		
		Enumeration<String> filenames = multiReq.getFileNames(); //upFile1, upFile2
		
		while(filenames.hasMoreElements()) {
			String name = filenames.nextElement(); // upFile1, upFile2...
			
			File upFile = multiReq.getFile(name); // input:file[name] 
			// name='upFile1'인 태그 객체의 파일을 가져와서 파일 객체를 만들어 upFile에 저장
			
			if(upFile != null) { // 파일이 올려져있으면
				Attachment attach = new Attachment();
				
				// 사용자가 지정한 파일명
				attach.setOriginalFilename(multiReq.getOriginalFileName(name)); 
				
				// getFilesystemName() : file속성으로 지정된 input 태그에 의해 서버상에 업로드된 파일 이름을
				// String 객체 타입으로 반환함
				attach.setRenamedFilename(multiReq.getFilesystemName(name)); // renamedFilename
				
				board.addAttachment(attach);
			}
		}
		
		// 2. 업무로직
		int result = boardService.insertBoard(board);
		
		// 3. 응답처리(목록페이지로 redirect) - POST 방식 DML 처리 후 url 변경을 위해 redirect처리
		response.sendRedirect(request.getContextPath() + "/board/boardDetail?no=" + board.getNo());
	}

}
