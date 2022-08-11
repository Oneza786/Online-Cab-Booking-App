package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.entities.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
