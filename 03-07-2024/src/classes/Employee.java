package classes;

import java.sql.SQLException;

import issues.EmployeeIssue;

public class Employee {
	private Long id;
	private String surName;
	private String lastName;
	private Long departmentId;
	private Long positionId;
	
	public Employee() {
		
	}

	public Employee(String surName, String lastName, Long departmentId, Long positionId) {
		super();
		this.id = null;
		this.surName = surName;
		this.lastName = lastName;
		this.departmentId = departmentId;
		this.positionId = positionId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	@Override
	public String toString() {
		Long depId=getDepartmentId();
		String dep=null;
		
		Long posId=getPositionId();
		String pos=null;
		
		try {
			 dep = EmployeeIssue.selectDepartmentID(depId);
			 pos = EmployeeIssue.selectPositionID(posId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "Personel No= " + id + ", İsim= " + surName + ", Soyisim= " + lastName + ", Departman= "
				+ dep + ", Ünvan= " + pos;
	}
		

}
