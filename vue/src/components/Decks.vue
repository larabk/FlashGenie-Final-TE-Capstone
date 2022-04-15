<template>
  <div class="decksPage">
    <div class="deck-container">
          <div class="my-decks">
            <img id="bolt" src="/bolt.png" alt="" />
            <h3>My Decks</h3>
            <img id="bolt" src="/bolt.png" alt="" />
          </div>
      <div class="decks">
        <div
          class="deck"
          v-for="deck in decks.slice(0, displayLength)"
          v-bind:key="deck.deckId"
        >

        <div id="flex-text">
          
          <router-link
            class="deck-name"
            :to="{ name: 'cards', params: { id: deck.deckId } }"
            >{{ deck.name }}
          </router-link>

          <router-link class="edit-deck"
            :to="{ name: 'edit-deck', params: { id: deck.deckId } }"
            >Edit</router-link
          >
        </div>
        </div>

        <router-link class="addDeck addDeckDetails" :to="{ name: 'new-deck' }"
          >+</router-link
        >
      </div>
      
      
          <div id="button">
            <button id="show-all" @click="partialDisplay = !partialDisplay" v-if="decksAvailable > minDisplayLength">
              {{ partialDisplay ? "Show All" : "Show Less" }}
            </button>
        </div>
  </div>
  </div>
</template>

<script>
import deckService from "@/services/DeckService";
export default {
  name: "decksPage",
  data() {
    return {
      minDisplayLength: 7,
      partialDisplay: true,
      deck: {
        deckId: 0,
        userId: 0,
        deckName: "",
        clickCount: 0,
        subject: "",
      },
    };
  },
  computed: {
    displayLength() {
      if (this.partialDisplay) {
        return 7;
      } else {
        return this.$store.state.decks.length;
      }
    },
    decks() {
      return this.$store.state.decks;
    },
    decksAvailable(){
      return this.$store.state.decks.length;
    }
  },
  methods: {
    getDecks() {
      deckService.getAllDecks().then((response) => {
        this.$store.commit("SET_DECKS", response.data);
      });
    },
  },
  created() {
    this.getDecks();
  },
};
</script>

<style scoped>
.decksPage {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0;
  margin: 0;
  width: 100%;
  min-height: 100vh;
}

.deck-container {
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

.decks {
  display: flex;
  flex-wrap: wrap;
  margin: 30px 0px 30px 0px;
  justify-content: center;
}

.deck,
.addDeck {
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
  text-transform: uppercase;
}

.deck {
  /* width: [object Object]px;
  height: [object Object]px; */
  background-color: #f8f9d2;
  background-image: linear-gradient(315deg, #f8f9d2 0%, #e8dbfc 74%);
  border: solid #bdbdbd 1px;
  box-shadow: 5px 5px 18px rgba(0, 0, 0, .93);
  -webkit-box-shadow: 5px 5px 18px rgba(0, 0, 0, .93);
  -moz-box-shadow: 5px 5px 18px rgba(0, 0, 0, .93);
  font-size: x-large;
  letter-spacing: 1.75px;
  font-weight: bold;
  

}

.addDeckDetails {
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


.deck:hover {

}

.addDeck {
  /* width: [object Object]px;
  height: [object Object]px; */
  background-color: white;
  border: solid #bdbdbd 1px;
  box-shadow: 5px 5px 18px rgba(0, 0, 0, .93);
  -webkit-box-shadow: 5px 5px 18px rgba(0, 0, 0, .93);
  -moz-box-shadow: 5px 5px 18px rgba(0, 0, 0, .93);
}

img {
  width: 30px;
  opacity: 0.5;
}

div.my-decks {
  display: flex;
  text-align: center;
  justify-content: center;
  align-items: center;
  color: white;
  vertical-align: middle;
  margin-top: 10px;
}

button#show-all {
  font-family: monospace;
  letter-spacing: 1px;
  width: 100px;
  height: 40px;
  border-radius: 10px;
  margin-right: 15px;
}
div#button {
  display: flex;
  justify-content: right;
  margin-bottom: 10px;
}

a.deck-name, a {
  text-decoration: none;
  color: black;
  
}

a.deck-name:visited, a:visited {
   color: black;

}
a.deck-name {
  padding-top: 10px;
}

a.edit-deck {
  font-size: 10px;
  text-align: right;
  padding-top: 10px;
}

div#flex-text {
  display: flex;
  flex-direction: column;
  width: 100%;
  line-height: 20px;
}




</style>