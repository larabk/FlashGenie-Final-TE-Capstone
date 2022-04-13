<template>
  <div class="landingPage">
    <div class="deck-container">
      <div class="my-decks">
        <h3>My Decks</h3>
      </div>
      <div class="decks">
        <router-link
          class="deck"
          v-for="deck in decks.slice(0, displayLength)"
          v-bind:key="deck.deckId"
          :to="{ name: 'cards', params: { id: deck.deckId } }"
          >{{ deck.name }}
        </router-link>
        <router-link class="addDeck addDeckDetails" :to="{ name: 'new-deck' }"
          >+</router-link
        >
      </div>
      <button @click="partialDisplay = !partialDisplay ">{{partialDisplay ? "Show All" : "Show Less"}}</button>
    </div>
  </div>
</template>

<script>
import deckService from "@/services/DeckService";
export default {
  name: "landingPage",
  data() {
    return {
      partialDisplay: true,
      deck: {
        deckId: 0,
        userId: 0,
        deckName: "",
        clickCount: 0,
        topics: "",
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
  },
  methods: {
    getDecks() {
      deckService.getAllDecks().then((response) => {
        this.$store.commit("SET_DECKS", response.data);
        if (response.status === 200) {
          this.$router.push("/");
        }
      });
    },
  },
  created() {
    this.getDecks();
  },
};
</script>

<style scoped>
.landingPage {
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 100vh;
}

.deck-container {
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

.decks {
  display: flex;
  flex-wrap: wrap;
  margin-left: 45px;
}

.deck,
.addDeck {
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

.addDeckDetails {
  font-size: 3em;
  font-weight: 1000;
  color: grey;
}
</style>