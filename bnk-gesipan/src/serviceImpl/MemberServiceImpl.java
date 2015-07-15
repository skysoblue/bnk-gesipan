package serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.MemberBean;
import daoImpl.MemberDaoImpl;
import factory.Command;
import service.MemberService;

public class MemberServiceImpl implements MemberService {
/*===== Field =====*/	
	Map<String,Object> map = new HashMap<String,Object>();
	MemberBean bean = new MemberBean();
	
/*===== Constructor =====*/	
	/*
     * DAO 가 싱글톤 패턴으로 단 하나의 인스턴스만 리턴한다면
     * 그것을 사용하는 서비스도 싱글톤으로 구성해야 한다.
     * 그러지 않으면 다중 접속 상태에서 하나의 인스턴스만 사용하게되어
     * 접속불량 현상이 발생한다.
     * */
    private MemberServiceImpl() {}
    private static MemberService service = new MemberServiceImpl();
    public static MemberServiceImpl getInstance(){
        return (MemberServiceImpl) service;
    }
    
/*===== executeUpdate =====*/
    @Override
    public int joinMember(MemberBean bean) {
        return MemberDaoImpl.getInstance().insert(bean);
    }
    @Override
	public int updateMember(MemberBean bean) {
		return MemberDaoImpl.getInstance().update(bean);
	}
	@Override
	public int deleteMember(MemberBean bean) {
		return MemberDaoImpl.getInstance().delete(bean);
	}
   
/*===== executeQuery =====*/	
	@Override
	public MemberBean memberDetail(Command command) {
		return MemberDaoImpl.getInstance().getElementById(command);
	}
	@Override
	public List<MemberBean> searchByKeyword(Command command) {
		return MemberDaoImpl.getInstance().getElementsByName(command);
	}
	
	@Override
	public String login(Command command) {
		String msg = "";
		
		bean = (MemberBean) MemberDaoImpl.getInstance().getElementById(command);
		
		if (bean.getMemId().equals(command.getKeyField()) && 
				bean.getPassword().equals(command.getKeyword())) {
			msg = "환영합니다.";
		} else if(bean.getMemId().equals(command.getKeyField())&&
				!bean.getPassword().equals(command.getKeyword())){
			msg = "비밀번호가 일치하지 않습니다.";
		}else{
			msg = "등록된 아이디가 아닙니다.";
		}
		
		return msg;
	}
    @Override
    public List<MemberBean> memberList(Command command) {
        return  MemberDaoImpl.getInstance().list(command);
    }
	@Override
	public int memberCount(Command command) {
		return MemberDaoImpl.getInstance().count(command);
	}
}
