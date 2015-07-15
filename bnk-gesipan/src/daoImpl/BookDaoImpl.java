package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.BookBean;
import dao.BookDao;
import dao.CommonDao;
import factory.Command;
import util.DBmanager;

public class BookDaoImpl implements BookDao{
	Connection connector;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	BookBean bean = new BookBean();
	
	
	public BookDaoImpl() {
		connector = DBmanager.getConnection();
	}
	private static BookDaoImpl bookDao = new BookDaoImpl();
	public static BookDaoImpl getConnection() {
		return bookDao;
	}
	
/*===== executeUpdate =====*/	
	@Override
	public int insert(BookBean bean) {
		int result = 0;
		return result;
	}
	@Override
	public int delete(BookBean bean) {
		int result = 0;
		return result;
	}

	@Override
	public int update(BookBean bean) {
		// TODO Auto-generated method stub
		return 0;
	}
	
/*===== executeQuery =====*/	
	@Override
	public int count(Command command) {
		int result = 0;
		String sql = "select count(*) as count from book";
		try {
			rs = connector.createStatement().executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("BookDaoImpl-count() 에러발생");
		}
		return result;
	}

	@Override
	public BookBean getElementById(Command command) {
		BookBean bean = new BookBean();
		String sql = "select * from book "
				+ "where mem_id = ";
		return bean;
	}

	@Override
	public List<BookBean> getElementsByName(Command command) {
		List<BookBean> list = new ArrayList<BookBean>();
		return list;
	}

	@Override
	public List<BookBean> list(Command command) {
		List<BookBean> list = new ArrayList<BookBean>();
		String sql = "select * from  book";
		try {
			rs = connector.createStatement().executeQuery(sql);
			while (rs.next()) {
				bean.setTitle(rs.getString("TITLE"));
				bean.setCreator(rs.getString("CREATOR"));
				bean.setGenre(rs.getString("GENRE"));
				bean.setMemId(rs.getString("MEM_ID"));
				bean.setRegDate(rs.getDate("REG_DATE"));
				
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("BookDaoImpl-list() 에러");
		}
		return list;
	}

}
