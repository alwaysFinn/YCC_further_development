/*
 * 작성자 : alwaysFinn(김지호)
 * 최초 작성일 : '23.01.23
 * 마지막 업데이트 : '23.01.23
 * 업데이트 내용 : 파일 최초 생성 및 페이지네이션 검색 및작업 
 * 기능 : 동아리 상세 페이지에 검색 기능 추가
 */

package com.youngtvjobs.ycc.club;

import org.springframework.web.util.UriComponentsBuilder;
import static java.util.Objects.requireNonNullElse;
import static java.lang.Math.*;

public class SearchItem {

	public static final int DEFAULT_PAGE_SIZE = 10;	// 한 페이지에 보여줄 게시물 수 (기본 설정) 
	public static final int MIN_PAGE_SIZE = 5;		// ?
	public static final int MAX_PAGE_SIZE = 50;		// ?
	
	private Integer page = 1;
	private Integer pageSize = DEFAULT_PAGE_SIZE;	
	private String option = "";
	private String keyword = "";
	private Integer offset;
	
	public SearchItem() {
		// TODO Auto-generated constructor stub
	}
	
	public SearchItem(Integer page, Integer pageSize) {
		this(page, pageSize, "", "");
	}
	
	public SearchItem(Integer page, Integer pageSize, String option, String keyword) {
		this.page = page;
		this.pageSize = pageSize;
		this.option = option;
		this.keyword = keyword;
	}
	
	public String getQueryString() {
		return getQueryString(page);
	}

	public String getQueryString(Integer page) {
		return UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("pageSize", pageSize)
				.queryParam("option", option)
				.queryParam("keyword", keyword)
				.build().toString();
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = requireNonNullElse(pageSize, DEFAULT_PAGE_SIZE);
		this.pageSize = max(MIN_PAGE_SIZE, min(this.pageSize, MAX_PAGE_SIZE));
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public Integer getOffset() {
		int result = (page-1)*pageSize;
		if(result < 0) result = 0;
		return result;
	}

	@Override
	public String toString() {
		return "SearchItem [page=" + page + ", pageSize=" + pageSize + ", option=" + option + ", keyword=" + keyword
				+ ", offset=" + offset + "]";
	}

	
}
