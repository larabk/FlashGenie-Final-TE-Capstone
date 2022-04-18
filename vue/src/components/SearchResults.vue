<template>
  <div class="searchResultsPage">
    <div class="decks-container">
      <h1>Deck Results</h1>
      <div class="no-decks" v-if="filteredDecks.length === 0">
        No deck subjects match this search
      </div>
      <div class="decks" v-for="deck in filteredDecks" v-bind:key="deck.id">
        <div class="deck-link">
          <router-link :to="{ name: 'cards', params: { id: deck.deckId } }">
            Deck Name: {{ deck.name }} | Subject: {{ deck.subject }}
          </router-link>
        </div>
      </div>
    </div>
    <h1>Card Results</h1>
    <div class="no-cards" v-if="filteredCards.length === 0">
      No card keywords match this search
    </div>

    <div class="cards-container">
      <div class="cards" v-for="card in filteredCards" v-bind:key="card.id">
        <div class="card-link">
          <router-link :to="{ name: 'cards', params: { id: card.deckId } }">
            Deck Name: {{ deckName(card.deckId) }} | Term:
            {{ card.frontText }} | Definition: {{ card.backText }} | Keywords:
            {{ card.keyWords.split(" ").join(", ") }}</router-link
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      decks: this.$store.state.decks,
      cards: this.$store.state.allCards,
    };
  },
  computed: {
    filteredDecks() {
      return this.decks.filter((deck) => {
        return (
          deck.subject.toLowerCase().includes(this.filterTerm) ||
          deck.name.toLowerCase().includes(this.filterTerm)
        );
      });
    },
    filterTerm() {
      return this.$route.params.searchInput.toLowerCase();
    },
    filteredCards() {
      return this.cards.filter((card) => {
        return (
          card.keyWords.toLowerCase().includes(this.filterTerm) ||
          card.frontText.toLowerCase().includes(this.filterTerm)
        );
      });
    },
  },
  methods: {
    deckName(id) {
      return this.decks.find((deck) => deck.deckId === id).name;
    },
  },
};
</script>

<style scoped>
a {
  color: black;
}
</style>