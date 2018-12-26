package edu.ftn.isa.controllers.app;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {

	@RequestMapping("/")
	String home(ModelMap modal) {
		System.out.println("Upaooo");
		modal.addAttribute("title","Flight Application");
		return "index";
	}

	@RequestMapping(value="/partials/{page}")
	String partialHandler(@PathVariable("page") final String page) {
		System.out.println("trazi ovde "+page);
		
		return page;
	}
	
	
	 @RequestMapping(value= "/images/{image}", method = RequestMethod.GET,
	            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
	    public ResponseEntity<InputStreamResource> getImage(@PathVariable("image") final String image) throws IOException {
	        ClassPathResource imgFile = new ClassPathResource("images/"+image + ".png");
	        return ResponseEntity
	                .ok()
	               // .contentType(MediaType.IMAGE_PNG, MediaType.IMAGE_JPEG)
	                .body(new InputStreamResource(imgFile.getInputStream()));
	    }

}
