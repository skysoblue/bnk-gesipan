package dao;

import java.util.List;

import bean.ArticleBean;
import factory.Command;

public interface ArticleDao{
/*===== executeUpdate =====*/	
	
	public int insert(ArticleBean bean);
	public int delete(ArticleBean bean);
	public int update(ArticleBean bean);
	
/*===== executeQuery =====*/	
	
	public List<ArticleBean> getElementsByName(Command command);
	public int count(Command command);
	public int searchCount(Command command);
	public List<ArticleBean> list(Command command);
	public ArticleBean getElementById(Command command);
	
}
