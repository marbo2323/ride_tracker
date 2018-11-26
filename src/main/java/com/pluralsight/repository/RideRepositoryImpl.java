package com.pluralsight.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.pluralsight.model.Ride;
import com.pluralsight.repository.util.RideRowMapper;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

	@Autowired 	
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Ride> getRides() {		
		List <Ride> rides = jdbcTemplate.query("select * from ride", new RideRowMapper());
		return rides;
	}

	@Override
	public Ride createRide(Ride ride) {
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.setGeneratedKeyName("id");
		
		Map<String, Object> data = new HashMap<>();
		data.put("name", ride.getName());
		data.put("duration", ride.getDuration());
		
		List<String> columns = new ArrayList<>();
		columns.add("name");
		columns.add("duration");
		
		insert.setTableName("ride");
		insert.setColumnNames(columns);
		Number id = insert.executeAndReturnKey(data);
		
		return getRide(id.intValue());
	}
	
	
	public Ride getRide(Integer id) {
		Ride ride = jdbcTemplate.queryForObject("select * from ride where id = ?", new RideRowMapper(), id);
		return ride;
	}
	
}
