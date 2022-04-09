package com.techelevator.controller;

import com.techelevator.dao.DeckDao;
import com.techelevator.model.Deck;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class DeckController {
    private DeckDao deckDao;

    public DeckController(DeckDao deckdao){
        this.deckDao = deckdao;
    }

    @RequestMapping(value = "user/{userId}/decks", method = RequestMethod.GET)
    public List<Deck> getDecksByUser(@PathVariable Long userId){
        return deckDao.getAllDecks(userId);
    }

    @RequestMapping(value = "user/{userId}/decks/{deckId}", method = RequestMethod.GET)
    public Deck getDeckById(@PathVariable Long userId, @PathVariable Long deckId) {
        return deckDao.getDeck(userId, deckId);
    }

}
