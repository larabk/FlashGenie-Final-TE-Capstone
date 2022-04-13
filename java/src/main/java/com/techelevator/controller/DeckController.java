package com.techelevator.controller;

import com.techelevator.dao.DeckDao;
import com.techelevator.model.Deck;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @RequestMapping(value = "/decks", method = RequestMethod.GET)
    public List<Deck> getDecksByUser(Principal userName){
        return deckDao.getAllDecks(userName.getName());
    }

    //Maybe needed later?
    @RequestMapping(value = "/decks/{deckId}", method = RequestMethod.GET)
    public Deck getDeckById(Principal userName, @PathVariable Long deckId) {
        return deckDao.getDeck(userName.getName(), deckId);
    }

    @RequestMapping(value = "/decks", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Deck postDeck(@RequestBody Deck newDeck, Principal userName){
        return deckDao.createDeck(userName.getName(), newDeck);
    }

}
