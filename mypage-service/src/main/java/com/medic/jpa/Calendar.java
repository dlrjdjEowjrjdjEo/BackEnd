package com.medic.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "calendar")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Calendar {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "calendar_id")
		private int calendarId;
		
		@Column(name = "routine_id")
		private int routineId;
		
		@Column(name = "user_id")
		private int userId;
		
		// yyyy-MM-dd
		private String date;

		private Boolean taken;
}
