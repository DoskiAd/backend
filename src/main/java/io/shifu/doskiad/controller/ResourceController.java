package io.shifu.doskiad.controller;

import javax.servlet.ServletContext;

import io.shifu.doskiad.model.Item;
import io.shifu.doskiad.model.User;
import io.shifu.doskiad.repository.TokenRepository;
import io.shifu.doskiad.services.ItemService;
import io.shifu.doskiad.validators.ItemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ResourceController {
	
	private final ServletContext servletContext;
    private final TokenRepository tokenRepository;
    private final ItemValidator itemValidator;
    private final ItemService itemService;

	@Autowired
	public ResourceController(ServletContext servletContext, TokenRepository tokenRepository, ItemValidator itemValidator, ItemService itemService) {
		this.servletContext = servletContext;
        this.tokenRepository = tokenRepository;
        this.itemValidator = itemValidator;
        this.itemService = itemService;
    }

	@RequestMapping(value = "items/{id}/photo/{file}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<Resource> getImageAsResource(
			@PathVariable("id") Long id,
			@PathVariable("file") String file) {
	    Resource resource =
	        new ServletContextResource(servletContext,
	        		String.format("/WEB-INF/photos/%s/%s.jpg", id, file));
	    return new ResponseEntity<>(resource, HttpStatus.OK);
	}

    @RequestMapping(value = "/additem", method = RequestMethod.POST)
    ResponseEntity<String> addItem(@RequestBody Item item, @RequestParam("token") String token) {
        String answer = itemValidator.validate(item);

        if (answer != null) {
            return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
        } else {
            User user = tokenRepository.findOneByValue(token).get().getUser();
            item.setName(user.getName());
            item.setUserId(user.getId());
            item.setDate(new Date());
            itemService.save(item);
            return ResponseEntity.ok("" + item.getId());
        }
    }

    @RequestMapping(value = "/addphoto/{itemId}", method = RequestMethod.POST)
    ResponseEntity<String> addItem(@RequestParam("files") MultipartFile[] uploaded,
                                   @PathVariable("itemId") Long itemId,
                                   @RequestParam("token") String token) {
        if (uploaded.length == 0) {
            return new ResponseEntity<>("Please select file", HttpStatus.BAD_REQUEST);
        }

        Optional<Item> optionalItem = itemService.findById(itemId);
        if (optionalItem.isPresent()) {
            User user = tokenRepository.findOneByValue(token).get().getUser();
            Item item = optionalItem.get();
            if (!item.getUserId().equals(user.getId())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            try {
                Integer photo = saveUploadedFiles(Arrays.asList(uploaded), itemId);
                item.setPhotos(photo);
                itemService.update(item);
                return ResponseEntity.ok(photo + "");
            } catch (IOException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

	//save file
	private Integer saveUploadedFiles(List<MultipartFile> files, Long id) throws IOException {
	    int a = 0;
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                a++;
                String temp = this.getClass().getClassLoader().getResource("").getPath();
                String pathArr[] = temp.split("/classes/");
                byte[] bytes = file.getBytes();
                Path path = Paths.get(pathArr[0] + "/photos/" + id);
                Files.createDirectories(path);
                Path filePath = Paths.get(path + "/" + a + ".jpg");
                Path write = Files.write(filePath, bytes);
            }
        }
        return a > 0 ? a : null;
	}

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(500000);
        return multipartResolver;
    }
}
