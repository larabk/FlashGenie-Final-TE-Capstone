<template>
  <div class="cardsPage">
    <router-link :to="{name: 'home'}">Back to Decks</router-link>
    <router-link :to="{name: 'edit-deck', params: {id: this.$route.params.id}}">Edit current deck properties</router-link>
    <div class="cards-container">
      <div class="my-cards">
        <h3>My Cards</h3>
      </div>
      <div class="cards">
        <div
          class="card"
          v-for="card in cards.slice(0, displayLength)"
          v-bind:key="card.cardId"
        >
        <router-link :to="{name: 'edit-card', params: {deckId: card.deckId, cardId: card.cardId}}">Edit this Card</router-link>
          {{ card.frontText }}
        </div>
        <router-link :to="{name: 'new-card', params: {id: this.$route.params.id}}" class="addCard addCardDetails">+</router-link>
      </div>
      <button v-if="deckSize > minDisplayLength" @click="partialDisplay = !partialDisplay ">{{partialDisplay ? "Show All" : "Show Less"}}</button>
    </div>
  </div>
</template>

<script>
import cardService from "@/services/CardService";
export default {
  name: "cardsPage",
  data() {
    return {
      minDisplayLength: 11,
      partialDisplay: true,
      deckId: 0,
      active: false,

    };
  },
  computed: {
    deckSize(){
      return this.$store.state.cards.length;
    },
    displayLength() {
      if (this.partialDisplay) {
        return 11;
      } else {
        return this.$store.state.cards.length;
      }
    },
    cards(){
      return this.$store.state.cards
    }
  },
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