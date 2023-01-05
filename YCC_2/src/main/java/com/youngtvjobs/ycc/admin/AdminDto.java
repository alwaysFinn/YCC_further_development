package com.youngtvjobs.ycc.admin;

import java.util.Objects;

public class AdminDto {
	
	private String rental_terms; //대관 약관
	private String join_terms; //이용 약관
	private String join_privacy_terms; //개인정보 약관
	
	public AdminDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	public AdminDto(String rental_terms, String join_terms, String join_privacy_terms) {
		super();
		this.rental_terms = rental_terms;
		this.join_terms = join_terms;
		this.join_privacy_terms = join_privacy_terms;
	}

	public String getRental_terms() {
		return rental_terms;
	}
	public void setRental_terms(String rental_terms) {
		this.rental_terms = rental_terms;
	}
	public String getJoin_terms() {
		return join_terms;
	}
	public void setJoin_terms(String join_terms) {
		this.join_terms = join_terms;
	}	
	public String getJoin_privacy_terms() {
		return join_privacy_terms;
	}
	public void setJoin_privacy_terms(String join_privacy_terms) {
		this.join_privacy_terms = join_privacy_terms;
	}


	@Override
	public int hashCode() {
		return Objects.hash(join_privacy_terms, join_terms, rental_terms);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdminDto other = (AdminDto) obj;
		return Objects.equals(join_privacy_terms, other.join_privacy_terms)
				&& Objects.equals(join_terms, other.join_terms) && Objects.equals(rental_terms, other.rental_terms);
	}


	@Override
	public String toString() {
		return "AdminDto [rental_terms=" + rental_terms + ", join_terms=" + join_terms + ", join_privacy_terms="
				+ join_privacy_terms + "]";
	}
	
	
	
	

}
