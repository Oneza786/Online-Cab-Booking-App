package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
