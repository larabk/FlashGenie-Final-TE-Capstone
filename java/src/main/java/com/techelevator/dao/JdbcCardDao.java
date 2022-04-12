package com.techelevator.dao;

import com.techelevator.model.Card;
import com.techelevator.model.Deck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCardDao implements CardDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Card> getAllCards(Long deckId, String username) {
        List<Card> cards = new ArrayList<>();

        String sql = "SELECT card_id, cards.deck_id, front_text, back_text, keywords " +
                "FROM cards JOIN decks ON decks.deck_id = cards.deck_id " +
                "JOIN users ON users.user_id = decks.user_id " +
                "WHERE username = ? AND decks.deck_id = ?; ";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username, deckId);
        while (results.next()) {
            cards.add(mapRowToCard(results));
        }

        return cards;
    }

    private Card mapRowToCard(SqlRowSet rowSet) {
        Card card = new Card();
        card.setCardId(rowSet.getLong("card_id"));
        card.setDeckId(rowSet.getLong("deck_id"));
        card.setFrontText(rowSet.getString("front_text"));
        card.setBackText(rowSet.getString("back_text"));
        card.setKeyWords(rowSet.getString("keywords"));
        return card;
    }
}
