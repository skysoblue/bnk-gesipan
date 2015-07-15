package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import bean.ThemeBean;
import dao.CommonDao;
import dao.ThemeDao;
import factory.Command;

public class ThemeDaoImpl implements ThemeDao {
/*===== Field =====*/	
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	
/*===== Constructor ====*/	
	
/*===== executeUpdate =====*/	

	@Override
	public int insert(ThemeBean bean) {
		int result = 0;
		try {
			conn.setAutoCommit(false);

			if (bean.getParentId() == 0) {
				// 답글이 아닌 경우 그룹번호를 새롭게 구한다.
				stmt = conn.createStatement();
				rs = stmt
						.executeQuery("select max(GROUP_ID) from THEME_MESSAGE");
				int maxGroupId = 0;
				if (rs.next()) {
					maxGroupId = rs.getInt(1);
				}
				maxGroupId++;

				bean.setGroupId(maxGroupId);
				bean.setOrderNo(0);
			} else {
				// 특정 글의 답글인 경우,
				// 같은 그룹 번호 내에서의 출력 순서를 구한다.
				pstmt = conn
						.prepareStatement("select max(ORDER_NO) from THEME_MESSAGE "
								+ "where PARENT_ID = ? or THEME_MESSAGE_ID = ?");
				pstmt.setInt(1, bean.getParentId());
				pstmt.setInt(2, bean.getParentId());
				rs = pstmt.executeQuery();
				int maxOrder = 0;
				if (rs.next()) {
					maxOrder = rs.getInt(1);
				}
				maxOrder++;
				bean.setOrderNo(maxOrder);
			}

			// 특정 글의 답변 글인 경우 같은 그룹 내에서
			// 순서 번호를 변경한다.
			if (bean.getOrderNo() > 0) {
				pstmt = conn
						.prepareStatement("update THEME_MESSAGE set ORDER_NO = ORDER_NO + 1 "
								+ "where GROUP_ID = ? and ORDER_NO >= ?");
				pstmt.setInt(1, bean.getGroupId());
				pstmt.setInt(2, bean.getOrderNo());
				pstmt.executeUpdate();
			}
			// 새로운 글의 번호를 구한다.
			bean.setId(0);
			// 글을 삽입한다.
			pstmt = conn
					.prepareStatement("insert into THEME_MESSAGE values (?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, bean.getId());
			pstmt.setInt(2, bean.getGroupId());
			pstmt.setInt(3, bean.getOrderNo());
			pstmt.setInt(4, bean.getLevel());
			pstmt.setInt(5, bean.getParentId());
			// pstmt.setString(6, bean.getRegister());
			pstmt.setString(7, bean.getName());
			pstmt.setString(8, bean.getEmail());
			pstmt.setString(9, bean.getImage());
			pstmt.setString(10, bean.getPassword());
			pstmt.setString(11, bean.getTitle());
			pstmt.executeUpdate();

			pstmt = conn
					.prepareStatement("insert into THEME_CONTENT values (?,?)");
			pstmt.setInt(1, bean.getId());
			pstmt.setCharacterStream(2, null);
			pstmt.executeUpdate();

			conn.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return result;
	}
	@Override
	public int update(ThemeBean bean) {
		int result = 0;
		try {

			pstmt = conn
					.prepareStatement("update THEME_MESSAGE set NAME=?,EMAIL=?,IMAGE=?,TITLE=? "
							+ "where THEME_MESSAGE_ID=?");
			pstmt = conn.prepareStatement("update THEME_CONTENT set CONTENT=? "
					+ "where THEME_MESSAGE_ID=?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getImage());
			pstmt.setString(4, bean.getTitle());
			pstmt.setInt(5, bean.getId());
			pstmt.executeUpdate();

			pstmt.setString(1, null);
			pstmt.setInt(2, bean.getId());
			pstmt.executeUpdate();

			conn.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return result;
	}

	@Override
	public int delete(ThemeBean bean) {
		int result = 0;
		  Connection conn = null; 
	        PreparedStatement pstmtMessage = null;
	        PreparedStatement pstmtContent = null;
	        
	        try {
	           
	            conn.setAutoCommit(false);
	            
	            pstmtMessage = conn.prepareStatement(
	                "delete from THEME_MESSAGE where THEME_MESSAGE_ID = ?");
	            pstmtContent = conn.prepareStatement(
	                "delete from THEME_CONTENT where THEME_MESSAGE_ID = ?");
	            
	            
	            int updatedCount1 = pstmtMessage.executeUpdate();
	            int updatedCount2 = pstmtContent.executeUpdate();
	            
	            if (updatedCount1 + updatedCount2 == 2) {
	                conn.commit();
	            } else {
	                conn.rollback();
	                
	            }
	        } catch(Exception ex) {
	            try {
	                conn.rollback();
	            } catch(Exception ex1) {}
	            
	        } finally { 
	           
	        }
	   
		return result;
	}
/*===== executeQuery =====*/	
	
	@Override
	public int count(Command command) {
		Map<String, Object> valueMap = new HashMap<String, Object>();
		int count = 0;
		if (valueMap == null)
			valueMap = Collections.EMPTY_MAP;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			StringBuffer query = new StringBuffer(200);
			query.append("select count(*) from THEME_MESSAGE ");

			pstmt = conn.prepareStatement(query.toString());

			Iterator keyIter = valueMap.keySet().iterator();
			while (keyIter.hasNext()) {
				Integer key = (Integer) keyIter.next();
				Object obj = valueMap.get(key);
				if (obj instanceof String) {
					pstmt.setString(key.intValue(), (String) obj);
				} else if (obj instanceof Integer) {
					pstmt.setInt(key.intValue(), ((Integer) obj).intValue());
				} else if (obj instanceof Date) {
					// pstmt.setRegister(key.intValue(), (Date) obj);
				}
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
			return count;
		} catch (Exception ex) {

		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
				}
		}
		return count;
	}

	@Override
	public ThemeBean getElementById(Command command) {
		Object obj = null;
		 Connection conn = null; 
	        PreparedStatement pstmtMessage = null;
	        ResultSet rsMessage = null; 
	        PreparedStatement pstmtContent = null;
	        ResultSet rsContent = null; 
	        
	        try {
	            ThemeBean theme = null; 
	            
	            
	            pstmtMessage = conn.prepareStatement(
	                "select * from THEME_MESSAGE "+
	                "where THEME_MESSAGE_ID = ?");
	            rsMessage = pstmtMessage.executeQuery();
	            if (rsMessage.next()) { 
	                theme = new ThemeBean();
	                theme.setId(rsMessage.getInt("THEME_MESSAGE_ID"));
	                theme.setGroupId(rsMessage.getInt("GROUP_ID")); 
	                theme.setOrderNo(rsMessage.getInt("ORDER_NO")); 
	                theme.setLevel(rsMessage.getInt("LEVEL"));
	                theme.setParentId(rsMessage.getInt("PARENT_ID"));
	                theme.setRegister(rsMessage.getTimestamp("REGISTER"));
	                theme.setName(rsMessage.getString("NAME")); 
	                theme.setEmail(rsMessage.getString("EMAIL"));
	                theme.setImage(rsMessage.getString("IMAGE"));
	                theme.setPassword(rsMessage.getString("PASSWORD")); 
	                theme.setTitle(rsMessage.getString("TITLE"));

	                pstmtContent = conn.prepareStatement(
	                    "select CONTENT from THEME_CONTENT "+
	                    "where THEME_MESSAGE_ID = ?");
	             //   pstmtContent.setInt(1, id); 
	                rsContent = pstmtContent.executeQuery();
	                if (rsContent.next()) { 
	                   String reader = null;
	                    try {
	                  //      reader = rsContent.getCharacterStream("CONTENT");
	                        char[] buff = new char[512];
	                        int len = -1;
	                     
	                   //     theme.setContent(buffer.toString());
	                    } catch(Exception iex) {
	                      
	                    } finally { 
	                       
	                    }           
	                } else {
	                    return null;
	                }
	                return theme;
	            } else {
	                return null;
	            }
	        } catch(Exception ex) {
	            
	        } finally { 
	          
	        }
		return null;
	}

	@Override
	public List<ThemeBean> getElementsByName(Command command) {
		List<ThemeBean> list = new ArrayList<ThemeBean>();
		return list;
	}

	@Override
	public List<ThemeBean> list(Command command) {
		List<ThemeBean> list = new ArrayList<ThemeBean>();
		Map<String, Object> valueMap = new HashMap<String, Object>();
		int startRow=0,endRow=0;
		if (valueMap == null)
			valueMap = Collections.EMPTY_MAP;

		Connection conn = null;
		PreparedStatement pstmtMessage = null;
		ResultSet rsMessage = null;

		try {
			StringBuffer query = new StringBuffer(200);
			query.append("select * from THEME_MESSAGE ");
			
			query.append(" order by GROUP_ID desc, ORDER_NO asc limit ?, ?");

			

			pstmtMessage = conn.prepareStatement(query.toString());
			Iterator keyIter = valueMap.keySet().iterator();
			while (keyIter.hasNext()) {
				Integer key = (Integer) keyIter.next();
				Object obj = valueMap.get(key);
				if (obj instanceof String) {
					pstmtMessage.setString(key.intValue(), (String) obj);
				} else if (obj instanceof Integer) {
					pstmtMessage.setInt(key.intValue(),
							((Integer) obj).intValue());
				} else if (obj instanceof Date) {
					// pstmtMessage.setTimestamp(key.intValue(), (Timestamp) obj);
				}
			}

			pstmtMessage.setInt(valueMap.size() + 1, startRow);
			pstmtMessage.setInt(valueMap.size() + 2, endRow - startRow + 1);

			rsMessage = pstmtMessage.executeQuery();
			if (rsMessage.next()) {
				List list2 = new java.util.ArrayList(endRow - startRow + 1);

				do {
					ThemeBean theme = new ThemeBean();
					theme.setId(rsMessage.getInt("THEME_MESSAGE_ID"));
					theme.setGroupId(rsMessage.getInt("GROUP_ID"));
					theme.setOrderNo(rsMessage.getInt("ORDER_NO"));
					theme.setLevel(rsMessage.getInt("LEVEL"));
					theme.setParentId(rsMessage.getInt("PARENT_ID"));
					theme.setRegister(rsMessage.getTimestamp("REGISTER"));
					theme.setName(rsMessage.getString("NAME"));
					theme.setEmail(rsMessage.getString("EMAIL"));
					theme.setImage(rsMessage.getString("IMAGE"));
					theme.setPassword(rsMessage.getString("PASSWORD"));
					theme.setTitle(rsMessage.getString("TITLE"));
					list2.add(theme);
				} while (rsMessage.next());

				return list;

			} else {
				return Collections.EMPTY_LIST;
			}

		} catch (Exception ex) {
			
		} finally {
			
		}
		return list;
	}

	

}
