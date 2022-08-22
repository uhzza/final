package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//화면 띄우는 컨트롤러


@Controller
public class ViewController {
	
		@GetMapping({"/","/board/list"})
		public String list() {
			return "board/list";
		}
		
		@GetMapping("/board/write")
		public void write() {
			
		}
		@GetMapping("board/read")
		public void read() {
			
		}
}
