package com.example.system_user_app.jdbc_repository;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.system_user_app.model.Client;

@Repository
public class ClientJdbcRepositoryImpl implements ClientJdbcRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void save(Client user) {
		String sql = "";
		Integer id = user.getId();
		if(user.getId() == null) {
			sql = "INSERT INTO system_user(\r\n"
					+ "	user_type, id, date_of_birth, email, first_name, last_name, password, telephone, username,"
					+ " number_of_penalties, system_role_id)\r\n"
					+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			String idQuery = "SELECT max(id) from system_user";
			id = jdbcTemplate.queryForObject(idQuery, Integer.class);
			id++;
			jdbcTemplate.update(sql, new Object[] { "client", id, user.getDateOfBirth(), user.getEmail(),
					user.getFirstName(), user.getLastName(), user.getPassword(), user.getTelephone(),
					user.getUsername(), user.getNumberOfPenalties(), user.getSystemRole().getId()});
		}
		else {
			sql = "UPDATE system_user\r\n"
					+ "	SET date_of_birth=?, first_name=?, last_name=?,telephone=?, username=?\r\n"
					+ "	WHERE user_type='client' and id="+Integer.toString(id);
			jdbcTemplate.update(sql, new Object[] { user.getDateOfBirth(),
					user.getFirstName(), user.getLastName(), user.getTelephone(),
					user.getUsername()});
		}

		
	}

	@Override
	public Client findById(Integer id) {
		String sql = "SELECT * FROM system_user WHERE ID = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Client(
                		rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("password"),
                        rs.getString("telephone"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getInt("system_role_id"),
                        rs.getDate("date_of_birth"),
                        rs.getInt("id"),
                        rs.getInt("number_of_penalties")
                ));
	}

	@Override
	public Collection<Client> findAll() {
		String query = "select * from system_user where user_type='client'";
		return jdbcTemplate.query(query, (rs, rowNum) ->
        new Client(
        		rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("password"),
                rs.getString("telephone"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getInt("system_role_id"),
                rs.getDate("date_of_birth"),
                rs.getInt("id"),
                rs.getInt("number_of_penalties")
        ));
	}

	@Override
	public Collection<Client> findByUsernameContainingIgnoreCase(String username) {
		String query = "select * from system_user where user_type='client' and username like '%"+username+"%'";
		return jdbcTemplate.query(query, (rs, rowNum) ->
        new Client(
        		rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("password"),
                rs.getString("telephone"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getInt("system_role_id"),
                rs.getDate("date_of_birth"),
                rs.getInt("id"),
                rs.getInt("number_of_penalties")
        ));
	}

	@Override
	public void delete(Client client) {
		jdbcTemplate.execute("delete from system_user where id="+client.getId().toString());
		
	}

}
