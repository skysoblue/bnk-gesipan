package service;

import java.util.List;

import bean.MemberBean;
import factory.Command;

public interface MemberService {
/*===== executeUpdate =====*/	
	
	/*insert : 회원가입*/
    public int joinMember(MemberBean bean);
    /*update : 회원정보 수정*/
    public int updateMember(MemberBean bean);
    /*delete : 회원탈퇴*/
    public int deleteMember(MemberBean bean);
    
/*===== executeQuery =====*/    
    
    /*getElementsByName : 검색어로 회원 검색*/
    public List<MemberBean> searchByKeyword(Command command);
    /*list : 회원전체 목록*/
    public List<MemberBean> memberList(Command command);
    /*login : 로그인*/
    public String login(Command command);
    /*count : 회원수*/
    public int memberCount(Command command);
    /*getElementById : 회원상세정보*/
    public MemberBean memberDetail(Command command);
    
}
