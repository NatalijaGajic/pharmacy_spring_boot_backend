package com.example.pharmacy.repository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.pharmacy.jpa.Pharmacist;
import org.springframework.jdbc.core.RowMapper;


public class PharmacistRepositoryImpl implements PharmacistCustomRepository{

	@Autowired 
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Collection<Pharmacist> findAllActivePharmacists() {
		
		String SELECT_ALL_QUERY = "select * from system_user where user_type like 'Pharmacist' and end_date_of_contract is null";
		return this.jdbcTemplate.query(SELECT_ALL_QUERY, getMap());
	}
	
	@Override
	public Collection<Pharmacist> findAllInactivePharmacists() {
		
		String SELECT_ALL_QUERY = "select * from system_user where user_type like 'Pharmacist' and end_date_of_contract is not null";
		return this.jdbcTemplate.query(SELECT_ALL_QUERY, getMap());
	}


	private RowMapper<Pharmacist> getMap(){
		  // Lambda block
		  RowMapper<Pharmacist> userMap = (rs, rowNum) -> {
			  Pharmacist user = new Pharmacist();
			  user.setId(rs.getInt("id"));
			  user.setDateOfBirth(rs.getDate("date_of_birth"));
			  user.setEmail(rs.getString("email"));
			  user.setFirstName(rs.getString("first_name"));
			  user.setLastName(rs.getString("last_name"));
			  user.setTelephone(rs.getString("telephone"));
			  user.setSalary(rs.getDouble("salary"));
			  user.setStartDateOfContract(rs.getDate("start_date_of_contract"));
			  user.setEndDateOfContract(rs.getDate("end_date_of_contract"));
			  user.setUsername(rs.getString("username"));
		      return user;
		  };
		  return userMap;
		}
	
}
