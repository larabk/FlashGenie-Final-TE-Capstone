<template>
  <div id="app">
    <div id="site-nav" v-if="$store.state.token != ''">
      <nav id="site-nav">
        <ul id-site-nav>
          <li><img id="logo" src="../public/flash-genie-logo.png" /></li>

          <div id="search-home">
            <li id="search-bar">
              <label for="search"></label>
              <input
                id="search"
                name="search"
                placeholder="Search"
                type="text"
              />
            </li>
            <li id="home-logout">
              <router-link v-bind:to="{ name: 'home' }">Home</router-link
              >&nbsp;|&nbsp;
              <router-link
                v-bind:to="{ name: 'logout' }"
              >
                Logout</router-link
              >
            </li>
          </div>
        </ul>
      </nav>
    </div>
    <router-view />
  </div>
</template>






<style scoped>
div#app {
  /* gradient background */
  /* background-color: #f9ea8f;
  background-image: linear-gradient(360deg, 
  #f9ea8f 0%, #aff1da 74%); */

  background-color: #b8c6db;
  background-image: linear-gradient(315deg, #b8c6db 0%, #f5f7fa 74%);
  font-family: monospace;

  padding: 0;
  margin: 0;
  /* width: 100vw; */
  min-height: 100vh;
}

nav#site-nav {
  background-color: white;
}

nav ul {
  display: flex;
  flex-direction: row;
  list-style: none;
  justify-content: space-between;
  margin: 10px;
  align-items: center;
}

div#search-home {
  display: flex;
  column-gap: 10px;
  justify-content: center;
  align-items: center;
  text-transform: uppercase;
}

a {
  text-decoration: none;
  color: black;
}

#search {
  width: 240px;
  border: 3px solid #ccc;
  border-radius: 5px;
  background-color: rgba(241, 241, 241, 0.959);
  padding: 5px;
  box-sizing: border-box;
  resize: vertical;
}
#search:hover {
  background-color: white;
}

a:hover {
  /* font-style:oblique; */
  font-weight: bold;
}

img {
  width: 90px;
  height: auto;
  padding: 10px 0px 10px 0px;
  margin-left: -40px;
}
</style>
