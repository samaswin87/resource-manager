package com.resource.common.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the employee_types database table.
 *
 */
@Entity
@Table(name="employee_types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude= {"company", "employmentRelationships"})
@NamedQuery(name="EmployeeType.findAll", query="SELECT e FROM EmployeeType e")
public class EmployeeType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="break_style")
	private Integer breakStyle;

	@Column(length=45)
	private String code;

	@Column(name="core_end_time", length=5)
	private String coreEndTime;

	@Column(name="core_start_time", length=5)
	private String coreStartTime;

	@Column(name="daily_work_hours", length=5)
	private String dailyWorkHours;

	@Column(name="days_per_week", length=5)
	private String daysPerWeek;

	@Column(name="evening_break_end_time", length=5)
	private String eveningBreakEndTime;

	@Column(name="evening_break_minutes", length=5)
	private String eveningBreakMinutes;

	@Column(name="evening_break_start_time", length=5)
	private String eveningBreakStartTime;

	@Column(name="evening_half_day_end_time", length=5)
	private String eveningHalfDayEndTime;

	@Column(name="evening_half_day_start_time", length=5)
	private String eveningHalfDayStartTime;

	@Column(name="include_permissions")
	private Boolean includePermissions;

	@Column(name="lunch_break_end_time", length=5)
	private String lunchBreakEndTime;

	@Column(name="lunch_break_minutes", length=5)
	private String lunchBreakMinutes;

	@Column(name="lunch_break_start_time", length=5)
	private String lunchBreakStartTime;

	@Column(name="morning_break_end_time", length=5)
	private String morningBreakEndTime;

	@Column(name="morning_break_minutes", length=5)
	private String morningBreakMinutes;

	@Column(name="morning_break_start_time", length=5)
	private String morningBreakStartTime;

	@Column(name="morning_half_day_end_time", length=5)
	private String morningHalfDayEndTime;

	@Column(name="morning_half_day_start_time", length=5)
	private String morningHalfDayStartTime;

	@Column(nullable=false, length=45)
	private String name;

	@Column(name="no_of_employees", columnDefinition = "integer default 0")
	private Integer noOfEmployees;

	@Column(name="permissions_minutes")
	private Integer permissionsMinutes;

	@Column(name="probation_period")
	private int probationPeriod;

	@Column(name="shift_type")
	private int shiftType;

	@Column(name="work_end_time", length=5)
	private String workEndTime;

	@Column(name="work_start_time", length=5)
	private String workStartTime;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="company_id", nullable=false)
	private Company company;

	@OneToMany(mappedBy="employeeType")
	private List<EmploymentRelationship> employmentRelationships;

}