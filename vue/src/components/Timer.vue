<template>
  <div class="container">
    {{ countDown }}
  </div>
</template>

<script>
export default {
  name: 'Timer',
  data() {
    return {
      countDown: 10,
    };
  },
  computed: {
    isTimerEnabled() {
      return this.$store.state.timerEnabled;
    },

  },
  methods: {
    countDownTimer() {
      if (this.isTimerEnabled && this.countDown >= 0) {
        setTimeout(() => {
          this.countDown--;
          if (this.countDown <= 0) {
            this.passEvent();
            setTimeout(() => {
              this.countDown = 11;
            }, 1000);
          }
          this.countDownTimer();
        }, 1000);
      }
    },
    resetTimer(){
      this.countDown = 10;
    },
    passEvent() {
      this.$emit('timerZero');
      
    }
  },
  created() {
    this.countDownTimer();
  },
};
</script>

<style>
</style>