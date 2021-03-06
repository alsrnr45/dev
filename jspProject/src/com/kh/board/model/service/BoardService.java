package com.kh.board.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Reply;
import com.kh.common.model.vo.PageInfo;
public class BoardService {

	public int selectListCount() {
		Connection conn = getConnection();
		int listCount = new BoardDao().selectListCount(conn);
		
		close(conn);
		
		return listCount;
	}

	public ArrayList<Board> selectList(PageInfo pi) {
		Connection conn = getConnection();
		ArrayList<Board> list = new BoardDao().selectList(conn, pi);
		
		close(conn);
		return list;
	}

	public int insertBoard(Board b, Attachment at) {
		Connection conn = getConnection();
		
		int result1 = new BoardDao().insertBoard(conn, b);
		//  board테이블에 insert하는 dao메소드;
		
		int result2 = 1;
		// attachment 테이블이 없블경우 기본 1로 반환
		
		if(at != null) { // 첨부파일이 있었을 경우에만 실행될 메소드
			result2 = new BoardDao().insertAttachment(conn, at);
			// attachment 테이블에 insert하는 dao 메소드
			// 성공하면 1로 반환, 실패하면 0으로 반환
		}
		
		if(result1 > 0 && result2 > 0) {
			// 기본 1값으로 초기화했기때문에 attachment가 없을 경우 실행, 
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1 * result2;
	}

	public int increaseCount(int boardNo) {
		Connection conn = getConnection();
		int result = new BoardDao().increaseCount(conn, boardNo);
		
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
		
	}

	public Board selectBoard(int boardNo) {
		
		Connection conn = getConnection();
		Board b = new BoardDao().selectBoard(conn, boardNo);
		
		close(conn);
		
		return b;
	}

	public Attachment selectAttachment(int boardNo) {
		     
		Connection conn = getConnection();
		Attachment at = new BoardDao().selectAttachment(conn, boardNo);
		
		close(conn);
		return at;
	}

	public int updateBoard(Board b, Attachment at) {
		
		Connection conn = getConnection();
		
		int result1 = new BoardDao().updateBoard(conn, b);
		
		int result2 = 1;
		
		if(at != null) {
			// 새로온 첨부파일 있을 경우
			if(at.getFileNo() != 0) {
				// 기존의 첨부파일이 있었을 경우 => Attachment Update
				result2 = new BoardDao().updateAttachment(conn, at);
			} else {
				// 기존의 첨부파일이 없었을 경우 => Attachment Insert
				result2 = new BoardDao().insertNewAttachment(conn, at);
			}
			
		}
		
		if(result1 > 00 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		} 
		close(conn);
		
		return result1 * result2;
	}

	public int insertThBoard(Board b, ArrayList<Attachment> list) {
		Connection conn = getConnection();
		
		int result1 = new BoardDao().insertThBoard(conn, b);
		
		int result2 = new BoardDao().insertAttachmentList(conn, list);
		
		if(result1 > 00 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		} 
		close(conn);
		
		return result1 * result2;
	}

	public ArrayList<Board> selectThumbnailList() {
		
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectThumbnailList(conn);
		
		close(conn);
		
		return list;
	}

	public ArrayList<Attachment> selectAttachmentList(int boardNo) {
		Connection conn = getConnection();
		ArrayList<Attachment> list = new BoardDao().setAttachmentList(conn, boardNo);
		
		close(conn);
		
		return list;
	}

	public ArrayList<Reply> selectReplyList(int boardNo) {
		Connection conn = getConnection();
		
		ArrayList<Reply> list = new BoardDao().selectReplyList(conn, boardNo);
		
		close(conn);
		
		return list;
	}
	
	public int insertReply(Reply r) {
		Connection conn = getConnection();
		int result = new BoardDao().insertReply(conn, r);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		} 
		close(conn);
		return result;
	}
}
