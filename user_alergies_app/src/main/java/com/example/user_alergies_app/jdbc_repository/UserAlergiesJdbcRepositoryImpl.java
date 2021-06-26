package com.example.user_alergies_app.jdbc_repository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.user_alergies_app.model.UserAlergies;

@Repository
public class UserAlergiesJdbcRepositoryImpl implements UserAlergiesJdbcRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void save(UserAlergies userAlergies) {
		String sql = "";
		sql = "INSERT INTO system_user_alergy_medicines(\r\n"
				+ "	client_id, alergy_medicines_id)"
				+ "\r\n"
				+ "	VALUES ( ?, ?);";

		jdbcTemplate.update(sql, new Object[] { 
				userAlergies.getUserId(), userAlergies.getMedicineId()		
		});
		
	}

	@Override
	public Collection<UserAlergies> findMedicineByUserId(Integer id) {
		String sql = "SELECT * FROM system_user_alergy_medicines WHERE client_id =" + id.toString();
        return jdbcTemplate.query(sql,  (rs, rowNum) ->
                new UserAlergies(
                        rs.getInt("client_id"),
                        rs.getInt("alergy_medicines_id")
                ));
		
	}

	@Override
	public void delete(UserAlergies userAlergies) {
		// TODO Auto-generated method stub
		
	}


}
