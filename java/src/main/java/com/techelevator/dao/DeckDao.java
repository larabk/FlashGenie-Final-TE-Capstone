package com.techelevator.dao;

import com.techelevator.model.Deck;

import java.security.Principal;
import java.util.List;

public interface DeckDao {

    List<Deck> getAllDecks(String userName);

    Deck getDeck(String userName, Long deckId);



}
