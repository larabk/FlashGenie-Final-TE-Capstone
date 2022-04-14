package com.techelevator.dao;

import com.techelevator.model.Card;
import java.util.List;

public interface CardDao {
    List<Card> getAllCards(Long deckId, String username);

    Card createCard(Long deckId, Card card);

    void updateCard(Card card);
}
