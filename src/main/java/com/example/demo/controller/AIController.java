package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.AIQueryService;

@RestController
@RequestMapping("/api/ai")
public class AIController {

	
	private final AIQueryService aiService;
	
	public AIController(AIQueryService aiService) {
		this.aiService = aiService;
	}
	
	@PostMapping("/ask")
	public ResponseEntity<String> ask(@RequestBody String question){
		return ResponseEntity.ok(aiService.process(question));
	}
}
