package dao;

import java.util.List;

import bean.BookBean;

public interface BookDao extends CommonDao{
	@Override
	int count();
	@Override
	int delete(String id);
	@Override
	BookBean getElementById(String id);
	@Override
	List<Object> getElementsByName(String name);
	int insert(BookBean obj);
	@Override
	List<Object> list();
	int update(BookBean obj);
}
