package com.CH22_Project.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Owner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long owner_id;
	@Column(nullable = false, unique = true)
	private String ownerusername;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, unique = true)
	private long ownerphone;
	@Column(nullable = false)
	private String hostelname;
	@Column(nullable = false)
	private String hosteladdress;
	@Column(nullable = false)
	private String ownerpassword;
	
//	@OneToMany(mappedBy = "owner")
//	private List<Token> tokens;
	
//	@OneToOne(mappedBy = "owner", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//	private VacancyDetails vacancyDetails;
	
//	@Override
//    public String toString() {
//        return "Owner{owner_id=" + owner_id + 
//               ", ownerusername='" + ownerusername + "'" + 
//               ", name='" + name + "'" +
//               ", hostelname='" + hostelname + "'" + 
//               ", hosteladdress='" + hosteladdress + "'" + 
//               ", vacancyDetails=" + (vacancyDetails != null ? vacancyDetails.getVacancy() : "N/A") + "}";
//    }

}
