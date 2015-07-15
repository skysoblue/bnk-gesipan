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
/*===== executeQuery =====*/    

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
            System.out.println("MemberDAO 에서 에러가 발생 !!");
        }
        return result;
	}
	@Override
	public int delete(MemberBean bean) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int update(MemberBean bean) {
		// TODO Auto-generated method stub
		return 0;
	}

/*===== executeUpdate =====*/	

	@Override
	public int count(Command command) {
		int result = 0;
		String sql = "select count(*) count from member";
		try {
			rs = connector.createStatement().executeQuery(sql);
			result = rs.getInt("count");
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		}
		return bean;
	}

	@Override
	public List<MemberBean> getElementsByName(Command command) {
		List<MemberBean> list = new ArrayList<MemberBean>();
		String sql = "select * from member where ? = ?";
		try {
			pstmt  = connector.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<MemberBean> list(Command command) {
		List<MemberBean> list = new ArrayList<MemberBean>();
		MemberBean bean = new MemberBean();
		String sql = "select * from member";
        try {
            stmt = connector.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
               
 
                list.add(bean);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
 
            try {
                rs.close();
                stmt.close();
                connector.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
 
        }
        return list;
	}


	
  
}
