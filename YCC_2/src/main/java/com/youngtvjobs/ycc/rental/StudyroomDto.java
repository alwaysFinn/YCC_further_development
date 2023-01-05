package com.youngtvjobs.ycc.rental;

import java.util.Date;
import java.util.Objects;

import org.springframework.web.util.UriComponentsBuilder;

public class StudyroomDto {

	
	private String user_id; // 회원아이디 (fk)
	private Integer serial_id;
	private Integer sroom_seat_id;
	private Date sroom_rental_stime;
	private Date sroom_rental_etime;
	// end of tb_studyroom(독서실)
	
	public StudyroomDto() {
		// TODO Auto-generated constructor stub
	}

	public StudyroomDto(String user_id, Integer serial_id, Integer sroom_seat_id, Date sroom_rental_stime,
			Date sroom_rental_etime) {
		super();
		this.user_id = user_id;
		this.serial_id = serial_id;
		this.sroom_seat_id = sroom_seat_id;
		this.sroom_rental_stime = sroom_rental_stime;
		this.sroom_rental_etime = sroom_rental_etime;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Integer getSerial_id() {
		return serial_id;
	}

	public void setSerial_id(Integer serial_id) {
		this.serial_id = serial_id;
	}

	public Integer getSroom_seat_id() {
		return sroom_seat_id;
	}

	public void setSroom_seat_id(Integer sroom_seat_id) {
		this.sroom_seat_id = sroom_seat_id;
	}

	public Date getSroom_rental_stime() {
		return sroom_rental_stime;
	}

	public void setSroom_rental_stime(Date sroom_rental_stime) {
		this.sroom_rental_stime = sroom_rental_stime;
	}

	public Date getSroom_rental_etime() {
		return sroom_rental_etime;
	}

	public void setSroom_rental_etime(Date sroom_rental_etime) {
		this.sroom_rental_etime = sroom_rental_etime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(serial_id, sroom_rental_etime, sroom_rental_stime, sroom_seat_id, user_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudyroomDto other = (StudyroomDto) obj;
		return Objects.equals(serial_id, other.serial_id)
				&& Objects.equals(sroom_rental_etime, other.sroom_rental_etime)
				&& Objects.equals(sroom_rental_stime, other.sroom_rental_stime)
				&& Objects.equals(sroom_seat_id, other.sroom_seat_id) && Objects.equals(user_id, other.user_id);
	}

	@Override
	public String toString() {
		return "StudyroomDto [user_id=" + user_id + ", serial_id=" + serial_id + ", sroom_seat_id=" + sroom_seat_id
				+ ", sroom_rental_stime=" + sroom_rental_stime + ", sroom_rental_etime=" + sroom_rental_etime + "]";
	}
	
}