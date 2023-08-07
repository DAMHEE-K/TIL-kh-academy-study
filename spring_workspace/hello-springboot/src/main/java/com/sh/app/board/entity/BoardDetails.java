package com.sh.app.board.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder // 부모 필드까지 설정 가능한 Builder
@AllArgsConstructor
public class BoardDetails  extends Board{
	private int attachCount; // 게시글에 딸린 첨부파일 수
	private List<Attachment> attachments;
}
