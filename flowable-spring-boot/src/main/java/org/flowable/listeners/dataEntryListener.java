package org.flowable.listeners;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.flowable.utils.ConnUtil;

public class dataEntryListener implements ExecutionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4016765813511686264L;
	private Expression strSN;

	public void setStrSN(Expression strSN) {
		this.strSN = strSN;
	}

	public void notify(DelegateExecution execution) {
		String currentStrSN = strSN.getValue(execution).toString();
		String sql = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> fileList = new ArrayList<String>();
		
		try {
			conn = ConnUtil.getConnection();
			sql = "select 文章编号 from 文章信息表 where 编号 = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, currentStrSN);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				fileList.add(rs.getString(1));
			}
			
			execution.setVariable("fileList", fileList);
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
