package com.devtwt.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.devtwt.app.bean.DevCategoryBean;
import com.devtwt.app.bean.RootBean;
import com.devtwt.app.bean.UserBean;
import com.devtwt.app.dao.GroupCreateDao;

@Component
public class GroupCreateDaoImpl implements GroupCreateDao {
	
	ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-jdbc.xml");
    
    // JdbcTemplateのオブジェクトを取得
    JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);
    
    @Override
	public List<UserBean> getAllMember(String userName) {
		// TODO Auto-generated method stub
    	
    	//Invite Member画面に表示するユーザを取得(ログインアカウントは除く)
    	List<UserBean> memberList = jdbcTemplate.query(
				"SELECT * FROM USER_MASTER WHERE MEMBER_NAME <> ?"
				, new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						UserBean user = new UserBean();
						user.setUserId(rs.getString("MEMBER_ID"));
						user.setUserName(rs.getString("MEMBER_NAME"));
						user.setUserPassword(rs.getString("PASSWORD"));
						user.setRoleId(rs.getString("ROLE_MASTER_ROLE_ID"));
						user.setCreateId(rs.getString("CREATE_ID"));
						user.setCreateDate(rs.getString("CREATE_DATE"));
						user.setUpdateId(rs.getString("UPDATE_ID"));
						user.setUpdateDate(rs.getString("UPDATE_DATE"));
						user.setDeleteFlag(rs.getString("DELETE_FLAG"));
						return user;
					}}
				, userName);
		return memberList;
	}

	@Override
	public List<DevCategoryBean> getAllData() {
		// TODO Auto-generated method stub
		
		//Group Create画面に表示するDevCategoryを取得
		List<DevCategoryBean> devCategoryList = jdbcTemplate.query(
				"SELECT * FROM DEV_CATEGORY"
				, new RowMapper<DevCategoryBean>() {
					public DevCategoryBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						DevCategoryBean devCategory = new DevCategoryBean();
						devCategory.setDevCategoryId(rs.getString("COMMUNITY_ID"));
						devCategory.setDevCategoryName(rs.getString("DEV_CATEGORY"));
						devCategory.setPhase(rs.getString("PHASE"));
						devCategory.setCreateId(rs.getString("CREATE_ID"));
						devCategory.setCreateDate(rs.getString("CREATE_DATE"));
						devCategory.setUpdateId(rs.getString("UPDATE_ID"));
						devCategory.setUpdateDate(rs.getString("UPDATE_DATE"));
						return devCategory;
					}
				});

		return devCategoryList; 

	}

	@Override
	public void dropTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertData(RootBean bean, String userName) {
		
    	//Group作成者または、招待メンバの情報を設定
    	String memberId = jdbcTemplate.queryForObject("SELECT MEMBER_ID FROM USER_MASTER WHERE MEMBER_NAME = ?", String.class, userName);
    	bean.getGroup().setCreateId(memberId);
    	bean.getGroup().setUpdateId(memberId);
    	bean.getGroup().setMemberId(memberId);
    	
    	//Groupを新規作成
    	jdbcTemplate.update(
                "INSERT INTO COMMUNITY (COMMUNITY_NAME, DEV_CATEGORY_COMMUNITY_ID, MEMBER_ID"
                			+ ", CREATE_ID, CREATE_DATE, UPDATE_ID, UPDATE_DATE) VALUES (?, ?, ?, ?, ?, ?, ?)"
                , bean.getGroup().getGroupName(), bean.getGroup().getSlctDevCateId()
                		, bean.getGroup().getMemberId(), bean.getGroup().getCreateId(), bean.getGroup().getCreateDate()
                		, bean.getGroup().getUpdateId(), bean.getGroup().getUpdateDate());
    }
	}


