<template>
  <div class ="create-deck">
      <form class="deck-form"></form>
  </div>
</template>

<script>
import deckService from "@/services/DeckService";

export default {
  name: "createNewDeck",
  data() {
    return {
      deck: {
        deckName: "",
        clickCount: 0,
        topics: ""
      },
    };
  },
  methods: {
      saveDeck() {
          deckService.create(this.deck).then(response => {
              if (response.status === 201) {
                  this.$router.push('/');
              }
          });
      }
  }
};
</script>

<style>
</style>