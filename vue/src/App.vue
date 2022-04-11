<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }"></router-link>&nbsp;&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

<style scoped>

div#app {
    /* background-color:rgb(222, 229, 243); */
    /* background-color: #5ca0f2;
    background-image: linear-gradient(315deg, 
  #5ca0f2 0%, #f5f7f6 74%); */

  background-color: #f9ea8f;
  background-image: linear-gradient(360deg, 
  #f9ea8f 0%, #aff1da 74%);
}


</style>
