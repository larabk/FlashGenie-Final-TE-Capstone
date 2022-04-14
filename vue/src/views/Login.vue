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
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>

      <label for="username" class="sr-only"></label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only"></label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />

      <div id="login-buttons">
        <button id="create-account">
          <router-link :to="{ name: 'register' }" id="router-account">
            Register</router-link
          >
        </button>

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
h1#sign-in {
  font-family: monospace;
  text-align: left;
  margin-left: 3px;
  margin-top: 50px;
  font-size: medium;
  margin-bottom: 10px;
}

div#login {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 0px 50px 50px 50px;
}

.form-signin {
  display: flex;
  flex-direction: column;
  max-width: 300px;
}

div#login-buttons {
  display: flex;
  justify-content: center;
  margin-top: 20px;
  column-gap: 25px;
}

button#create-account,
button#sign-in {
  width: 250px;
  margin: 10px 10px 20px 10px;
  text-decoration: none;
  text-align: center;
  font-size: 1.75vh;
  font-family: monospace;
  background-color: #537895;
  background-image: linear-gradient(315deg, 
  #537895 0%, #09203f 74%);
  border: none;
  color: white;
  padding: 10px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  border-radius: 12px;
  margin: 4px 2px;
  font-weight: bold;
  margin-bottom: 100px;
  box-shadow: 10px 10px 53px 0px rgba(0,0,0,0.7);
  -webkit-box-shadow: 10px 10px 53px 0px rgba(0,0,0,0.7);
  -moz-box-shadow: 10px 10px 53px 0px rgba(0,0,0,0.7);
}

a#router-account {
  text-decoration: none;
  color: white;
}
a#router-account:visited {
  text-decoration: none;

}
a#router-account:hover {
  text-decoration: none;
  color: #ebeb85; 
}

button#create-account:hover, 
button#sign-in:hover {
  color: #ebeb85;  
}

input#username,
input#password {
  font-family: monospace;
  margin-bottom: 20px;
  padding: 8px;
  border-radius: 8px;
  font-size: medium;
  border-color: #05445e;
}

img#logo {
  width: 250px;
  height: auto;
  margin-bottom: 5px;
  margin-top: 60px;
}
</style>
