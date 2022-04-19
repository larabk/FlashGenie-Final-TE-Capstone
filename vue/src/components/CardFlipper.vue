<template>
  <div class="cardFlipperDisplay">
    <div class="card" @click="flipCard">
      {{
        displayFront
          ? currentCards[currentIndex].frontText
          : currentCards[currentIndex].backText
      }}
    </div>
    <div class="scoreButtons">
      <button class="incorrect" @click="markIncorrect">Incorrect</button>
      <button class="correct" @click="markCorrect">Correct</button>
    </div>
    <div class="buttonControl">
      <button class="decrease" @click="previousCard">Previous</button>
      <div class="cardTracker">
        {{ this.currentIndex + 1 }}/{{ this.currentCards.length}}
      </div>
      <button class="increase" @click="nextCard">Next</button>
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
/* just to see router link */
a{
  color: black;
}
.cardFlipperDisplay {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.card {
  min-width: 500px;
  min-height: 250px;
  text-align: center;
  border: 1px solid red;
}
.buttonControl{
    display:flex;
}
</style>