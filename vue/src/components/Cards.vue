<template>
  <div class="cardsPage">
    <router-link :to="{name: 'home'}">Back to Decks</router-link>
    <router-link :to="{name: 'edit-deck', params: {id: this.$route.params.id}}">Edit</router-link>
    <div class="cards-container">
      <div class="my-cards">
        <img id="bolt" src="/bolt.png" alt="">
        <h3>My Cards</h3>
        <img id="bolt" src="/bolt.png" alt="">
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
      <div id="button">
      <button id="show-all" v-if="deckSize > minDisplayLength" @click="partialDisplay = !partialDisplay ">{{partialDisplay ? "Show All" : "Show Less"}}</button>
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
      return this.$store.state.cards;
    },
  },
  methods: {
    getCards() {
      cardService.getAllCards(this.$route.params.id).then((response) => {
        this.$store.commit("SET_CARDS", response.data);
      });
    },
    // --- Still trying to figure this thing out
    // sortCards(){
    //   this.cards.sort((a, b) => {
    //     let nameA = a.frontText.toUpperCase();
    //     let nameB = b.frontText.toUpperCase();
    //     return (nameA < nameB) ? -1 : (nameA > nameB) ? 1 : 0;
    //   })
    // }
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
  padding: 0;
  margin: 0;
  width: 100%;
  min-height: 100vh;
}

.cards-container {
  background-color: #537895;
  background-image: linear-gradient(315deg, 
  #537895 0%, #09203f 74%);
  justify-content: center;
  min-width: 600px;
  border-radius: 30px;
  margin: 30px 0px 0px 0px;
  padding: 30px;
  padding-top: 40px;
  width: 75%;
  max-width: 1200px;
}

.cards {
  display: flex;
  flex-wrap: wrap;
  margin: 30px 45px 30px 45px;
  justify-content: center;
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
  color: rgb(95, 95, 95);
}

h3 {
  color: white;
  font-size: 45px;
  text-align: center;
  text-transform: uppercase;
  letter-spacing: 1.75px;
  margin: 15px;
}

div.card {
  width: [object Object]px; 
	height: [object Object]px; 
  background-color: #f8f9d2;
  background-image: linear-gradient(315deg, #f8f9d2 0%, #e8dbfc 74%);
  border: solid #BDBDBD 1px; 
	box-shadow: 5px 5px 18px rgba(0, 0, 0, 1.93); 
	-webkit-box-shadow: 5px 5px 18px rgba(0, 0, 0, 1.93); 
	-moz-box-shadow: 5px 5px 18px rgba(0, 0, 0, 1.93); 
  font-size: x-large;
  letter-spacing: 1.75px;
  font-weight: bold;
}
.addCard {
  width: [object Object]px; 
	height: [object Object]px; 
	background-color: white;
  border: solid #BDBDBD 1px; 
	box-shadow: 5px 5px 18px rgba(0, 0, 0, 1.93); 
	-webkit-box-shadow: 5px 5px 18px rgba(0, 0, 0, 1.93); 
	-moz-box-shadow: 5px 5px 18px rgba(0, 0, 0, 1.93); 
}

img {
  width: 30px;
  opacity: 0.5;
}

div.my-cards {
    display: flex;
    text-align: center;
    justify-content: center;
    align-items: center;
    color: white;
    vertical-align: middle;
    margin-top:10px;
}

button#show-all {
  font-family: monospace;
  letter-spacing: 1px;
  width: 100px;
  height: 40px;
  border-radius: 10px;
  margin-right: 60px;
}
div#button {
  display: flex;
  justify-content: right;
  margin-bottom: 10px;
  
}



</style>