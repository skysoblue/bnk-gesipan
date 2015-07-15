package dao;

import java.util.List;

import factory.Command;

public interface CommonDao {
/*===== executeUpdate =====*/	
	/* 
	 * 추가 CREATE
	 * */
	public int insert(Object obj);
	/*
	 * 수정
	 * */
	public int update(Object obj);
	/*
	 * 삭제
	 * */
	public int delete(Object obj);
	
/*===== executeQuery =====*/	
	/*
	 * 요소의 갯수 
	 * */
	public int count(Command command);
	public int searchCount(Command command);
	/*
	 * ID 로 중복값 없이 추출
	 * */
	public Object getElementById(Command command);
	/*
	 * Name 으로 중복값 허용하며 추출
	 * */
	public List<Object> getElementsByName(Command command);
	/*
	 * 전체 목록 추출
	 * */
	public List<Object> list(Command command);

	
	
}
