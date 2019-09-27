package edu.ftn.isa.dto;

import java.util.List;

import lombok.Data;

public @Data class HotelStatisticsDTO {
	

		float avgHotelRate;
		
		List<RoomForStatsDTO> rooms;
		
		private Long dailyAttendance;
		
		private Long weeklyAttendance;
		
		private Long monthlyAttendance;
		
	
}
