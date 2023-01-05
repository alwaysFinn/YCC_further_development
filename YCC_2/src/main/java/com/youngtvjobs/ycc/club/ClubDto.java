package com.youngtvjobs.ycc.club;

import java.util.Date;
import java.util.Objects;

public class ClubDto {

	//club
	private Integer club_id;
	private String club_title;
	private Date club_create_time;
	private String club_info;
	private String club_master_id;
	private int count;
	
	//club_board
	private Integer club_article_id;
	private String club_article_title;
	private Date club_board_upload_time;
	private Integer club_article_cnt;
	private String user_id;
	
	public ClubDto() {
		// TODO Auto-generated constructor stub
	}

	public ClubDto(Integer club_id, String club_title, Date club_create_time, String club_info, String club_master_id,
			Integer club_article_id, String club_article_title, Date club_board_upload_time, Integer club_article_cnt,
			String user_id, int count) {
		super();
		this.club_id = club_id;
		this.club_title = club_title;
		this.club_create_time = club_create_time;
		this.club_info = club_info;
		this.club_master_id = club_master_id;
		this.club_article_id = club_article_id;
		this.club_article_title = club_article_title;
		this.club_board_upload_time = club_board_upload_time;
		this.club_article_cnt = club_article_cnt;
		this.user_id = user_id;
		this.count = count;
	}

	public Integer getClub_id() {
		return club_id;
	}

	public void setClub_id(Integer club_id) {
		this.club_id = club_id;
	}

	public String getClub_title() {
		return club_title;
	}

	public void setClub_title(String club_title) {
		this.club_title = club_title;
	}

	public Date getClub_create_time() {
		return club_create_time;
	}

	public void setClub_create_time(Date club_create_time) {
		this.club_create_time = club_create_time;
	}

	public String getClub_info() {
		return club_info;
	}

	public void setClub_info(String club_info) {
		this.club_info = club_info;
	}

	public String getClub_master_id() {
		return club_master_id;
	}

	public void setClub_master_id(String club_master_id) {
		this.club_master_id = club_master_id;
	}

	public Integer getClub_article_id() {
		return club_article_id;
	}

	public void setClub_article_id(Integer club_article_id) {
		this.club_article_id = club_article_id;
	}

	public String getClub_article_title() {
		return club_article_title;
	}

	public void setClub_article_title(String club_article_title) {
		this.club_article_title = club_article_title;
	}

	public Date getClub_board_upload_time() {
		return club_board_upload_time;
	}

	public void setClub_board_upload_time(Date club_board_upload_time) {
		this.club_board_upload_time = club_board_upload_time;
	}

	public Integer getClub_article_cnt() {
		return club_article_cnt;
	}

	public void setClub_article_cnt(Integer club_article_cnt) {
		this.club_article_cnt = club_article_cnt;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int hashCode() {
		return Objects.hash(club_article_cnt, club_article_id, club_article_title, club_board_upload_time,
				club_create_time, club_id, club_info, club_master_id, club_title, count, user_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClubDto other = (ClubDto) obj;
		return Objects.equals(club_article_cnt, other.club_article_cnt)
				&& Objects.equals(club_article_id, other.club_article_id)
				&& Objects.equals(club_article_title, other.club_article_title)
				&& Objects.equals(club_board_upload_time, other.club_board_upload_time)
				&& Objects.equals(club_create_time, other.club_create_time) && Objects.equals(club_id, other.club_id)
				&& Objects.equals(club_info, other.club_info) && Objects.equals(club_master_id, other.club_master_id)
				&& Objects.equals(club_title, other.club_title) && count == other.count
				&& Objects.equals(user_id, other.user_id);
	}
	
	
}
