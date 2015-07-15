package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import bean.MemberBean;
import dao.CommonDao;
import dao.MemberDao;
import factory.Command;
import util.DBmanager;

public class MemberDaoImpl implements MemberDao{
/*===== Field =====*/	
	Connection connector;
    PreparedStatement pstmt;
    Statement stmt;
    ResultSet rs;
    /*
     * 지금 보시는 내용이 싱글톤 + DBCP 정석입니다. 
     다만, 이것만 해서는 단위별 서버 실행에서 
     DB접속이 안됩니다. 그래서
     * DBmanager 를 만들었고 당분간은 DBmanager 를 
     사용하다가 프로젝트가 완성되면 철거하는 방식으로 하겠습니다.
     */
    
/*===== Singleton-Constructor =====*/ 
    private MemberDaoImpl() {
        // 단위 테스트가 끝나고 프로젝트가 완성되면 걷어 낼 부분
        connector = DBmanager.getConnection();
    }
    private static MemberDaoImpl memberDAO = new MemberDaoImpl();
    public static MemberDaoImpl getInstance() {
        return memberDAO;
    }
/*===== DBCP connector =====*/    
    // 현재는 작동하지 않지만 위 DBmanager 를 걷어내는 순간
    // 작동함. 미리 설정함.	
    public Connection getConnection() throws Exception {
        Connection connector = null;
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
        connector = ds.getConnection();
        return connector;
    }
/*===== executeUpdate =====*/    

	@Override
	public int insert(MemberBean bean) {
		int result = 0;
        String sql = 
           "insert into member(MEM_ID,ADDR_SEQ,NAME,AGE,PASSWORD,EMAIL,IS_ADMIN)"
                +" values( ? , ? , ? , ? , ? , ? , ? )";
        try{
            pstmt = connector.prepareStatement(sql);
            
            pstmt.setString(1, bean.getMemId());
            pstmt.setInt(2, bean.getAddrSeq());
            pstmt.setString(3, bean.getName());
            pstmt.setString(4, bean.getAge());
            pstmt.setString(5, bean.getPassword());
            pstmt.setString(6, bean.getEmail());
            pstmt.setInt(7, bean.getIsAdmin());
            result = pstmt.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("MEMEBERDAO-INSERT-ERROR");
        }
        return result;
	}
	@Override
	public int delete(MemberBean bean) {
		int result = 0;
		String sql = "delete from member where mem_id = ?";
		try {
			pstmt = connector.prepareStatement(sql);
			pstmt.setString(1, bean.getMemId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("MEMEBERDAO-DELETE-ERROR");
		}
		
		return result;
	}


	@Override
	public int update(MemberBean bean) {
		int result = 0;
		String sql = "update member set "
				+ "password = ?"
				+ "email = ?";
		try {
			pstmt = connector.prepareStatement(sql);
			pstmt.setString(1, bean.getPassword());
			pstmt.setString(2, bean.getEmail());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("MEMEBERDAO-UPDATE-ERROR");
		}
		return result;
	}

/*===== executeQuery =====*/	

	@Override
	public int count(Command command) {
		int result = 0;
		String sql = "select count(*) count from member";
		try {
			rs = connector.createStatement().executeQuery(sql);
			result = rs.getInt("count");
					
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("MEMEBERDAO-COUNT-ERROR");
		}
		return result;
	}

	@Override
	public MemberBean getElementById(Command command) {
		MemberBean bean = new MemberBean();
		String sql = "select * from member where id = ?";
		try {
			pstmt = connector.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean.setMemId(rs.getString("MEM_ID"));
				bean.setAddrSeq(rs.getInt("ADDR_SEQ"));
				bean.setName(rs.getString("NAME"));
				bean.setAge(rs.getString("AGE"));
				bean.setPassword(rs.getString("PASSWORD"));
				bean.setEmail(rs.getString("EAMIL"));
				bean.setIsAdmin(rs.getInt("IS_ADMIN"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("MEMEBERDAO-getElementById-ERROR");
		}
		return bean;
	}

	@Override
	public List<MemberBean> getElementsByName(Command command) {
		List<MemberBean> list = new ArrayList<MemberBean>();
		MemberBean bean = new MemberBean();
		String sql = "select * "
				+ "\n from (select rownum as seq, m.* from member m"
				+ "\n where ? like like '%?%')"
				+ "\n where seq between ? and ?"
				+ "\n order by reg_date desc";
		try {
			pstmt  = connector.prepareStatement(sql);
			pstmt.setString(1, command.getKeyField());
			pstmt.setString(2, command.getKeyword());
			pstmt.setInt(3, command.getStart());
			pstmt.setInt(4, command.getEnd());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean.setMemId(rs.getString("MEM_ID"));
				bean.setAddrSeq(rs.getInt("ADDR_SEQ"));
				bean.setName(rs.getString("NAME"));
				bean.setAge(rs.getString("AGE"));
				bean.setPassword(rs.getString("PASSWORD"));;
				bean.setEmail(rs.getString("EMAIL"));
				bean.setIsAdmin(rs.getInt("IS_ADMIN"));
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("MEMEBERDAO-getElementsByName-ERROR");
		}
		return list;
	}

	@Override
	public List<MemberBean> list(Command command) {
		List<MemberBean> list = new ArrayList<MemberBean>();
		MemberBean bean = new MemberBean();
		String sql = "select * "
				+ "\n(select rownum as seq, m.* from member m)"
				+ "\n where seq between ? and ?"
				+ "\n order by reg_date desc";
        try {
            pstmt = connector.prepareStatement(sql);
            pstmt.setInt(1, command.getStart());
            pstmt.setInt(2, command.getEnd());
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	bean.setMemId(rs.getString("MEM_ID"));
                bean.setAddrSeq(rs.getInt("ADDR_SEQ"));
                bean.setName(rs.getString("NAME"));
                bean.setAge(rs.getString("AGE"));
                bean.setPassword(rs.getString("PASSWORD"));
                bean.setEmail(rs.getString("EMAIL"));
                bean.setIsAdmin(rs.getInt("IS_ADMIN"));
                list.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MEMEBERDAO-list-ERROR");
        } finally {
 
            try {
                rs.close();
                stmt.close();
                connector.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
 
        }
        return list;
	}
	@Override
	public int searchCount(Command command) {
		int result = 0;
		String sql = "select count(*) COUNT "
				+ "\n from member"
				+ "\n where ? like '%?%'";
		try {
			pstmt = connector.prepareStatement(sql);
			pstmt.setString(1, command.getKeyField());
			pstmt.setString(2, command.getKeyword());
			rs = pstmt.executeQuery();
			result = rs.getInt("COUNT");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("MEMEBERDAO-searchCount-ERROR");
		}
		return result;
	}


	
  
}
