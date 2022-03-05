package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Output;

public interface OutputRepository extends JpaRepository<Output, Long> {

}
