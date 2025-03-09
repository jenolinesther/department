package com.example.department.model;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class departmentModel {
    
    @Id
    private int department_id;
	private String name;
    private String code;   
    private int college_id;   
    private String contact_email;   
    private String phone_number;   
    private boolean is_active;   
    
    @Column(updatable = false)
    private Timestamp created_at;
    private Timestamp updated_at;
    
    @PrePersist
    protected void onCreate() {
        if (this.created_at == null) {
        	this.created_at = new Timestamp(System.currentTimeMillis());
        }
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
    	this.updated_at = new Timestamp(System.currentTimeMillis());
    }
    
    public departmentModel() {
		super();
	}
    
    public departmentModel(int department_id, String name, String code, int college_id, String contact_email,
			String phone_number, boolean is_active, Timestamp created_at, Timestamp updated_at) {
		super();
		this.department_id = department_id;
		this.name = name;
		this.code = code;
		this.college_id = college_id;
		this.contact_email = contact_email;
		this.phone_number = phone_number;
		this.is_active = is_active;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

    public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getCollege_id() {
		return college_id;
	}

	public void setCollege_id(int college_id) {
		this.college_id = college_id;
	}

	public String getContact_email() {
		return contact_email;
	}

	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
}