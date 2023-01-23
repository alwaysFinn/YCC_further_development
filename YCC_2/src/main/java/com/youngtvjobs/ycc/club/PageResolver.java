/*
 * 작성자 : alwaysFinn(김지호)
 * 최초 작성일 : '23.01.23
 * 마지막 업데이트 : '23.01.23
 * 업데이트 내용 : 파일 최초 생성 및 페이징 기능 구현
 * 기능 : 동아리 게시판 하단 페이지네이션 구현 및 검색 기능과 연계  
 */

package com.youngtvjobs.ycc.club;

public class PageResolver {
	
	private SearchItem sc;
	
	private int totalCnt;					// 게시물 총합
	public final int NAV_SIZE = 10;			// ? -> pageSize랑 다른점?
	private int totalPage;					// 전체 페이지 수
	private int beginPage;					// 화면에 보여줄 첫 페이지
	private int endPage;					// 화면에 보여줄 마지막 페이지
	private boolean showNext = false;		// ▶를 보여줄 지 여부(endPage == totalPage ? false : true)	-> 마지막 페이지의 수와 전체 페이지의 수가 일치하면 더 이상의 게시물이 없다는 뜻이므로
	private boolean showPrev = false;		// ◀를 보여줄 지 여부(beginPage == 1 ? false : true)		-> 시작 페이지가 1이면 더 이상 이전 페이지가 없고 게시물이 없으므로 false, 1이 아닐 경우 이전에 게시물을 모아놓은 페이지가 있다는 뜻이므로 true 
	
	public PageResolver(int totalCnt, Integer page) {
		this(totalCnt, new SearchItem(page, 10));
	}
	
	public PageResolver(int totalCnt, Integer page, Integer pageSize) {
		this(totalCnt, new SearchItem(page, pageSize));
	}
	
	public PageResolver(int totalCnt, SearchItem sc) {
		this.totalCnt = totalCnt;
		this.sc = sc;
		
		doPaging(totalCnt, sc);
	}

	public void doPaging(int totalCnt, SearchItem sc) {
		this.totalPage = totalCnt / sc.getPageSize() + (totalCnt % sc.getPageSize() == 0? 0 : 1);	//전체 페이지 갯수
		this.sc.setPage(Math.min(sc.getPage(), totalPage));	//page가 totalPage보다 크지 않음
		this.beginPage = (this.sc.getPage()-1) / NAV_SIZE * NAV_SIZE + 1;	//첫 패이지 숫자 11 -> 11, 10-> 1, 15 -> 11
		this.endPage = Math.min(this.beginPage + this.NAV_SIZE -1, totalPage);
		this.showPrev = beginPage != 1;
		this.showNext = endPage != totalPage; 
	}
	
	public void print() {
		System.out.println("page : " + sc.getPage());
		System.out.println(showPrev ? "PREF " : " ");
		
		for (int i = beginPage; i <= endPage; i++) {
			System.out.println(i + " ");
		}
		
		System.out.println(showNext ? " NEXT" : " ");
	}

	@Override
	public String toString() {
		return "PageResolver [sc=" + sc + ", totalCnt=" + totalCnt + ", totalPage=" + totalPage + ", beginPage="
				+ beginPage + ", endPage=" + endPage + ", showNext=" + showNext + ", showPrev=" + showPrev + "]";
	}

	public SearchItem getSc() {
		return sc;
	}

	public void setSc(SearchItem sc) {
		this.sc = sc;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isShowNext() {
		return showNext;
	}

	public void setShowNext(boolean showNext) {
		this.showNext = showNext;
	}

	public boolean isShowPrev() {
		return showPrev;
	}

	public void setShowPrev(boolean showPrev) {
		this.showPrev = showPrev;
	}

	public int getNAV_SIZE() {
		return NAV_SIZE;
	}
	
	
	
}
