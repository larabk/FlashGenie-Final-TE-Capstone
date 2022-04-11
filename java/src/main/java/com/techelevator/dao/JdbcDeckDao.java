package com.techelevator.dao;

import com.techelevator.model.Deck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcDeckDao implements DeckDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcDeckDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Deck> getAllDecks(String userName) {
        List<Deck> decks = new ArrayList<>();

        String sql = "SELECT deck_id, decks.user_id, click_count, deck_name " +
                "FROM decks JOIN users ON users.user_id = decks.user_id " +
                "WHERE username = ?; ";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userName);
        while (results.next()) {
            decks.add(mapRowToDeck(results));
        }

        return decks;
    }

    public Deck getDeck(String userName, Long deckId) {
        Deck resultDeck = null;

        String sql = "SELECT deck_id, decks.user_id, click_count, deck_name " +
                "FROM decks JOIN users ON decks.user_id = users.user_id " +
                "WHERE deck_id = ? AND users.username = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, deckId, userName);

        if (results.next()) {
            resultDeck = mapRowToDeck(results);
        }
        return resultDeck;
    }


    private Deck mapRowToDeck(SqlRowSet rowSet) {
        Deck deck = new Deck();
        deck.setDeckId(rowSet.getLong("deck_id"));
        deck.setUserId(rowSet.getLong("user_id"));
        deck.setClickCount(rowSet.getInt("click_count"));
        deck.setName((rowSet.getString("deck_name")));
        return deck;
    }


}
