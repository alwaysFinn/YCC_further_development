/*
 * 작성자 : alwaysFinn(김지호)
 * 최초 작성일 : '23.01.06
 * 마지막 업데이트 : '23.01.06
 * 업데이트 내용 : dto 생성 및 컬럼들 추가
 */

package com.youngtvjobs.ycc.club;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubDto {
	
	private int club_id;	//동아리 번호
	private String club_title;	//동아리 이름
	private String club_create_time;	//동아리 생성 날짜
	private String club_info;	//동아리 설명
	private String club_master_id;	//동아리장 아이디
	private int club_member;	//동아리 멤버 수
	//end of club table
	
	private int club_article_id;	//동아리 게시글 번호
	private String club_article_title;	//동아리 게시글 제목
	private String club_article_content;	//동아리 글 내용
	private Date club_board_upload_time;	//동아리 글 작성 시간
	private int club_aricle_viewcnt;	//동아리 게시글 조회수
	private String user_id;	//동아리 게시글 작성자id
	//end of club_board table
	
	private int club_comment_id;	//댓글번호
	private String club_comment;	//댓글
	private Date club_comment_time;	//댓글 입력 시간
	//end of club_board_comment
	
	private int club_member_id;	//가입번호
	private Date club_secession_date;	//탈퇴날짜
	private Date club_join_date;	//가입날짜
	private String user_name;	//유저이름
	//end of club_member
	

}
