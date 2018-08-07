package io.shifu.doskiad.controller;

import java.util.List;
import java.util.Optional;

import io.shifu.doskiad.services.ItemService;
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
	private final ItemService itemService;
	
	@Autowired
    public LikeController(LikeService likeService, TokenRepository tokenRepository, ItemService itemService) {
        this.likeService = likeService;
        this.tokenRepository = tokenRepository;
		this.itemService = itemService;
	}
	
	@RequestMapping(value="/likes", method = RequestMethod.POST)
	public ResponseEntity<List<Item>> getLikes(
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "12") Integer size,
            @RequestParam(value = "d", required = false, defaultValue = "desc") String direction,
            @RequestParam(value = "p", required = false, defaultValue = "date") String param,
			@RequestParam("token") String token){
		User user = tokenRepository.findOneByValue(token).get().getUser();
		Page<Item> result = itemService.findItemsByIds(likeService.findItemsIdByUser(user.getId()), page, size, direction, param);
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("totalFound", Long.toString(result.getTotalElements()));
        responseHeaders.set("totalPage", Integer.toString(result.getTotalPages()));
        return new ResponseEntity<>(result.getContent(), responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = {"/like", "/like/{itemId}"}, method = RequestMethod.POST)
	public ResponseEntity<List<Long>> like(@RequestParam("token") String token,
										   @PathVariable("itemId") Optional<Long> itemId) {
		User user = tokenRepository.findOneByValue(token).get().getUser();

		if (itemId.isPresent()) {
			Optional<Like> optionalLike = likeService.findLikeByUserAndItem(user.getId(), itemId.get());
			if (optionalLike.isPresent()) {
                likeService.deleteByUserAndItem(user.getId(), itemId.get());
			} else {
			    likeService.add(new Like(itemId.get(), user.getId()));
			}
		}
		return new ResponseEntity<>(likeService.findItemsIdByUser(user.getId()), HttpStatus.OK);
	}
}
