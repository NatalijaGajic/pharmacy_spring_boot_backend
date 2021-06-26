package com.example.reservation_app.jdbc_repository;

import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.reservation_app.model.Reservation;


@Repository
public class ReservationJdbcRepositoryImpl implements ReservationJdbcRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void save(Reservation reservation) {
		String sql = "";
		Integer id = reservation.getId();
		if(id == null) {
			sql = "INSERT INTO reservation(\r\n"
					+ "	date_of_reservation, id, date_of_pick_up, is_cancelled, status, "
					+ "client_id, reservation_code"
					+ "	VALUES (?, ?, ?, ?, ?, ?, ?);";
			String idQuery = "SELECT max(id) from reservation";
			id = jdbcTemplate.queryForObject(idQuery, Integer.class);
			id++;
			jdbcTemplate.update(sql, new Object[] { reservation.getDateOfReservation(), id, reservation.getDateOfPickUp(),
					reservation.isCancelled(), reservation.getStatus(), reservation.getClientId(), reservation.getReservationCode()});
		}
		else {
			sql = "UPDATE reservation\r\n"
					+ "	SET status=?\r\n"
					+ "	WHERE id="+Integer.toString(id);
			jdbcTemplate.update(sql, new Object[] {reservation.getStatus()});
		}
		
	}

	@Override
	public Reservation findById(Integer id) {
		String sql = "SELECT * FROM reservation WHERE ID = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Reservation(
                        rs.getDate("date_of_reservation"),
                        rs.getDate("date_of_pick_up"),
                		rs.getBoolean("is_cancelled"),
                        rs.getString("status"),
                        rs.getInt("client_id"),
                        UUID.fromString(rs.getString("reservation_code"))
                ));
	}

	@Override
	public Collection<Reservation> findAll() {
		String query = "select * from reservation";
		return jdbcTemplate.query(query, (rs, rowNum) ->
		new Reservation(
                rs.getDate("date_of_reservation"),
                rs.getDate("date_of_pick_up"),
        		rs.getBoolean("is_cancelled"),
                rs.getString("status"),
                rs.getInt("client_id"),
                UUID.fromString(rs.getString("reservation_code"))
        ));
	}

	@Override
	public void delete(Reservation reservation) {
		jdbcTemplate.execute("delete from reservation where id="+reservation.getId().toString());
		
	}

	@Override
	public Collection<Reservation> getReservationsForClient(Integer clientId) {
		String query = "select * from reservation where client_id="+clientId;
		return jdbcTemplate.query(query, (rs, rowNum) ->
		new Reservation(
                rs.getDate("date_of_reservation"),
                rs.getDate("date_of_pick_up"),
        		rs.getBoolean("is_cancelled"),
                rs.getString("status"),
                rs.getInt("client_id"),
                UUID.fromString(rs.getString("reservation_code"))
        ));
	}

}
