<template>
  <div class="flipperPage">
    <div class="flipper-container">
      <router-link class="end-session" :to="{name: 'score-summary', params: {id: this.deckId}}">End Session</router-link>
      <div class="flip-card">
        <div class="flip-card-inner">
          
          <div class="flip-card-front" >
              <div class="front-card-text">
              {{ currentCards[currentIndex].frontText }}
              
              </div>
          </div>
      
          <div class="flip-card-back">
            <div class="back-card-text">
            {{ currentCards[currentIndex].backText }}
            </div>
          </div>
      </div>
      </div>

      <div class=study-buttons>

        
        <button class=incorrect  @click="markIncorrect">Wrong
                  <img id="click-x" src="/redX.png"/></button>
        

        <div class="buttonControl">
          <button class="decrease" @click="previousCard">Previous</button>
            <div class="cardTracker">
              {{ this.currentIndex + 1 }}/{{ this.currentCards.length}}
            </div>
          <button class="increase" @click="nextCard">Next</button>
          
        </div>
        
       
        <button class="correct" @click="markCorrect">Correct
                  <img id="click-bolt" src="/bolt.png" alt=""/></button>
        
      
      </div>
    </div>
      
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
    // functionFlip() {
    //   var card = document.querySelector('.card');
    //   card.addEventListener( 'click', function() {
    //   card.classList.toggle('is-flipped');
    // });
    // }
    

   
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
  margin: 30px 0px 0px 0px;
  padding: 45px 30px 65px 30px;
  width: 75%;
}

a.end-session {
  color: #ebeb85;
  text-decoration: none;
  text-transform: uppercase;
  letter-spacing: 1px;
  padding: 0px;
  margin-bottom: 25px;
}

a.end-session:hover {
  color: yellow;
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
  justify-content: space-between;
  width: 475px;
  margin-top: 10px;
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
  height: 30px;
  color: white;
}

div.cardTracker {
  padding-top: 10px;
  font-size: 16px;
}

button.increase, button.decrease {
  background-color: #f8f9d2;
  background-image: linear-gradient(315deg, 
  #d2e6f9 0%, #6b8fa7 74%);
  border: solid #747474 1px; 
  height: 40px;
  display: inline-block;
  padding: 5px;
  text-decoration: none;
  letter-spacing: 1.75px;
  text-align: center;
  font-size: 14px;
  font-family: monospace;
  border-radius: 12px;
  font-weight: bold;
  border: none;
  color: white;
  box-shadow: 5px 5px 18px 0px rgba(0,0,0,0.7);
  -webkit-box-shadow: 5px 5px 18px 0px rgba(0,0,0,0.7);
  -moz-box-shadow: 5px 5px 18px 0px rgba(0,0,0,0.7);
  width: 100px;
  border-radius: 10px;
}

button.increase:hover, button.decrease:hover,
button.increase:focus, button.decrease:focus {
  text-decoration: none;
  background-image: linear-gradient(315deg, 
  #dbec8e 0%, #d8b30e 85%);
  box-shadow: inset 0 0 0 2em var(--hover);
  color:#09203f;
  font-weight: bold;
  cursor: pointer; 
}


a{
  color: black;
}

.buttonControl{
    display:flex;
}

.front-card-text, .back-card-text {
  text-align: center;
  padding: 20px;
}

.front-card-text {
  font-size: 50px;
  text-transform: uppercase;
  line-height: 1;
}

button.correct, button.incorrect {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: bottom;
  padding: 5px;
  border-radius: 10px;
  width: 70px;
  height: 70px;
  cursor: pointer; 
  border: none;
  font-family: monospace;
  box-shadow: 5px 5px 18px 0px rgba(0,0,0,0.7);
  -webkit-box-shadow: 5px 5px 18px 0px rgba(0,0,0,0.7);
  -moz-box-shadow: 5px 5px 18px 0px rgba(0,0,0,0.7);
}
button.incorrect:hover {
  background-color: rgb(197, 169, 169);
}

button.correct:hover {
  background-color: rgb(183, 224, 183);
}


img#click-bolt {
  width: 25px;
  height: 40px;
  padding-top: 1px;
  padding-bottom: 5px;
}

img#click-x {
  width: auto;
  height: 25px;
  padding-top: 3px;
  /* height: 65px; */
  margin-top: 5px;
  padding-bottom: 5px;
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
.flip-card:hover .flip-card-inner {
  transform: rotateY(180deg);
}

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
  
   text-align: center;
}

/* Style the front side (fallback if image is missing) */
.flip-card-front {
  background-color: #f8f9d2;
  background-image: linear-gradient(315deg, #e6e9a1 0%, #c6b1e6 74%);
  
  color: white;
  box-shadow: 5px 5px 18px rgba(0, 0, 0, 0.93);
  -webkit-box-shadow: 5px 5px 18px rgba(0, 0, 0, 0.93);
  -moz-box-shadow: 5px 5px 18px rgba(0, 0, 0, 0.93);
  border-radius: 10px;
}

/* Style the back side */
.flip-card-back {
  background-color: #f8f9d2;
  background-image: linear-gradient(315deg, #f8f9d2 0%, #e8dbfc 74%);
  color: black;
  transform: rotateY(180deg);
  box-shadow: 5px 5px 18px rgba(0, 0, 0, 0.93);
  -webkit-box-shadow: 5px 5px 18px rgba(0, 0, 0, 0.93);
  -moz-box-shadow: 5px 5px 18px rgba(0, 0, 0, 0.93);
  border-radius: 10px;
}





</style>