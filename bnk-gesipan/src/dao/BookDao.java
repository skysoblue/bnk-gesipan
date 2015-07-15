package dao;

import java.util.List;

import bean.BookBean;
import factory.Command;

public interface BookDao{
/*===== executeUpdate =====*/
	
	public int insert(BookBean bean);
	public int update(BookBean bean);
	public int delete(BookBean bean);
	
	
/*===== executeQuery =====*/	
	
	public List<BookBean> getElementsByName(Command command);
	public int count(Command command);
	public int searchCount(Command command);
	public BookBean getElementById(Command command);
	public List<BookBean> list(Command command);
	
}
