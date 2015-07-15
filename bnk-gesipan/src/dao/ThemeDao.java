package dao;

import java.util.List;

import bean.ThemeBean;
import factory.Command;

public interface ThemeDao {
	int insert(ThemeBean bean);
	int update(ThemeBean bean);
	int delete(ThemeBean bean);
	
	ThemeBean getElementById(Command command);
	List<ThemeBean> getElementsByName(Command command);
	List<ThemeBean> list(Command command);
	int count(Command command);
	
}
