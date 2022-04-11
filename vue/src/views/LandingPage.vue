<template>
  <div class="landingPage">
    <h1>My Decks</h1>
    <div class="decks">
      <router-link class="deck"
        :to="{ path: '#' }"
        v-for="deck in this.$store.state.decks"
        v-bind:key="deck.deckId"
        >{{ deck.name }}</router-link
      >
    </div>
  </div>
</template>

<script>
import deckService from "@/services/DeckService";
export default {
  name: "landingPage",
  data() {
    return {
      deck: {
        deckId: 0,
        userId: 0,
        deckName: "",
        clickCount: 0,
      },
    };
  },
  methods: {
    getDecks() {
      deckService.getAllDecks().then((response) => {
        this.$store.commit("SET_DECKS", response.data);
      });
    },
  },
  created() {
    this.getDecks();
  },
};
</script>

<style scoped>
.landingPage{
  display: flex;
  flex-direction: column;
  align-items: center;
}
.decks{
  display: flex;
  width: 1000px;
  justify-content: center;
}
.deck{
  display: flex;
  width: 25%;
  padding: 40px;
  margin: 10px;
  background-color: blueviolet;
  border-radius: 10px;
  justify-content: center;
}
</style>