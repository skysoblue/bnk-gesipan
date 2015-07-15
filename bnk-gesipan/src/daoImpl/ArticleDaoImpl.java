package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import bean.AddrBean;
import bean.ArticleBean;
import dao.ArticleDao;
import dao.CommonDao;
import factory.Command;
import util.DBmanager;

public class ArticleDaoImpl implements ArticleDao{
/*===== field =====*/	
	Connection conn = null;
    PreparedStatement pstmt = null;
    Statement stmt = null;
    ResultSet rs = null;
/*===== Singleton-Constructor =====*/
    private ArticleDaoImpl() {
        // 단위 테스트가 끝나고 프로젝트가 완성되면 걷어 낼 부분
        conn = DBmanager.getConnection();
    }
    private static ArticleDaoImpl bangDAO = new ArticleDaoImpl();
    public static ArticleDaoImpl getInstance() {
        return bangDAO;
    }
    public Connection getConnection() throws Exception {
        Connection conn = null;
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
        conn = ds.getConnection();
        return conn;
    }

/*===== executeUpdate =====*/
	@Override
	public int insert(ArticleBean bean) {
		int result = 0;
		String sql = "insert into GUESTBOOK (REGISTER,NAME,EMAIL,PASSWORD,CONTENT) "+
	            "values (?,?,?,?,?)";
		  try {
	           
	            pstmt = conn.prepareStatement(sql);
	            
	         
	              
	        } catch(Exception ex) {
	            ex.printStackTrace();
	            
	        } finally {
	            if (pstmt != null) try { pstmt.close(); } catch(Exception ex) {}
	            if (conn != null) try { conn.close(); } catch(Exception ex) {}
	        }
		return result;
	}
	@Override
	public int update(ArticleBean bean) {
		int result = 0;
		 try {
	            pstmt = conn.prepareStatement(
	            "update GUESTBOOK set NAME=?,EMAIL=?,PASSWORD=?,CONTENT=? "+
	            "where GUESTBOOK_ID = ?");
	            
	            
	            pstmt.executeUpdate();
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            if (pstmt != null) try { pstmt.close(); } catch(Exception ex) {}
	            if (conn != null) try { conn.close(); } catch(Exception ex) {}
	        }
		return result;
	}

	@Override
	public int delete(ArticleBean bean) {
		int result = 0;
		 try {
	            pstmt = conn.prepareStatement(
	            "delete from GUESTBOOK where GUESTBOOK_ID = ?");
	            pstmt.executeUpdate();
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            if (pstmt != null) try { pstmt.close(); } catch(Exception ex) {}
	            if (conn != null) try { conn.close(); } catch(Exception ex) {}
	        }
		return result;
	}
	
/*===== executeQuery =====*/	

	@Override
	public int count(Command command) {
		int count = 0;
		  try {
	            stmt = conn.createStatement();
	            rs = stmt.executeQuery("select count(*) from GUESTBOOK");
	            
	            if (rs.next()) {
	                count = rs.getInt(1);
	            }
	            
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            if (rs != null) try { rs.close(); } catch(Exception ex) {}
	            if (stmt != null) try { stmt.close(); } catch(Exception ex) {}
	            if (conn != null) try { conn.close(); } catch(Exception ex) {}
	        }
		return count;
	}

	@Override
	public ArticleBean getElementById(Command command) {
		ArticleBean bean = null;
		 try {
	            pstmt = conn.prepareStatement(
	            "select * from GUESTBOOK where GUESTBOOK_ID = ? ");
	            rs = pstmt.executeQuery();
	            
	            if (rs.next()) {
	            }
	          
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            if (rs != null) try { rs.close(); } catch(Exception ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(Exception ex) {}
	            if (conn != null) try { conn.close(); } catch(Exception ex) {}
	        }
		return bean;
	}

	@Override
	public List<ArticleBean> getElementsByName(Command command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleBean> list(Command command) {
		List<ArticleBean> list = new ArrayList<ArticleBean>();
		int startRow = 0,endRow = 0;
		int pageParam = endRow-startRow+1;
		
		 try {
	            pstmt = conn.prepareStatement(
	            "select * from GUESTBOOK order by GUESTBOOK_ID desc "+
	            "limit ?, ?" );
	            pstmt.setInt(1, startRow - 1); 
	            pstmt.setInt(2, endRow - startRow);
	            rs = pstmt.executeQuery();
	            
	            if (rs.next()) {
	                
	               // 페이징 처리 필요
	                
	            } else {
	               
	            }
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            if (rs != null) try { rs.close(); } catch(Exception ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(Exception ex) {}
	            if (conn != null) try { conn.close(); } catch(Exception ex) {}
	        }
		return list;
	}

	


}
