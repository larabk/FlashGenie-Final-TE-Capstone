<template>
  <div class="create-deck">
    <form @submit.prevent="saveDeck" class="deck-form">
      <input
        type="text"
        id="deck-name"
        placeholder="Deck Name"
        v-model="deck.name"
      />
      <input
        type="text"
        id="topics"
        placeholder="Deck Topics"
        v-model="deck.topics"
      />
      <div class="alert" v-if="creationErrors">{{creationErrorMessage}}</div>
      <button type="submit">Submit</button>
      <button type="cancel" @click.prevent="cancelForm">Cancel</button>
    </form>
  </div>
</template>

<script>
import deckService from "@/services/DeckService";

export default {
  name: "newDeckForm",
  data() {
    return {
      deck: {
        name: "",
        clickCount: 0,
        topics: "",
      },
      creationErrors: false,
      creationErrorMessage: "There was a problem creating this deck",
    };
  },
  methods: {
    saveDeck() {
      if (this.deck.name === "" || this.deck.topics === "") {
        this.creationErrors = true;
        this.creationErrorMessage = "All Fields Required";
      } else {
        deckService.create(this.deck).then((response) => {
          if (response.status === 201) {
            this.$router.push("/");
          }
        });
      }
    },
    cancelForm() {
      this.deck.name = "";
      this.deck.topics = "";
      this.$router.push("/");
    },
  },
};
</script>

<style>
</style>