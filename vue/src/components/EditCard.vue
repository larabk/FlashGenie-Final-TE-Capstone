<template>
  <div class="editCardPage">
    <form @submit.prevent="updateCard" class="update-card-form">
      <input
        type="text"
        class="front-text"
        placeholder="New term"
        v-model="card.frontText"
      />
      <input
        type="text"
        class="back-text"
        placeholder="New definition"
        v-model="card.backText"
      />
      <input type="text" class="key-words" v-model="card.keyWords" />
      <button type="submit">Submit</button>
      <button type="cancel" @click.prevent="cancelUpdate">Cancel</button>
    </form>
  </div>
</template>

<script>
import cardService from "@/services/CardService";
export default {
  data() {
    return {
      card: {
        cardId: this.$route.params.cardId,
        deckId: this.$route.params.deckId,
        frontText: "",
        backText: "",
        keyWords: "",
      },
    };
  },
  methods: {
    updateCard() {
      cardService.update(this.card).then((response) => {
        if (response.status === 200) {
          this.$router.push(`/deck/${this.card.deckId}/cards`);
        }
      });
    },
  },
};
</script>

<style>
</style>