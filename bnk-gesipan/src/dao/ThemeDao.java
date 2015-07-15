package dao;

import java.util.List;

import bean.ThemeBean;
import factory.Command;

public interface ThemeDao {
	
/*===== executeUpdate =====*/		
	
	public int insert(ThemeBean bean);
	public int update(ThemeBean bean);
	public int delete(ThemeBean bean);

/*===== executeQuery =====*/	
	
	public ThemeBean getElementById(Command command);
	public List<ThemeBean> getElementsByName(Command command);
	public List<ThemeBean> list(Command command);
	public int count(Command command);
	public int searchCount(Command command);
	
}
