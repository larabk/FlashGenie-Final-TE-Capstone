<template>
  <div class="editDeckPage">
    <form @submit.prevent="updateDeck" class="update-deck-form">
      <input
        type="text"
        class="name"
        placeholder="New deck name"
        v-model="deck.name"
      />
      <input
        type="text"
        class="topics"
        placeholder="New topics"
        v-model="deck.topics"
      />
      <button type="submit">Submit</button>
      <button type="cancel" @click.prevent="cancelUpdate">Cancel</button>
    </form>
  </div>
</template>

<script>
import deckService from "@/services/DeckService";
export default {
  data() {
    return {
      deck: {
        deckId: Number(this.$route.params.id),
        name: "",
        topics: "",
        clickCount: 0,
      },
    };
  },
  computed: {},
  methods: {
    updateDeck() {
      deckService.update(this.deck).then((response) => {
        if (response.status === 200) {
          this.$router.push("/");
        }
      });
    },
  },
};
</script>

<style>
</style>