package com.example.dogservice.service;

import com.example.dogservice.dao.DogDao;
import com.example.dogservice.model.response.BaseResponse;
import com.example.dogservice.vo.DogVo;
import com.example.dogservice.model.request.InsertDogRequest;
import com.example.dogservice.model.response.InsertDogResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DogService {

    @Autowired
    private DogDao dogDao;

    @Transactional
    public List<DogVo> getDogs() {
        return dogDao.findAll();
    }

    @Transactional
    public Optional<DogVo> getDog(Long id) {
        return dogDao.findById(id);
    }

    @Transactional
    public BaseResponse insertDog(InsertDogRequest request) {
        DogVo data = new DogVo();
        data.from(request);
        data = dogDao.save(data);
        return InsertDogResponse.builder().id(data.getId()).statusCode(201).message("success").build();
    }

    @Transactional
    public boolean updateDog(Long id, InsertDogRequest request) {
        return dogDao.findById(id).map(dog -> {
            dog.from(request);
            dogDao.save(dog);
            return true;
        }).orElse(false);
    }

    @Transactional
    public boolean removeDog(Long id) {
        dogDao.deleteById(id);
        return true;
    }
}
