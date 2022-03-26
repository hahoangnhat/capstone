package com.edu.capstone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.capstone.entity.ClassSubject;
import com.edu.capstone.entity.key.CSKey;

public interface ClassSubjectRepository extends JpaRepository<ClassSubject, CSKey> {

	List<ClassSubject> findByKeyClassId(String classId);
	
}