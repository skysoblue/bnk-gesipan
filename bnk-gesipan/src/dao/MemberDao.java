package dao;
import java.util.List;

import bean.MemberBean;
import factory.Command;
public interface MemberDao{
/*===== executeUpdate =====*/		
	
	public int insert(MemberBean bean);
	public int delete(MemberBean bean);
	public int update(MemberBean bean);
	
/*===== executeQuery =====*/	
	
	public List<MemberBean> getElementsByName(Command command);
	public int count(Command command);
	public List<MemberBean> list(Command command);
	public MemberBean getElementById(Command command);
	
}
