package com.example.system_user_app.jdbc_repository;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.system_user_app.model.Pharmacist;

@Repository
public class PharmacistJdbcRepositoryImpl implements PharmacistJdbcRepository {


	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void save(Pharmacist user) {
		String sql = "";
		Integer id = user.getId();
		if(user.getId() == null) {
			sql = "INSERT INTO system_user(\r\n"
					+ "	user_type, id, date_of_birth, email, first_name, last_name, password, telephone, username,"
					+ " end_date_of_contract, salary, start_date_of_contract, system_role_id)\r\n"
					+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			String idQuery = "SELECT max(id) from system_user";
			id = jdbcTemplate.queryForObject(idQuery, Integer.class);
			id++;
			jdbcTemplate.update(sql, new Object[] { "pharmacist", id, user.getDateOfBirth(), user.getEmail(),
					user.getFirstName(), user.getLastName(), user.getPassword(), user.getTelephone(),
					user.getUsername(), user.getEndDateOfContract(), user.getSalary(), 
					user.getStartDateOfContract(), user.getSystemRole().getId()});
		}
		else {
			sql = "UPDATE system_user\r\n"
					+ "	SET date_of_birth=?, first_name=?, last_name=?,telephone=?, username=?, end_date_of_contract=?, salary=?, start_date_of_contract=?\r\n"
					+ "	WHERE user_type='pharmacist' and id="+Integer.toString(id);
			jdbcTemplate.update(sql, new Object[] { user.getDateOfBirth(),
					user.getFirstName(), user.getLastName(), user.getTelephone(),
					user.getUsername(), user.getEndDateOfContract(), user.getSalary(), 
					user.getStartDateOfContract()});
		}


	}

	@Override
	public Collection<Pharmacist> findAll() {
		String query = "select * from system_user where user_type='pharmacist'";
		return jdbcTemplate.query(query, (rs, rowNum) ->
        new Pharmacist(
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("password"),
                rs.getString("telephone"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getInt("system_role_id"),
                rs.getDate("date_of_birth"),
                rs.getInt("id"),
                rs.getDouble("salary"),
                rs.getDate("start_date_of_contract"),
                rs.getDate("end_date_of_contract")
        ));
	}

	@Override
	public Pharmacist findById(Integer id) {
		String sql = "SELECT * FROM system_user WHERE ID = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Pharmacist(
                		rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("password"),
                        rs.getString("telephone"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getInt("system_role_id"),
                        rs.getDate("date_of_birth"),
                        rs.getInt("id"),
                        rs.getDouble("salary"),
                        rs.getDate("start_date_of_contract"),
                        rs.getDate("end_date_of_contract")
                ));
	}

	@Override
	public Collection<Pharmacist> findAllActivePharmacists() {
		return null;
	}

	@Override
	public Collection<Pharmacist> findAllInactivePharmacists() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Pharmacist> findByUsernameContainingIgnoreCase(String username) {
		String query = "select * from system_user where user_type='pharmacist' and username like '%"+username+"'";
		return jdbcTemplate.query(query, (rs, rowNum) ->
        new Pharmacist(
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("password"),
                rs.getString("telephone"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getInt("system_role_id"),
                rs.getDate("date_of_birth"),
                rs.getInt("id"),
                rs.getDouble("salary"),
                rs.getDate("start_date_of_contract"),
                rs.getDate("end_date_of_contract")
        ));
	}

	//TODO: cascade delete
	@Override
	public void delete(Pharmacist pharmacist) {
		jdbcTemplate.execute("delete from system_user where id="+pharmacist.getId().toString());
		
	}


	
}
