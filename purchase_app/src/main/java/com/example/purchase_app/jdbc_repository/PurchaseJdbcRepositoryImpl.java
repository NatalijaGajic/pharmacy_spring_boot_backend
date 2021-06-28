package com.example.purchase_app.jdbc_repository;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.purchase_app.model.Purchase;

@Repository
public class PurchaseJdbcRepositoryImpl implements PurchaseJdbcRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public void save(Purchase purchase) {
		String sql = "";
		sql = "INSERT INTO purchase(\r\n"
				+ "	id, date_of_purchase, payment_type, pharmacist_id, reservation_id)"
				+ "\r\n"
				+ "	VALUES (?, ?, ?, ?);";
		String idQuery = "SELECT max(id) from purchase";
		Integer id = jdbcTemplate.queryForObject(idQuery, Integer.class);
		id++;
		jdbcTemplate.update(sql, new Object[] { id, purchase.getDateOfPurchase(), 
		purchase.getPaymentType(),purchase.getPharmacistId() ,purchase.getReservationId()		
		});
	}
	
	@Override
	public Collection<Purchase> findAll() {
		String sql = "SELECT * FROM purchase";
      return jdbcTemplate.query(sql,  (rs, rowNum) ->
              new Purchase(
                      rs.getInt("id"),
                      rs.getDate("date_of_purchase"),
                      rs.getString("payment_type"),
                      rs.getInt("pharmacist_id"),
                      rs.getInt("reservation_id")
              ));
	}

	@Override
	public Purchase findByPurchaseId(Integer id) {
		String sql = "SELECT * FROM purchase WHERE id =" + id.toString();
		List<Purchase> purchases = jdbcTemplate.query(sql,  (rs, rowNum) ->
              new Purchase(
            		  rs.getInt("id"),
                      rs.getDate("date_of_purchase"),
                      rs.getString("payment_type"),
                      rs.getInt("pharmacist_id"),
                      rs.getInt("reservation_id")
              ));
		if(purchases.size() > 0) {
			return purchases.get(0);
		}
		else {
			return null;
		}
	}

	@Override
	public Purchase findByReservationId(Integer id) {
		String sql = "SELECT * FROM purchase WHERE reservation_id =" + id.toString();
		List<Purchase> purchases = jdbcTemplate.query(sql,  (rs, rowNum) ->
              new Purchase(
            		  rs.getInt("id"),
                      rs.getDate("date_of_purchase"),
                      rs.getString("payment_type"),
                      rs.getInt("pharmacist_id"),
                      rs.getInt("reservation_id")
              ));
		if(purchases.size() > 0) {
			return purchases.get(0);
		}
		else {
			return null;
		}
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE from purchase where id="+ id;
		jdbcTemplate.execute(sql);
	}
}
