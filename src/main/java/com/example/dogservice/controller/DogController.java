package com.example.dogservice.controller;

import com.example.dogservice.vo.DogVo;
import com.example.dogservice.model.request.InsertDogRequest;
import com.example.dogservice.model.response.BaseResponse;
import com.example.dogservice.service.DogService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/dog")
public class DogController {

    private final DogService dogService;

    @Autowired
    public DogController(DogService dogService) {
        this.dogService = dogService;
    }


    @ApiOperation(value="取得小狗列表", notes="返回帳戶下所有的小狗")
    @GetMapping("")
    public List<DogVo> getDogList() {
        return dogService.getDogs();
    }

    @ApiOperation(value="取得小狗信息", notes="根據小狗 ID 返回信息")
    @ApiResponses({
            @ApiResponse(code = 404, message="小狗 ID 不存在"),
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity getDog(@PathVariable("id")  long id) {
        Optional<DogVo> dog = dogService.getDog(id);
        if (dog.isPresent()) {
            return new ResponseEntity<>(dog.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new BaseResponse(404, String.format("小狗 %s 不存在", id)), HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value="添加小狗", notes="添加一隻小狗到帳戶下")
    @PostMapping(path = "", consumes={"application/json"})
    public ResponseEntity addDog(@RequestBody InsertDogRequest data) {
        BaseResponse response = dogService.insertDog(data);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ApiOperation(value="編輯小狗信息", notes="編輯特定 ID 小狗信息")
    @ApiResponses({
            @ApiResponse(code = 400, message="請求格式錯誤"),
            @ApiResponse(code = 404, message="小狗 ID 不存在"),
    })
    @PutMapping(path = "/{id}", consumes = {"application/json"})
    public ResponseEntity updateDog(@PathVariable("id")  long id, @RequestBody InsertDogRequest data) {
        Optional<DogVo> dog = dogService.getDog(id);
        if (dog.isPresent()) {
            dogService.updateDog(Long.valueOf(id), data);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(new BaseResponse(404, String.format("小狗 %s 不存在", id)), HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value="移除小狗信息", notes="移除特定 ID 小狗信息")
    @ApiResponses({
            @ApiResponse(code = 400, message="請求格式錯誤"),
            @ApiResponse(code = 404, message="小狗 ID 不存在"),
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity removeDog(@PathVariable("id")  String id) {
        Optional<DogVo> dog = dogService.getDog(Long.valueOf(id));
        if (dog.isPresent()) {
            dogService.removeDog(Long.valueOf(id));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(new BaseResponse(404, String.format("小狗 %s 不存在", id)), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/test")
    public String test() throws Exception {
        throw new Exception("fuck alvin");
    }

}
