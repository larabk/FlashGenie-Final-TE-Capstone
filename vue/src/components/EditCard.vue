<template>
  <div class="editCardPage">
    <div class="form-container">
      <div class="header">
        <img id="bolt" src="/bolt.png" alt="">  
        <h3>Edit Card</h3>
        <img id="bolt" src="/bolt.png" alt="">
      </div>

      <div class="form">   
        <form @submit.prevent="updateCard" class="update-card-form">
          <input
            type="text"
            class="front-text"
            placeholder="New term"
            v-model="card.frontText"/>
          
          <input
            type="text"
            class="back-text"
            placeholder="New definition"
            v-model="card.backText"/>

          <input
            type="text"
            class="key-words"
            v-model="card.keyWords"
            placeholder="New keywords (Separate with commas)"/>

            <div class="buttons">
              <button id="delete"  @click.prevent="deleteCard">DELETE THIS CARD</button>
              <button id="cancel" type="cancel" @click.prevent="cancelUpdate">Cancel</button>
              <button id="save" type="submit">Submit</button>
            </div>
        </form>
      </div>
    </div>
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
    cancelUpdate() {
      this.card.frontText = "";
      this.card.backText = "";
      this.card.keyWords = "";
      this.$router.push(`/deck/${this.card.deckId}/cards`);
    },
    deleteCard() {
      if (
        confirm("Do you really want to delete the card? This cannot be undone.")
      ) {
        cardService.delete(this.card.cardId);
        this.$router.push(`/deck/${this.card.deckId}/cards`);
      }
    },
  },
};
</script>

<style scoped>
.editCardPage {
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
  background-image: linear-gradient(315deg, 
  #537895 0%, #09203f 74%);
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
  margin-top:10px;
}

img#bolt {
  width: 30px;
  opacity: 0.5;
}

h3 {
  color: white;
  font-size: 45px;
  text-align: center;
  text-transform: uppercase;
  letter-spacing: 1.75px;
  margin: 15px;
}

div.form {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.update-card-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 50px;
  width: 325px;
  row-gap: 20px;
}

input.front-text, input.back-text, input.key-words  {
  width: 100%;
  font-family: monospace;
  margin-bottom: 10px;
  padding: 15px;
  border-radius: 8px;
  font-size: medium;
  border-color: #05445e;
  background-color: rgba(241, 241, 241, 0.959);
}

input.front-text:hover, 
input.back-text:hover, 
input.key-words:hover {
  background-color: white;
}

div.buttons {
  display: flex;
  column-gap: 10px;
}

button#save, button#cancel {
  background-color: #f8f9d2;
  background-image: linear-gradient(315deg, #f8f9d2 0%, #e8dbfc 74%);
  border: solid #BDBDBD 1px; 
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