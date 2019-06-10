package com.example.dogservice.dao;

import com.example.dogservice.vo.DogVo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogDao extends JpaRepository<DogVo, Long> {

}
