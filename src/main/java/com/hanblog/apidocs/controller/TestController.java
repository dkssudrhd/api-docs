package com.hanblog.apidocs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import com.hanblog.apidocs.TestDto.TestDto;

@Slf4j
@RestController
public class TestController {

	@GetMapping("/test/{id}")
	public TestDto getTest(@PathVariable Long id){
		return new TestDto(id, "Test Title " + id, "Test Content " + id);
	}

	@PostMapping("/test")
	public TestDto postTest(@RequestBody TestDto request){
		return request;
	}
}