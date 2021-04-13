package com.kh.member.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;

import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {
	
	/**
	 * 1. 로그인용 서비스
	 * @param userId	사용자가 로그인폼에 입력했던 아이디값
	 * @param userPwd	사용자가 로그인폼에 입력했던 비밀번호값
	 * @return			해당 아이디와 비밀번호가 일치하는 조회된 한 회원 객체 / null
	 */
	public Member loginMember(String userId, String userPwd) {
		
		Connection conn = /*JDBCTemplate.*/getConnection();
		
		Member loginMember = new MemberDao().loginMember(conn, userId, userPwd);
		
		/*JDBCTemplate.*/close(conn);
		
		return loginMember;
	}
	
	/**
	 * 2. 회원가입용 서비스
	 * @param m		회원가입폼에 사용자가 입력한 값들이 담겨있는 Member객체
	 * @return		처리된 행 수
	 */
	public int insertMember(Member m) {
		
		Connection conn = getConnection();
		int result = new MemberDao().insertMember(conn, m);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
		
	}

	public Member updateMember(Member m) {
		
		Connection conn = getConnection();
		int result = new MemberDao().updateMember(conn, m);
		
		Member updateMem = null;
		
		if(result > 0) { // update 성공했을 경우 => 갱신된 회원객체를 다시 조회 해와야하기 때문에 이전에 있던 객체가 아닌 수정된 객체를 다시 조회 해야와야 함
			commit(conn);
			
			updateMem = new MemberDao().selectMember(conn, m.getUserId());
			// 새로운 객체생성(수정된 회원정보 객체를 가져오기) -> 다시 dao로 gogo!
			// 지역변수이므로 return할수가 없어 전역으로 updateMem = null로 초기화해주기
			
		} else { // update 실패했을 경우
			rollback(conn);
		}
		
		close(conn); // 커넥션 반환
		
		return updateMem;
		// 반환값이 Member객체이므로 메소드에 반환을 Member객체로 해주기
	}

	public int deleteMember(String userId, String userPwd) {
		Connection conn = getConnection();
		
		int result = new MemberDao().deleteMember(conn, userId, userPwd);
		
		if(result > 0) {
			commit(conn);
			
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public Member updatePwdMember(String userId, String userPwd, String updatePwd) {
		Connection conn = getConnection();
		
		int result = new MemberDao().updatePwdMember(conn, userId, userPwd, updatePwd);
		
		Member updateMem = null;
		if(result>0) {
			commit(conn);
			updateMem = new MemberDao().selectMember(conn, userId);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return updateMem;
		
	}
	
	
	
	
}



