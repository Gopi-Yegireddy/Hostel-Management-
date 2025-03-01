package com.CH22_Project.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class VacancyDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long vacancy_id;
	@OneToOne
	@JoinColumn(referencedColumnName = "ownerUsername", name = "ownerusername",nullable = false, unique = true)
	private Owner owner;
	@Column(nullable = false)
	private String vacancy;
	@Column(nullable = false)
	private String city;
	
//	@Override
//    public String toString() {
//        return "VacancyDetails{vacancy_id=" + vacancy_id + 
//               ", vacancy='" + vacancy + "'" + 
//               ", ownerUsername='" + (owner != null ? owner.getOwnerusername() : "N/A") + "'}";
//    }
	
}
