package io.shifu.doskiad.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.ServletContextResource;

@RestController
public class PhotoController {
	
	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = "items/{id}/photo/{file:.+}", method = RequestMethod.GET)
	public ResponseEntity<Resource> getImageAsResource(
			@PathVariable("id") Long id,
			@PathVariable("file") String file) {	    
	    Resource resource = 
	        new ServletContextResource(servletContext, 
	        		String.format("/WEB-INF/photos/%s/%s", id, file));
	    return new ResponseEntity<>(resource, HttpStatus.OK);
	}

}
