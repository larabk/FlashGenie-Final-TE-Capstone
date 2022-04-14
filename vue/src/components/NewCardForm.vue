<template>
  <div class="create-card">
    <form @submit.prevent="saveCard" class="card-form">
      <input
        type="text"
        id="card-front-text"
        placeholder="Term"
        v-model="card.frontText"
      />
      <input
        type="text"
        id="card-back-text"
        placeholder="Definition"
        v-model="card.backText"
      />
      <input
        type="text"
        id="card-keywords"
        placeholder="Keywords (separate with commas)"
        v-model="card.keyWords"
      />
      <div class="alert" v-if="creationErrors">{{ creationErrorMessage }}</div>
      <button type="submit">Submit</button>
      <button type="cancel" @click.prevent="cancelForm">Cancel</button>
    </form>
  </div>
</template>

<script>
import cardService from "@/services/CardService";

export default {
  name: "newCardForm",
  data() {
    return {
      creationErrors: false,
      creationErrorMessage: "There was a problem creating this card",
      card: {
        frontText: "",
        backText: "",
        keyWords: "",
      },
      id: this.$route.params.id,
    };
  },
  methods: {
    saveCard() {
      if (this.card.frontText === "" || this.card.backText === "" || this.card.keyWords === "") {
        this.creationErrors = true;
        this.creationErrorMessage = "All Fields Required";
      } else {
        cardService.create(this.id, this.card).then((response) => {
          if (response.status === 201) {
            this.$router.push(`/deck/${this.id}/cards`);
          }
        });
      }
    },
    cancelForm() {
      this.card.frontText = "";
      this.card.backText = "";
      this.card.keyWords = "";
      this.$router.push(`/deck/${this.id}/cards`);
    },
  },
};
</script>

<style>
</style>