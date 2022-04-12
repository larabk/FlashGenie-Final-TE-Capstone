package com.techelevator.controller;

import com.techelevator.dao.CardDao;
import com.techelevator.model.Card;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")

public class CardController {
    private CardDao cardDao;

    public CardController(CardDao cardDao) { this.cardDao = cardDao; }

    @RequestMapping (value = "/decks/{deckId}/cards", method = RequestMethod.GET)
    public List<Card> getAllCards(@PathVariable Long deckId, Principal userName) {
        return cardDao.getAllCards(deckId, userName.getName());
    }
}
