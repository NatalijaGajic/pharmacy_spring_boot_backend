package com.example.reservation_app.jdbc_repository;

import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
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
	public Reservation save(Reservation reservation) {
		String sql = "";
		Integer id = reservation.getId();
		if(id == null) {
			sql = "INSERT INTO reservation(\r\n"
					+ "	date_of_reservation, id, date_of_pick_up, is_cancelled, status, "
					+ "client_id, reservation_code, price)"
					+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
			String idQuery = "SELECT max(id) from reservation";
			id = jdbcTemplate.queryForObject(idQuery, Integer.class);
			id++;
			jdbcTemplate.update(sql, new Object[] { reservation.getDateOfReservation(), id, reservation.getDateOfPickUp(),
					reservation.isCancelled(), reservation.getStatus(), reservation.getClientId(), asBytes(reservation.getReservationCode()), reservation.getPrice()});
		}
		else {
			sql = "UPDATE reservation\r\n"
					+ "	SET status=?\r\n"
					+ "	WHERE id="+Integer.toString(id);
			jdbcTemplate.update(sql, new Object[] {reservation.getStatus()});
		}
		sql = "SELECT * FROM reservation WHERE ID = ?";

       return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Reservation(
                		rs.getInt("id"),
                        rs.getDate("date_of_reservation"),
                        rs.getDate("date_of_pick_up"),
                		rs.getBoolean("is_cancelled"),
                        rs.getString("status"),
                        rs.getInt("client_id"),
                        UUID.fromString(getGuidFromByteArray(rs.getBytes("reservation_code")))
                ));
	}

	@Override
	public Reservation findById(Integer id) {
		String sql = "SELECT * FROM reservation WHERE ID = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Reservation(
                		rs.getInt("id"),
                        rs.getDate("date_of_reservation"),
                        rs.getDate("date_of_pick_up"),
                		rs.getBoolean("is_cancelled"),
                        rs.getString("status"),
                        rs.getInt("client_id"),
                        UUID.fromString(getGuidFromByteArray(rs.getBytes("reservation_code")))
                ));
	}

	@Override
	public Collection<Reservation> findAll() {
		String query = "select * from reservation";
		return jdbcTemplate.query(query, (rs, rowNum) ->
		new Reservation(
				rs.getInt("id"),
                rs.getDate("date_of_reservation"),
                rs.getDate("date_of_pick_up"),
        		rs.getBoolean("is_cancelled"),
                rs.getString("status"),
                rs.getInt("client_id"),
                UUID.fromString(getGuidFromByteArray(rs.getBytes("reservation_code")))
        ));
	}

	@Override
	public void delete(Reservation reservation) {
		String sql = "UPDATE reservation\r\n"
				+ "	SET status=?\r\n"
				+ "	WHERE id="+Integer.toString(reservation.getId());
		jdbcTemplate.update(sql, new Object[] {"Deleted"});
		
	}

	@Override
	public Collection<Reservation> getReservationsForClient(Integer clientId) {
		String query = "select * from reservation where client_id="+clientId;
		return jdbcTemplate.query(query, (rs, rowNum) ->
		new Reservation(
				rs.getInt("id"),
                rs.getDate("date_of_reservation"),
                rs.getDate("date_of_pick_up"),
        		rs.getBoolean("is_cancelled"),
                rs.getString("status"),
                rs.getInt("client_id"),
                UUID.fromString(getGuidFromByteArray(rs.getBytes("reservation_code")))
        ));
	}

	@Override
	public Collection<Reservation> findAllByDateOfReservationBetween(Date dateOfReservationStart,
			Date dateOfReservationEnd) {
		String pattern = "yyyy/MM/dd";
		DateFormat df = new SimpleDateFormat(pattern);
		String query = "select * from reservation where date_of_reservation between '"+df.format(dateOfReservationStart)
				+"' and '"+df.format(dateOfReservationEnd)+"'";
		return jdbcTemplate.query(query, (rs, rowNum) ->
		new Reservation(
				rs.getInt("id"),
                rs.getDate("date_of_reservation"),
                rs.getDate("date_of_pick_up"),
        		rs.getBoolean("is_cancelled"),
                rs.getString("status"),
                rs.getInt("client_id"),
                UUID.fromString(getGuidFromByteArray(rs.getBytes("reservation_code")))
        ));
	}

	@Override
	public Collection<Reservation> findAllByDateOfPickUpBetween(Date dateOfPickUpStart, Date dateOfPickUpEnd) {
		String pattern = "yyyy/MM/dd";
		DateFormat df = new SimpleDateFormat(pattern);
		String query = "select * from reservation where date_of_pick_up between '"+df.format(dateOfPickUpStart)
				+"' and '"+df.format(dateOfPickUpEnd)+"'";
		return jdbcTemplate.query(query, (rs, rowNum) ->
		new Reservation(
				rs.getInt("id"),
                rs.getDate("date_of_reservation"),
                rs.getDate("date_of_pick_up"),
        		rs.getBoolean("is_cancelled"),
                rs.getString("status"),
                rs.getInt("client_id"),
                UUID.fromString(getGuidFromByteArray(rs.getBytes("reservation_code")))
        ));
	}
	
	@Override
	public Collection<Reservation> findAllByDateOfReservation(Date dateOfReservation) {
		String pattern = "yyyy/MM/dd";
		DateFormat df = new SimpleDateFormat(pattern);
		String query = "select * from reservation where date_of_reservation='"+df.format(dateOfReservation)+"'";
		return jdbcTemplate.query(query, (rs, rowNum) ->
		new Reservation(
				rs.getInt("id"),
		        rs.getDate("date_of_reservation"),
		        rs.getDate("date_of_pick_up"),
				rs.getBoolean("is_cancelled"),
		        rs.getString("status"),
		        rs.getInt("client_id"),
		        UUID.fromString(getGuidFromByteArray(rs.getBytes("reservation_code")))
		));
	}

	@Override
	public Collection<Reservation> findAllByDateOfPickUp(Date dateOfPickUp) {
		String pattern = "yyyy/MM/dd";
		DateFormat df = new SimpleDateFormat(pattern);
		String query = "select * from reservation where date_of_pick_up='"+df.format(dateOfPickUp)+"'";
		return jdbcTemplate.query(query, (rs, rowNum) ->
		new Reservation(
				rs.getInt("id"),
		        rs.getDate("date_of_reservation"),
		        rs.getDate("date_of_pick_up"),
				rs.getBoolean("is_cancelled"),
		        rs.getString("status"),
		        rs.getInt("client_id"),
		        UUID.fromString(getGuidFromByteArray(rs.getBytes("reservation_code")))
		));
	}

	private static String getGuidFromByteArray(byte[] bytes) {
	    ByteBuffer bb = ByteBuffer.wrap(bytes);
	    long high = bb.getLong();
	    long low = bb.getLong();
	    UUID uuid = new UUID(high, low);
	    return uuid.toString();
	}
	
	 private static byte[] asBytes(UUID uuid) {
		    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
		    bb.putLong(uuid.getMostSignificantBits());
		    bb.putLong(uuid.getLeastSignificantBits());
		    return bb.array();
	}


}
