package com.techelevator.dao;

import com.techelevator.model.Deck;

import java.security.Principal;
import java.util.List;

public interface DeckDao {

    List<Deck> getAllDecks(Long userId);

    Deck getDeck(Long userId, Long deckId);



}
