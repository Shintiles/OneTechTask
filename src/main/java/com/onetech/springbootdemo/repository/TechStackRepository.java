package com.onetech.springbootdemo.repository;

import com.onetech.springbootdemo.domain.TechStack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechStackRepository extends JpaRepository<TechStack, Long> {
}
