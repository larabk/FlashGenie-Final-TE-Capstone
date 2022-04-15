<template>
  <div class="create-card">
    <div class="form-container">
      <div class="header">
          <img id="bolt" src="/bolt.png" alt="">  
          <h3>Create New Card</h3>
          <img id="bolt" src="/bolt.png" alt="">
        </div>

     <div class="form">    
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
        <input type="text" id="card-keywords" placeholder="Keywords (separate with commas)" v-model="card.keyWords">
          <div class="buttons">
            <div class="alert" v-if="creationErrors">{{ creationErrorMessage }}</div>  
            <button id="cancel" type="cancel" @click.prevent="cancelForm">Cancel</button>
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

.create-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0;
  margin: 0;
  width: 100%;
  min-height: 100vh;
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

.card-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 50px;
  width: 325px;
  row-gap: 20px;
}

div.form {
  display: flex;
  flex-direction: row;
  justify-content: center;
  /* margin: 10px 50px 50px 50px; */
}

input#card-front-text, 
input#card-back-text, 
input#card-keywords {
  width: 100%;
  font-family: monospace;
  margin-bottom: 10px;
  padding: 15px;
  border-radius: 8px;
  font-size: medium;
  border-color: #05445e;
  background-color: rgba(241, 241, 241, 0.959);
}

input#card-front-text:hover, 
input#card-back-text:hover, 
input#card-keywords:hover {
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
  text-align: center;
  text-decoration: none;
  display: inline-block;
  border-radius: 12px;
  font-size: large;
  letter-spacing: 1.75px;
  font-weight: bold;
}

/* button#save:hover, button#cancel:hover {

} */





</style>