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
      <input type="text" id="card-keywords" placeholder="Keywords (separate with commas)" v-model="card.keywords">
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
      card: {
        frontText: "",
        backText: "",
        keywords: "",
      },
    };
  },
  methods: {
    saveCard() {
      cardService.create(Number(this.$route.param.id), this.card).then((response) => {
        if (response.status === 201) {
          this.$router.push("/");
        }
      });
    },
    cancelForm() {
      this.card.frontText = "";
      this.card.backText = "";
      this.card.keywords = "";
      this.$router.push("/");
    },
  },
};
</script>

<style>
</style>