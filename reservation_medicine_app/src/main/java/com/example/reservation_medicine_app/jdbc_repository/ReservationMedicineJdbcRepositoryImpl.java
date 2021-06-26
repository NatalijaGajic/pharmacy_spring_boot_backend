package com.example.reservation_medicine_app.jdbc_repository;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.reservation_medicine_app.model.ReservationMedicine;

@Repository
public class ReservationMedicineJdbcRepositoryImpl implements ReservationMedicineJdbcRepository {


	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void save(ReservationMedicine reservationMedicine) {
		String sql = "";
		sql = "INSERT INTO reservation_medicine(\r\n"
				+ "	id, amount, medicine_id, reservation_id)"
				+ "\r\n"
				+ "	VALUES (?, ?, ?, ?);";
		String idQuery = "SELECT max(id) from reservation_medicine";
		Integer id = jdbcTemplate.queryForObject(idQuery, Integer.class);
		id++;
		jdbcTemplate.update(sql, new Object[] { id, reservationMedicine.getAmount(), 
		reservationMedicine.getMedicineId(), reservationMedicine.getReservationId()		
		});
	}
	
	@Override
	public Collection<ReservationMedicine> findByReservationId(Integer id) {
		String sql = "SELECT * FROM reservation_medicine WHERE reservation_id =" + id.toString();
        return jdbcTemplate.query(sql,  (rs, rowNum) ->
                new ReservationMedicine(
                        rs.getInt("id"),
                        rs.getInt("amount"),
                        rs.getInt("medicine_id"),
                        rs.getInt("reservation_id")
                ));
	}
}
