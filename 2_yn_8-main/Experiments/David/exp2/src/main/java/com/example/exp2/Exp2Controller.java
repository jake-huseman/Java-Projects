package com.example.exp2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Exp2Controller {
	
	private List<ComplexNumberData> numbers = new ArrayList<ComplexNumberData>();
	
	@GetMapping("/getList")
	public String getData(@RequestParam(value = "username", defaultValue = "World") String message) {
		String ret = "";
		for(ComplexNumberData number : numbers) 
			ret += number.toString() + "\n";
		
		return String.format("Hello, %s! Your list of numbers:\n%s", message, ret);
	}
	
	@GetMapping("/{index}")
	public String readData(@PathVariable int index) {
		return numbers.get(index).toString();
	}
	
	@PostMapping("/postNumber")
	public String postTest1(@RequestBody ComplexNumberData newData) {
		numbers.add(newData);
		return String.format("You have added a new complex number. It is %s.", newData.toString());
	}
	
	@DeleteMapping("/deleteNumber/{index}")
	public void deleteTest(@PathVariable int index) {
		numbers.remove(index);
	}
	
	@PutMapping("/putNumber/{index}")
	public void putTest(@PathVariable int index, @RequestBody ComplexNumberData data) {
		numbers.set(index, data);
	}
}
