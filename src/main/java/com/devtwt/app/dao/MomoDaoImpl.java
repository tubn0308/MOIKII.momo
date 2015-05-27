package com.devtwt.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.devtwt.app.bean.MomoBean;
import com.devtwt.app.bean.RootBean;

@Component
public class MomoDaoImpl implements MomoDao {
	
    ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-jdbc.xml");
    
    // JdbcTemplateのオブジェクトを取得
    JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);
	
	@Override
	public List<MomoBean> getAllData() {
		// TODO Auto-generated method stub
		
		List<MomoBean> momoList = jdbcTemplate.query(
					"SELECT * FROM MOMO ORDER BY MOMO_NUM DESC"
					, new RowMapper<MomoBean>() {
						public MomoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							MomoBean momo = new MomoBean();
							momo.setMomoNum(rs.getString("MOMO_NUM"));
							momo.setStream_stream_num(rs.getString("STREAM_STEREAM_NUM"));
							momo.setPhase(rs.getString("PHASE"));
							momo.setMomo_contents(rs.getString("MOMO_CONTENTS"));
							momo.setCreate_id(rs.getString("CREATE_ID"));
							momo.setCreate_date(rs.getString("CREATE_DATE"));
							momo.setUpdate_id(rs.getString("UPDATE_ID"));
							momo.setUpdate_date(rs.getString("UPDATE_DATE"));
							momo.setUser_master_member_id(rs.getString("USER_MASTER_MEMBER_ID"));
							return momo;
						}
					});

		return momoList;
	}

	

	@Override
	public void exec(RootBean bean) {
		// TODO Auto-generated method stub
		
		String momoNum;
    	int tmp,cnt;
		
        cnt = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM MOMO");
    	
    	//テーブルMOMOのデータが0件の場合
    	if(cnt == 0) {
    		momoNum = "0";
    	} else {
    		momoNum  = jdbcTemplate.queryForObject("SELECT MAX(MOMO_NUM) FROM MOMO", String.class);
    	}
    	
    	//MOMO_NUMをインクリメント
    	tmp = Integer.parseInt(momoNum);
    	bean.getMomo().setMomoNum(String.valueOf(++tmp));
    	
    	//AllowNullがfalseのカラムに値を設定
    	bean.getMomo().setStream_stream_num("1");
    	bean.getMomo().setUpdate_id("1");
		
    	//MOMOをテーブルにINSERT
		jdbcTemplate.update(
                "INSERT INTO MOMO (MOMO_NUM, STREAM_STEREAM_NUM, PHASE, MOMO_CONTENTS, CREATE_ID"
                + ", CREATE_DATE, UPDATE_ID, UPDATE_DATE, USER_MASTER_MEMBER_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
                , bean.getMomo().getMomoNum(), bean.getMomo().getStream_stream_num(), bean.getMomo().getPhase(),
                bean.getMomo().getMomo_contents(), bean.getMomo().getCreate_id(), bean.getMomo().getCreate_date(),
                bean.getMomo().getUpdate_id(), bean.getMomo().getUpdate_date(), bean.getMomo().getUser_master_member_id());
	}

}
