<template>
  <div class="cardsPage">
    <div class="cards-container">
      <div class="my-cards">
        <h3>My Cards</h3>
      </div>
      <div class="cards">
        <div
          class="card"
          v-for="card in this.$store.state.cards"
          v-bind:key="card.cardId"
        >
          {{ card.frontText }}
        </div>
        <router-link :to="{name: 'new-card', params: {id: this.$route.params.id}}" class="addCard addCardDetails">+</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import cardService from "@/services/CardService";
export default {
  name: "cardsPage",
  data() {
    return {
      deckId: 0,
      active: false,
    };
  },
  computed: {},
  methods: {
    getCards() {
      cardService.getAllCards(this.$route.params.id).then((response) => {
        this.$store.commit("SET_CARDS", response.data);
      });
    },
  },
  created() {
    this.getCards();
  },
};
</script>

<style scoped>
.cardsPage {
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 100vh;
}

.cards-container {
  background-color: cornsilk;
  justify-content: center;
  min-width: 600px;
  border-radius: 30px;
  margin: 30px 0px 200px 0px;
  padding: 30px;
  padding-top: 40px;
  width: 75%;
  max-width: 1200px;
}

.cards {
  display: flex;
  flex-wrap: wrap;
  margin-left: 45px;
}

.card,
.addCard {
  display: flex;
  flex-wrap: wrap;
  width: 200px;
  height: 75px;
  align-items: center;
  padding: 20px;
  margin: 15px;
  margin-left: 20px;
  background-color: pink;
  border: none;
  color: rgb(0, 0, 0);
  text-align: center;
  text-decoration: none;
  border-radius: 10px;
  justify-content: center;
  font-size: larger;
  text-transform: uppercase;
}

.addCardDetails {
  font-size: 3em;
  font-weight: 1000;
  color: grey;
  border-radius: 90;
}
</style>