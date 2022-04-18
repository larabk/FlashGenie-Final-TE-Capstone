<template>
  <div id="login" class="text-center">
    <img id="logo" src="/flash-genie-logo.png" alt="" />

      <form class="form-signin" @submit.prevent="login">
        <h1 id="sign-in">Please Sign In</h1>
        
        <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
          Invalid username and password!
        </div>

        <div
          class="alert alert-success"
          role="alert"
          v-if="this.$route.query.registration">Thank you for registering, please sign in.
        </div>

        <label for="username" class="sr-only"></label>
          <input
            type="text"
            id="username"
            class="form-control"
            placeholder="Username"
            v-model="user.username"
            required
            autofocus/>

        <label for="password" class="sr-only"></label>
          <input
            type="password"
            id="password"
            class="form-control"
            placeholder="Password"
            v-model="user.password"
            required/>

        <div id="login-buttons">
          <router-link :to="{ name: 'register' }" id="router-account" class='button'>Register</router-link>
          <button id="sign-in" type="submit">Sign in</button>
        </div>
      </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";


export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },

      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push('/');
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>

div#login {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 0px 50px 50px 50px;
  padding-top: 70px;
}

img#logo {
  width: 350px;
  height: auto;
  margin: 40px 0px 5px 0px;
  padding: 0px;
}

.form-signin {
  display: flex;
  flex-direction: column;
  max-width: 400px;
}

h1#sign-in {
  font-family: monospace;
  text-align: left;
  margin-left: 3px;
  margin-top: 50px;
  font-size: large;
  margin-bottom: 15px;
}

input#username,
input#password {
  font-family: monospace;
  margin-bottom: 20px;
  padding: 12px;
  border-radius: 8px;
  font-size: larger;
  border-color: #05445e;
  background-color: rgba(241, 241, 241, 0.959);
}

input#username:hover,
input#password:hover {
  background-color: white;
}

/* original css 041722 EOD */
div#login-buttons {
  display: flex;
  justify-content: center;
  margin-top: 20px;
  column-gap: 25px;
}

a#router-account.button,
button#sign-in {
  width: 200px;
  height: 45px;
  margin: 10px 10px 20px 10px;
  text-decoration: none;
  text-align: center;
  font-size: 14px;
  font-family: monospace;
  border-radius: 12px;
  font-weight: bold;
  background-color: #537895;
  background-image: linear-gradient(315deg, 
  #537895 0%, #09203f 74%);
  border: none;
  color: white;
  margin-bottom: 100px;
  box-shadow: 5px 5px 18px 0px rgba(0,0,0,0.7);
  -webkit-box-shadow: 5px 5px 18px 0px rgba(0,0,0,0.7);
  -moz-box-shadow: 5px 5px 18px 0px rgba(0,0,0,0.7);
}

a#router-account.button {
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-decoration: none;
  color: white;
  padding-top: 0px;
}

button#sign-in {
  display: block;
  justify-content: center;
  padding: 0px;  
}

a#router-account:hover, a#router-account:focus {
  text-decoration: none;
  color: #ebeb85; 
  box-shadow: inset 0 0 0 2em var(--hover);
}

button#sign-in:hover, button#sign-in:focus {
  color: #ebeb85; 
  cursor: pointer; 
  box-shadow: inset 0 0 0 2em var(--hover);
}

/* playing with fill-in buttons 041822 AM */

a#router-account.button {
  display: inline-block;
  vertical-align: middle;
  -webkit-transform: perspective(1px) translateZ(0);
  transform: perspective(1px) translateZ(0);
  box-shadow: 0 0 1px rgba(0, 0, 0, 0);
  position: relative;
  background: #ebeb85; 
  -webkit-transition-property: color;
  transition-property: color;
  -webkit-transition-duration: 0.3s;
  transition-duration: 0.3s;
  color: #ebeb85; 
  box-shadow: 5px 5px 18px 0px rgba(0,0,0,0.7);
  -webkit-box-shadow: 5px 5px 18px 0px rgba(0,0,0,0.7);
  -moz-box-shadow: 5px 5px 18px 0px rgba(0,0,0,0.7);
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-decoration: none;
  padding-top: 0px;
  font-weight: bold;
}

button#sign-in {
  display: inline-block;
  vertical-align: middle;
  -webkit-transform: perspective(1px) translateZ(0);
  transform: perspective(1px) translateZ(0);
  box-shadow: 0 0 1px rgba(0, 0, 0, 0);
  position: relative;
  background: #ebeb85; 
  -webkit-transition-property: color;
  transition-property: color;
  -webkit-transition-duration: 0.3s;
  transition-duration: 0.3s;
  color: #ebeb85; 
  box-shadow: 5px 5px 18px 0px rgba(0,0,0,0.7);
  -webkit-box-shadow: 5px 5px 18px 0px rgba(0,0,0,0.7);
  -moz-box-shadow: 5px 5px 18px 0px rgba(0,0,0,0.7);
  font-weight: bold;
}

a#router-account.button:before {
  content: "";
  position: absolute;
  z-index: -1;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #537895;
  background-image: linear-gradient(315deg, 
  #537895 0%, #09203f 74%);
  -webkit-transform: scale(1);
  transform: scale(1);
  -webkit-transition-property: transform;
  transition-property: transform;
  -webkit-transition-duration: 0.3s;
  transition-duration: 0.3s;
  -webkit-transition-timing-function: ease-out;
  transition-timing-function: ease-out;
  border-radius: 12px;
  font-weight: bold;
}

button#sign-in:before {
  content: "";
  position: absolute;
  z-index: -1;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #537895;
  background-image: linear-gradient(315deg, 
  #537895 0%, #09203f 74%);
  -webkit-transform: scale(1);
  transform: scale(1);
  -webkit-transition-property: transform;
  transition-property: transform;
  -webkit-transition-duration: 0.3s;
  transition-duration: 0.3s;
  -webkit-transition-timing-function: ease-out;
  transition-timing-function: ease-out;
  border-radius: 12px;
  font-weight: bold;
}

a#router-account.button:hover, a#router-account.button:focus, a#router-account.button:active {
  color:#09203f;
  box-shadow: 5px 5px 18px 0px rgba(0,0,0,0.7);
  -webkit-box-shadow: 5px 5px 18px 0px rgba(0,0,0,0.7);
  -moz-box-shadow: 5px 5px 18px 0px rgba(0,0,0,0.7);
  font-weight: bold;
}

button#sign-in:hover, button#sign-in:focus, button#sign-in:active {
  color:#09203f;
  box-shadow: 5px 5px 18px 0px rgba(0,0,0,0.7);
  -webkit-box-shadow: 5px 5px 18px 0px rgba(0,0,0,0.7);
  -moz-box-shadow: 5px 5px 18px 0px rgba(0,0,0,0.7);
  font-weight: bold;
}

a#router-account.button:hover:before, a#router-account.button:focus:before, a#router-account.button:active:before {
  -webkit-transform: scale(0);
  transform: scale(0);
  font-weight: bold;
}
button#sign-in:hover:before, button#sign-in:focus:before, button#sign-in:active:before {
  -webkit-transform: scale(0);
  transform: scale(0);
  font-weight: bold;
}


</style>
