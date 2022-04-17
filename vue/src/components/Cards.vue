<template>
  <div class="cardsPage">
    
    
    <div class="cards-container">

      <div id="title-links">
        
      <div class="my-cards">
        <img id="bolt" src="/bolt.png" alt="">
        <h3>cards about {{currentDeckName}}</h3>
        <img id="bolt" src="/bolt.png" alt="">
      </div>
        <router-link class="back-to-decks" :to="{name: 'home'}">Back to Decks</router-link>
        <router-link class="edit-deck" :to="{name: 'edit-deck', params: {id: this.$route.params.id}}">Edit</router-link>
    </div>
      
      
      <div class="cards">
        <div
          class="card"
          v-for="card in cards.slice(0, displayLength)"
  
          v-bind:key="card.cardId"
        >
        <div id="flex-text">
          {{ card.frontText }}
          <router-link class="edit-card" :to="{name: 'edit-card', params: {deckId: card.deckId, cardId: card.cardId}}">Details/Edit</router-link>
            
        </div>

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
      currentDeck: this.$store.state.decks,
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
    currentDeckId(){
      return Number(this.$route.params.id);
    },
    currentDeckName(){
      return this.currentDeck.find(deck => 
        deck.deckId === this.currentDeckId
      ).name;
    }
  },
  methods: {
    getCards() {
      cardService.getAllCardsByDeckId(this.$route.params.id).then((response) => {
        this.$store.commit("SET_CARDS_BY_DECK_ID", response.data);
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
  max-width: 1080px;
  border-radius: 30px;
  margin: 30px 0px 0px 0px;
  padding: 30px;
  padding-top: 40px;
  width: 75%;

}

.cards {
  display: flex;
  flex-wrap: wrap;
  margin: 30px 0px 30px 0px;
  justify-content: center;
}

.card,
.addCard {
  display: flex;
  flex-wrap: wrap;
  width: 150px;
  height: 50px;
  align-items: center;
  padding: 15px;
  margin: 15px;
  margin-left: 20px;
  border: none;
  color: rgb(0, 0, 0);
  text-align: center;
  text-decoration: none;
  border-radius: 10px;
  justify-content: center;
  font-size: larger;
  /* text-transform: uppercase; */
}

div.card {
  /* width: [object Object]px; 
	height: [object Object]px;  */
  background-color: #f8f9d2;
  background-image: linear-gradient(315deg, #f8f9d2 0%, #e8dbfc 74%);
  border: solid #BDBDBD 1px; 
	box-shadow: 5px 5px 18px rgba(0, 0, 0, .93); 
	-webkit-box-shadow: 5px 5px 18px rgba(0, 0, 0, .93); 
	-moz-box-shadow: 5px 5px 18px rgba(0, 0, 0, .93); 
  font-size: x-large;
  letter-spacing: 1.75px;
  font-weight: bold;
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


.addCard {
  /* width: [object Object]px; 
	height: [object Object]px;  */
	background-color: white;
  border: solid #BDBDBD 1px; 
	box-shadow: 5px 5px 18px rgba(0, 0, 0, .93); 
	-webkit-box-shadow: 5px 5px 18px rgba(0, 0, 0, .93); 
	-moz-box-shadow: 5px 5px 18px rgba(0, 0, 0, .93); 
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


a.card-name, a {
  text-decoration: none;
  color: black;
  
}

a.card-name:visited, a:visited {
   color: black;

}
a.card-name {
  padding-top: 10px;
  
}

a.edit-card {
  font-size: 10px;
  text-align: right;
  padding-top: 0px;
}

div#flex-text {
  display: flex;
  flex-direction: column;
  width: 100%;
  line-height: 20px;
}

a.back-to-decks, a.edit-deck {
  text-align: center;
}

div#title-links {
  display: flex;
  flex-direction: column;
}

a.back-to-decks, a.edit-deck {
  color: #ebeb85;
  text-transform: uppercase;
  letter-spacing: 1px;
}

a.back-to-decks:hover,
a.edit-deck:hover {
  color: yellow;
}

</style>