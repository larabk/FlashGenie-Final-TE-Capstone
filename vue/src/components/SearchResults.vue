<template>
  <div class="searchResultsPage">{{ filteredDecks }}</div>
</template>

<script>
export default {
  data() {
    return {
      decks: this.$store.state.decks,
      cards: this.$store.state.cards,
    };
  },
  computed: {
    filteredDecks() {
      return this.decks.filter((deck) => {
        return deck.subject.toLowerCase().includes(this.filterTerm);
      });
    },
    filterTerm() {
      return this.$route.params.searchInput.toLowerCase();
    },
  },
  methods: {},
};
</script>

<style>
</style>