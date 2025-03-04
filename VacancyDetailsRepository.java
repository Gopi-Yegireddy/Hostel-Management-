package com.CH22_Project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CH22_Project.models.VacancyDetails;


@Repository
public interface VacancyDetailsRepository extends JpaRepository<VacancyDetails, Long>{
	
	VacancyDetails findByOwnerOwnerusername(String username);
	
	Page<VacancyDetails> findByVacancyAndCity(String vacancyStatus, String city, Pageable pageable);

	Page<VacancyDetails> findByVacancy(String vacancyStatus, Pageable pageable);
	
	Page<VacancyDetails> findByCity(String prefix, Pageable pageable);
	
}
