<template>
  <div class="scoreSummary">
    <h1>Your Score: {{ this.currentScore }} out of {{ this.maxScore }}</h1>
  </div>
</template>

<script>
export default {
  data() {
    return {
        currentScore: this.$store.state.currentScore,
        maxScore: this.$store.state.maxScore
    };
  },
}
</script>

<style>
</style>