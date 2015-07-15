package dao;
import java.util.List;

import bean.MemberBean;
public interface MemberDao extends CommonDao{
	@Override
	int count();
	@Override
	int delete(String id);
	MemberBean getElementById(String id);
	@Override
	List<Object> getElementsByName(String name);
	int insert(MemberBean obj);
	@Override
	List<Object> list();
	int update(MemberBean obj);
}
