package com.example.exp1;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
class Exp1Controller {
	
	int correctCount = 0;

    @GetMapping("/")
    public String welcome() {
        return "Hello and welcome to my experiment";
    }

    @GetMapping("/{name}")
    public String welcome(@PathVariable String name) {
        return "Hello and welcome to my experiment: " + name;
    }
    
	@GetMapping("/greet")
	public String getTest(
			@RequestParam(value = "greeting", defaultValue = "Hello") String greeting,
			@RequestParam(value = "username", required = true) String message
	) {
		return String.format("%s %s! This request has a default and required value!", greeting, message);
	}
	
	@PostMapping("/postTest")
	public String postTest(@RequestParam(value = "num", required = true) int num) {
		int randomNum = ThreadLocalRandom.current().nextInt(1, 6);
		String pre;
		if(num == randomNum) {
			++correctCount;
			pre = "Correct!\n";
		}
		else {
			pre = String.format(
				"Incorrect! The correct number was %d.\n", randomNum
			);
		}
		return pre + String.format(
			"Your guess was %d. You have guessed %d number(s) correctly this session.", num, correctCount
		);
	}
}
