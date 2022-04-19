<template>
  <div class="scoreSummary">
    <h1>Your Score: {{ this.currentScore }} out of {{ this.maxScore }}</h1>
    <div class="homeButton">
    <router-link :to="{ name: 'home' }">Home</router-link>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentScore: this.$store.state.currentScore,
      maxScore: this.$store.state.maxScore,
    };
  },
};
</script>

<style scoped>
.homeButton {
  color: black;
}
</style>