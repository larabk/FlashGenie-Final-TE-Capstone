<template>
  <div class="editDeckPage">
    <div class="details-container">
      
    </div>
    <div class="form-container">
      <div class="header">
        <img id="bolt" src="/bolt.png" alt="">  
        <h3>Edit Deck</h3>
        <img id="bolt" src="/bolt.png" alt="">
      </div>

      <h4>Deck Name: {{currentDeck.name}}</h4>
      <h4>Deck Subject: {{currentDeck.subject}}</h4>

     <div class="form">   
        <form @submit.prevent="updateDeck" class="update-deck-form">
          <input
            type="text"
            class="name"
            placeholder="New deck name"
            v-model="deck.name"/>
          
          <input
            type="text"
            class="subject"
            placeholder="New subject"
            v-model="deck.subject"/>

          <div class="buttons">
            <button id="delete" @click.prevent="deleteDeck">DELETE DECK</button>
            <button id="cancel" type="cancel" @click.prevent="cancelUpdate">Cancel</button>
            <button id="save" type="submit">Submit</button>
          </div>

        </form>
     </div>
    </div>
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
        subject: "",
        clickCount: 0,
      },
      decks: this.$store.state.decks,
    };
  },
  computed: {
   currentDeckId(){
      return Number(this.$route.params.id);
    },
    currentDeck(){
      return this.decks.find(deck => 
        deck.deckId === this.currentDeckId
      );
    }
  },
  methods: {
    updateDeck() {
      deckService.update(this.deck).then((response) => {
        if (response.status === 200) {
          this.$router.push("/");
        }
      });
    },
    cancelUpdate() {
      this.deck.name = "";
      this.deck.subject = "";
      this.$router.push("/");
    },
    deleteDeck() {
      if (
        confirm(
          "Do you really want to delete the deck? It will delete all cards in the deck."
        )
      ) {
        if (confirm("Are you sure? This action cannot be undone!")) {
          deckService.delete(this.deck.deckId);
          this.$router.push("/");
        }
      }
    },
  },
};
</script>

<style scoped>
.editDeckPage {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0;
  margin: 0;
  width: 100%;
  min-height: 100vh;
}

.form-container {
  background-color: #537895;
  background-image: linear-gradient(315deg, #537895 0%, #09203f 74%);
  min-width: 600px;
  border-radius: 30px;
  margin: 30px 0px 0px 0px;
  padding-top: 50px;
  padding-bottom: 50px;
  width: 75%;
  max-width: 1200px;
}

div.header {
  display: flex;
  text-align: center;
  justify-content: center;
  align-items: center;
  color: white;
  vertical-align: middle;
  margin-top: 10px;
}

img#bolt {
  width: 30px;
  opacity: .85;
}

h3 {
  color: white;
  font-size: 45px;
  text-align: center;
  text-transform: uppercase;
  letter-spacing: 1.75px;
  margin: 15px;
}

h4 {
  text-align: center;
  color: white;
  text-transform: uppercase;
  letter-spacing: 1px;
  padding: 0px;
  margin-top: 15px;
  line-height: 1px;
}

div.form {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.update-deck-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 20px 50px 50px 50px;
  width: 325px;
  row-gap: 20px;
}

input.name,
input.subject {
  width: 100%;
  font-family: monospace;
  margin-bottom: 10px;
  padding: 15px;
  border-radius: 8px;
  font-size: medium;
  border-color: #05445e;
  background-color: rgba(241, 241, 241, 0.959);
}
input.name:hover,
input.subject:hover {
  background-color: white;
}

div.buttons {
  display: flex;
  column-gap: 10px;
}

button#save,
button#cancel {
  background-color: #f8f9d2;
  background-image: linear-gradient(315deg, #f8f9d2 0%, #e8dbfc 74%);
  border: solid #bdbdbd 1px;
  box-shadow: 5px 5px 18px rgba(0, 0, 0, 1.93);
  -webkit-box-shadow: 5px 5px 18px rgba(0, 0, 0, 1.93);
  -moz-box-shadow: 5px 5px 18px rgba(0, 0, 0, 1.93);
  width: 120px;
  margin: 10px 10px 20px 10px;
  text-decoration: none;
  text-align: center;
  font-family: monospace;
  border: none;
  color: rgb(0, 0, 0);
  padding: 10px;
  display: inline-block;
  border-radius: 12px;
  font-size: large;
  letter-spacing: 1.75px;
  font-weight: bold;
}

button#delete {
   background-color: #b8b8b8;
  /* background-image: linear-gradient(315deg, #f8f9d2 0%, #e8dbfc 74%); */
  border: solid #BDBDBD 1px; 
  width: 80px;
  margin: 10px 10px 20px 10px;
  text-decoration: none;
  text-align: center;
  font-family: monospace;
  border: none;
  color: rgb(0, 0, 0);
  padding: 10px;
  display: inline-block;
  border-radius: 12px;
  font-size: 12px;
  letter-spacing: 1.75px;
  font-weight: bold;
  box-shadow: 5px 5px 18px rgba(0, 0, 0, 1.93); 
	-webkit-box-shadow: 5px 5px 18px rgba(0, 0, 0, 1.93); 
	-moz-box-shadow: 5px 5px 18px rgba(0, 0, 0, 1.93); 
}
button#delete:hover {
  background-color: red;
  width: 80px;
  margin: 10px 10px 20px 10px;
  text-decoration: none;
  text-align: center;
  font-family: monospace;
  border: none;
  color: rgb(0, 0, 0);
  padding: 10px;
  display: inline-block;
  border-radius: 12px;
  font-size: 12px;
  letter-spacing: 1.75px;
  font-weight: bold;
  box-shadow: 5px 5px 18px rgba(0, 0, 0, 1.93); 
	-webkit-box-shadow: 5px 5px 18px rgba(0, 0, 0, 1.93); 
	-moz-box-shadow: 5px 5px 18px rgba(0, 0, 0, 1.93); 
}

</style>