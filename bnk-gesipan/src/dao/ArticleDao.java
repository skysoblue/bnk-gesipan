package dao;

import java.util.List;

import bean.ArticleBean;

public interface ArticleDao extends CommonDao{
	@Override
	int count();
	@Override
	int delete(String id);
	@Override
	ArticleBean getElementById(String id);
	@Override
	List<Object> getElementsByName(String name);
	int insert(ArticleBean bean);
	@Override
	List<Object> list();
	int update(ArticleBean bean);
	
	
}
