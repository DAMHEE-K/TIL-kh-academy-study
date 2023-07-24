package com.sh.mvc.photo.model.service;

import static com.sh.mvc.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.sh.mvc.photo.model.dao.PhotoDao;
import com.sh.mvc.photo.model.vo.Photo;

public class PhotoService {
	private final PhotoDao photoDao = new PhotoDao();
	
	
	public int getTotalContent() {
		int result = 0;
		Connection conn = getConnection();
		result = photoDao.getTotalContent(conn);
		close(conn);
		return result;
	}


	public List<Photo> findPhoto(int start, int end) {
		Connection conn = getConnection();
		List<Photo> photos = photoDao.findPhoto(conn, start, end);
		close(conn);
		return photos;
	}
}
