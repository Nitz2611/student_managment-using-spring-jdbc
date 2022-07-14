package com.spring.jdbc;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.spring.jdbc.dao.StudentDao;
import com.spring.jdbc.dao.StudentDaoImp;

@Configuration
@ComponentScan(basePackages = {"com.spring.jdbc.dao"} )
public class JdbcConfig {

	@Bean("ds")
	public DataSource getDataSource() {
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/springjdbc");
		ds.setUsername("nitish");
		ds.setPassword("password");
		return ds;
	}
	
	@Bean("jdbcTemplate")
	public JdbcTemplate getTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate;
	}
	
	
	//we can also declare bean intead of using component annotation
//	@Bean("studentDao")
//	public StudentDao getStudentDao() {
//		
//		StudentDaoImp studentDao=new StudentDaoImp();
//		studentDao.setJdbcTemplate(getTemplate());
//		return studentDao;
//	}
	
}
