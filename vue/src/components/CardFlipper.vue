<template>
  <div class="flipperPage">
    <div class="flipper-container">
      <div class="flip-card">
        <div class="flip-card-inner">
          
          <div class="flip-card-front">
              <div class="front-card-text">
              {{ currentCards[currentIndex].frontText }}
              </div>

              <div class="scoreButtons">
                
                <a href="incorrect" class=incorrect @click="markIncorrect"><img id="click-x" src="/redX.png"/></a>
                <a href="correct" class="correct" @click="markCorrect"><img id="click-bolt" src="/bolt.png" alt=""/></a>
                                
              </div>
          </div>
      
          <div class="flip-card-back">
            <div class="back-card-text">
            {{ currentCards[currentIndex].backText }}
            </div>
              <div class="scoreButtons">
              <button class="incorrect" @click="markIncorrect">Incorrect</button>
              <button class="correct" @click="markCorrect">Correct</button>
            </div>
          </div>
      </div>
      </div>

      <div class=study-buttons>
        <div class="buttonControl">
          <button class="decrease" @click="previousCard">Previous</button>
            <div class="cardTracker">
              {{ this.currentIndex + 1 }}/{{ this.currentCards.length}}
            </div>
          <button class="increase" @click="nextCard">Next</button>
        </div>
      </div>
    </div>

    <router-link :to="{name: 'score-summary', params: {id: this.deckId}}">End Session</router-link>
      
  </div>
</template>

<script>
import cardService from "@/services/CardService";
export default {
  data() {
    return {
      displayFront: true,
      currentIndex: 0,
      deckId: Number(this.$route.params.id),
      // cards: this.$store.state.allCards,
    };
  },
  computed: {
    cards(){
      return this.$store.state.allCards;
    },
    currentCards() {
      return this.cards.filter((card) => card.deckId === this.deckId);
    },

    currentScore() {
      return this.$store.state.currentScore;
    },
  },
  methods: {
    flipCard() {
      this.displayFront = !this.displayFront;
    },
    nextCard() {
      if (this.currentIndex < this.currentCards.length - 1) {
        this.displayFront = true;
        this.currentIndex++;
      }
    },
    previousCard() {
      if (this.currentIndex > 0) {
        this.displayFront = true;
        this.currentIndex--;
      }
    },
    setScore() {
      let score = this.currentScore;
      this.$store.commit("SET_SCORE", score + 1);
    },
    resetScore(score) {
      this.$store.commit("SET_SCORE", score);
    },
    setMaxScore(){
      let score = this.currentCards.length;
      this.$store.commit("SET_MAX_SCORE", score)
    },
    addMarkedProperty() {
      return this.currentCards.forEach((card) => (card.isMarked = false));
    },
    markCorrect() {
      if (this.currentCards[this.currentIndex].isMarked === false) {
        this.setScore();
        this.currentCards[this.currentIndex].isMarked = true;
      }
    },
    markIncorrect() {
      this.currentCards[this.currentIndex].isMarked = true;
    },
     getAllCards() {
      if (this.$store.state.allCards.length === 0) {
        cardService.getAllCards().then((response) => {
          this.$store.commit("SET_ALL_CARDS", response.data);
        });
      }
    },
   
  },
  created() {
    this.getAllCards();
    this.addMarkedProperty();
    this.setMaxScore();
    this.resetScore(0);
  },
};
</script>

<style scoped>

.flipperPage {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0;
  margin: 0;
  width: 100%;
  min-height: 100vh;
}

.flipper-container {
  display: flex;
  flex-direction: column;
  background-color: #537895;
  background-image: linear-gradient(315deg, #537895 0%, #09203f 74%);
  align-items: center;
  min-width: 600px;
  max-width: 1080px;
  border-radius: 30px;
  margin: 0px 0px 0px 0px;
  padding: 65px 30px 65px 30px;
  width: 75%;
}

div.flip-card {
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  width: 80%;
  height: 275px;
  justify-content: center;
  align-items: center;
  max-width: 500px;
  padding: 15px;
  margin: 0;
  border: none;
  color: rgb(0, 0, 0);
  text-align: center;
  text-decoration: none;
  border-radius: 10px;
  font-size: larger;
  font-size: x-large;
  letter-spacing: 1.75px;
  font-weight: bold;
}

.flip-card:hover {
  cursor: pointer; 
}

div.study-buttons {
  display: flex;
}

div.scoreButtons {
  display: flex;
  justify-content: space-between;
  width: 90%;
  height: 40px;
  margin-left: 20px;
  margin-right: 20px;
  vertical-align: middle;

}

div.buttonControl {
  display: flex;
  column-gap: 20px;
}

a{
  color: black;
}

.buttonControl{
    display:flex;
}

.front-card-text, .back-card-text {
  text-align: center;
  /* margin-top: 100px; */
  /* letter-spacing: 1vh; */
  /* padding: 20px; */
}

.front-card-text {
  font-size: 50px;
  text-transform: uppercase;
  /* line-height: 1; */
  
}

/* .back-card-text {

} */

img#click-bolt {
  width: 30px;
  height: 45px;
  /* padding: 10px; */
}

img#click-x {
  width: auto;
  height: 30px;
  /* padding: 10px; */
  /* height: 65px; */
  margin-top: 5px;
}




/* CARD FLIP ACTION CSS */
.flip-card {
  background-color: transparent;
  width: 300px;
  height: 200px;
  border: 1px solid #f1f1f1;
  perspective: 1000px; 
}

/* This container is needed to position the front and back side */
.flip-card-inner {
  position: relative;
  width: 100%;
  height: 100%;
  text-align: center;
  transition: transform 0.8s;
  transform-style: preserve-3d;
}

/* Do an horizontal flip when you move the mouse over the flip box container */
/* .flip-card:active .flip-card-inner {
  transform: rotateY(180deg);
} */

/* Position the front and back side */
.flip-card-front, .flip-card-back {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  row-gap: 65px;
  position: absolute; 
  width: 100%;
  height: 100%;
  -webkit-backface-visibility: hidden; /* Safari */
  backface-visibility: hidden;
  /* width: fit-content; */
   text-align: center;
}

/* Style the front side (fallback if image is missing) */
.flip-card-front {
  background-color: #f8f9d2;
  background-image: linear-gradient(315deg, #f8f9d2 0%, #e8dbfc 74%);
  color: black;
  box-shadow: 5px 5px 18px rgba(0, 0, 0, 0.93);
  -webkit-box-shadow: 5px 5px 18px rgba(0, 0, 0, 0.93);
  -moz-box-shadow: 5px 5px 18px rgba(0, 0, 0, 0.93);
  border-radius: 10px;
}

/* Style the back side */
.flip-card-back {
  background-color: #f8f9d2;
  background-image: linear-gradient(315deg, #f8f9d2 0%, #e8dbfc 74%);
  color: white;
  transform: rotateY(180deg);
  box-shadow: 5px 5px 18px rgba(0, 0, 0, 0.93);
  -webkit-box-shadow: 5px 5px 18px rgba(0, 0, 0, 0.93);
  -moz-box-shadow: 5px 5px 18px rgba(0, 0, 0, 0.93);
  border-radius: 10px;
}





</style>