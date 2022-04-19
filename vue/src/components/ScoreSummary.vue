<template>
  <div class="scoreSummary">
    <div class="summary-container"> 
     <div id="title-links">

      <div class="header">
        <div class="my-cards">
          <img id="bolt" src="/bolt.png" alt="" />
          <h3>Summary</h3>
          <img id="bolt" src="/bolt.png" alt="" />
        </div>
      </div>
     </div>
      

     <div class="score"> 
    <h4>Your Score: {{ this.currentScore }} out of {{ this.maxScore }}</h4>
    </div>

    <div class="homeButton">
    <router-link :to="{ name: 'home' }">Home</router-link>
    </div>
    
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

.scoreSummary {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0;
  margin: 0;
  width: 100%;
  min-height: 100vh;
}

div#title-links {
  display: flex;
  flex-direction: column;
}

.summary-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #537895;
  background-image: linear-gradient(315deg, #537895 0%, #09203f 74%);
  justify-content: center;
  min-width: 600px;
  max-width: 1080px;
  border-radius: 30px;
  margin: 30px 0px 0px 0px;
  padding: 40px 30px 20px 30px;
  width: 75%;
}

div.my-cards {
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
  opacity: 0.85;
  padding: 0px;
  margin: 0px;
}

h3 {
  color: white;
  font-size: 45px;
  text-align: center;
  text-transform: uppercase;
  letter-spacing: 1.75px;
  margin: 0px 15px 0px 15px;
}

div.score {
  display: flex;
  width: 350px;
  height: 150px;
  align-items: center;
  padding: 25px;
  margin: 35px;
  /* margin-left: 20px; */
  border: none;
  color: rgb(0, 0, 0);
  text-align: center;
  text-decoration: none;
  border-radius: 10px;
  justify-content: center;
  font-size: larger;
  background-color: #f8f9d2;
  background-image: linear-gradient(315deg, #f8f9d2 0%, #e8dbfc 74%);
  border: solid #bdbdbd 1px;
  box-shadow: 5px 5px 18px rgba(0, 0, 0, 0.93);
  -webkit-box-shadow: 5px 5px 18px rgba(0, 0, 0, 0.93);
  -moz-box-shadow: 5px 5px 18px rgba(0, 0, 0, 0.93);
  font-size: x-large;
  letter-spacing: 1.75px;
  font-weight: bold;
 
}


a.router-link-active {
  color: white;
  text-decoration: none;
}

.homeButton:visited {
  color: white;
  text-decoration: none;
}
</style>