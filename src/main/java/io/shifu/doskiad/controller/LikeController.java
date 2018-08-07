package io.shifu.doskiad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.shifu.doskiad.model.Item;
import io.shifu.doskiad.model.Like;
import io.shifu.doskiad.model.User;
import io.shifu.doskiad.repository.TokenRepository;
import io.shifu.doskiad.services.LikeService;

@RestController
public class LikeController {
	
	private final LikeService likeService;
	private final TokenRepository tokenRepository;
	
	@Autowired
    public LikeController(LikeService likeService, TokenRepository tokenRepository) {
        this.likeService = likeService;
        this.tokenRepository = tokenRepository;
    }
	
	@RequestMapping(value="/likes", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> getLikes(
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "12") Integer size,
            @RequestParam(value = "d", required = false, defaultValue = "desc") String direction,
            @RequestParam(value = "p", required = false, defaultValue = "date") String param,
			@RequestParam("token") String token){
		Long id = tokenRepository.findOneByValue(token).get().getUser().getId();
		List<Like> likes = likeService.findLikesByUser(id);
		Page<Item> result = likeService.findItemsByLikes(likes, page, size, direction, param);
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("totalFound", Long.toString(result.getTotalElements()));
        responseHeaders.set("totalPage", Integer.toString(result.getTotalPages()));
        return new ResponseEntity<>(result.getContent(), responseHeaders, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/likes/ids", method = RequestMethod.GET)
	public ResponseEntity<List<Long>> getListLikes(@RequestParam("token") String token){
		Long id = tokenRepository.findOneByValue(token).get().getUser().getId();
		return new ResponseEntity<>(likeService.findItemsIdByUser(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/items/{id}/like", method = RequestMethod.GET)
	public ResponseEntity<List<Long>> like(
			@PathVariable(name="id") Long itemid,
			@RequestParam("token") String token){
		
		User user = tokenRepository.findOneByValue(token).get().getUser();
		List<Long> ids = likeService.findItemsIdByUser(user.getId());
		if (!ids.contains(itemid)) {
			Like like = new Like();
			like.setItem(itemid);
			like.setUser(user.getId());
			likeService.add(like);
		} else {
			likeService.deleteByUserAndItem(user.getId(),itemid);
		}
		
		ids = likeService.findItemsIdByUser(user.getId());
		return new ResponseEntity<>(ids, HttpStatus.OK);
	}

}
