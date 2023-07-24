package com.sh.mvc.board.model.vo;

import java.util.ArrayList;
import java.util.List;

public class Board extends BoardEntity{
	private int attachCnt;
	int commentCnt;
	private List<Attachment> attachments = new ArrayList<>();
	

	public int getAttachCnt() {
		return attachCnt;
	}

	public void setAttachCnt(int attachCnt) {
		this.attachCnt = attachCnt;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public void addAttachment(Attachment attach) {
		if(attach != null)
			this.attachments.add(attach);
	}

	@Override
	public String toString() {
		return "Board [attachCnt=" + attachCnt + ", attachments=" + attachments + ", commentCnt=" + commentCnt
				+ ", getAttachCnt()=" + getAttachCnt() + ", getAttachments()=" + getAttachments() + ", getNo()="
				+ getNo() + ", getTitle()=" + getTitle() + ", getWriter()=" + getWriter() + ", getContent()="
				+ getContent() + ", getReadCount()=" + getReadCount() + ", getRegDate()=" + getRegDate()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

	public int getCommentCnt() {
		return commentCnt;
	}

	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}
	
	
}
