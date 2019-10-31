package com.resource.common.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.resource.common.constants.WorkType;
import com.resource.common.validation.GroupHRSettings;
import com.resource.common.validation.GroupWorkTimeSettings;
import com.resource.common.validation.TimeString;
import com.resource.common.validation.ValidNumber;

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
	
	@NotBlank(message="Name should not be empty", groups = GroupHRSettings.class)
	@Column(nullable=false, length=45)
	private String name;

	@Lob
	private String description;

	@NotBlank(message="Code should not be empty", groups = GroupHRSettings.class)
	@Column(length=45)
	private String code;
	
	@Column(name="work_type", length=5, columnDefinition = "integer default 1")
	private Integer workType;

	@TimeString(message="Core end time not valid", groups = GroupWorkTimeSettings.class)
	@Column(name="core_end_time", length=5, columnDefinition = "varchar(5) default '18.00'")
	private String coreEndTime;

	@TimeString(message="Core start time not valid", groups = GroupWorkTimeSettings.class)
	@Column(name="core_start_time", length=5, columnDefinition = "varchar(5) default '9.00'")
	private String coreStartTime;

	@Column(name="daily_work_hours", length=5, columnDefinition = "integer default 8")
	private Integer dailyWorkHours;

	@ValidNumber(message="Days per week must be valid numbers", groups = GroupWorkTimeSettings.class)
	@Column(name="days_per_week", length=5, columnDefinition = "varchar(5) default '5'")
	private String daysPerWeek;
	
	@TimeString(message="Day start time not valid", groups = GroupWorkTimeSettings.class)
	@Column(name="first_half_day_start_time", length=5, columnDefinition = "varchar(5) default '9:00'")
	private String firstHalfDayStartTime;
	
	@TimeString(message="Day end time not valid", groups = GroupWorkTimeSettings.class)
	@Column(name="first_half_day_end_time", length=5, columnDefinition = "varchar(5) default '13:30'")
	private String firstHalfDayEndTime;
	
	@TimeString(message="Day end time not valid", groups = GroupWorkTimeSettings.class)
	@Column(name="second_half_day_end_time", length=5, columnDefinition = "varchar(5) default '18:30'")
	private String secondHalfDayEndTime;

	@TimeString(message="Day start time not valid", groups = GroupWorkTimeSettings.class)
	@Column(name="second_half_day_start_time", length=5, columnDefinition = "varchar(5) default '14:00'")
	private String secondHalfDayStartTime;

	@Column(name="include_permissions", columnDefinition = "tinyint(1) default '0'")
	private Boolean includePermissions;

	@TimeString(message="Lunch start break end time not valid", groups = GroupWorkTimeSettings.class)
	@Column(name="lunch_break_end_time", length=5, columnDefinition = "varchar(5) default '14:00'")
	private String lunchBreakEndTime;

	@Column(name="lunch_break_minutes", length=5, columnDefinition = "integer(5) default 60")
	private Integer lunchBreakMinutes;

	@TimeString(message="Lunch start break end time not valid", groups = GroupWorkTimeSettings.class)
	@Column(name="lunch_break_start_time", length=5, columnDefinition = "varchar(5) default '13:00'")
	private String lunchBreakStartTime;

	@Column(name="first_break_minutes", length=5, columnDefinition = "integer(5) default 15")
	private Integer firstBreakMinutes;
	
	@TimeString(message="Break start time not valid", groups = GroupWorkTimeSettings.class)
	@Column(name="first_break_start_time", length=5, columnDefinition = "varchar(5) default '11:00'")
	private String firstBreakStartTime;
	
	@TimeString(message="Break end time not valid", groups = GroupWorkTimeSettings.class)
	@Column(name="first_break_end_time", length=5, columnDefinition = "varchar(5) default '11:15'")
	private String firstBreakEndTime;

	@Column(name="second_break_minutes", length=5, columnDefinition = "integer(5) default 15")
	private Integer secondBreakMinutes;

	@TimeString(message="Break start time not valid", groups = GroupWorkTimeSettings.class)
	@Column(name="second_break_start_time", length=5, columnDefinition = "varchar(5) default '16:00'")
	private String secondBreakStartTime;
	
	@TimeString(message="Break end time not valid", groups = GroupWorkTimeSettings.class)
	@Column(name="second_break_end_time", length=5, columnDefinition = "varchar(5) default '16:15'")
	private String secondBreakEndTime;

	@Column(name="no_of_employees", columnDefinition = "integer default 0")
	private Integer noOfEmployees;

	@Column(name="permissions_minutes", columnDefinition = "varchar(5) default '5'")
	private Integer permissionsMinutes;
	
	@Column(name="no_of_permissions", columnDefinition = "integer default 1")
	private Integer noOfPermissions;

	@Column(name="probation_period", columnDefinition = "integer default 6")
	private int probationPeriod;

	@Column(name="notice_period", columnDefinition = "integer default 30")
	private int noticePeriod;

	@TimeString(message="Work start time not valid", groups = GroupWorkTimeSettings.class)
	@Column(name="work_end_time", length=5, columnDefinition = "varchar(5) default '18.00'")
	private String workEndTime;

	@TimeString(message="Work end time not valid", groups = GroupWorkTimeSettings.class)
	@Column(name="work_start_time", length=5, columnDefinition = "varchar(5) default '9.00'")
	private String workStartTime;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="company_id", nullable=false)
	private Company company;

	@OneToMany(mappedBy="employeeType")
	private List<EmploymentRelationship> employmentRelationships;
	
	public String getWorkTime() {
		return this.getWorkStartTime() +" - "+ this.getWorkEndTime();
	}
	
	public String getCoreTime() {
		return this.getCoreStartTime() +" - "+ this.getCoreEndTime();
	}
	
	public String getWorkTypeName() {
		int workType = this.getWorkType() == null ? 0 : this.getWorkType();
		return WorkType.getWorkType(workType).getName();
	}
	
	public String getFirstHalf() {
		return this.getFirstHalfDayStartTime() +" - "+ this.getFirstHalfDayEndTime();
	}
	
	public String getSecondHalf() {
		return this.getSecondHalfDayStartTime() +" - "+ this.getSecondHalfDayEndTime();
	}
	
	public String getBreak1() {
		return this.getFirstBreakStartTime() +" - "+ this.getFirstBreakEndTime();
	}
	
	public String getBreak2() {
		return this.getSecondBreakStartTime() +" - "+ this.getSecondBreakEndTime();
	}
	
	public String getLunchBreak() {
		return this.getLunchBreakStartTime() +" - "+ this.getLunchBreakEndTime();
	}
}