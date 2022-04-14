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
  background-color: #a0f3d5;
  border: none;
  color: rgb(0, 0, 0);
  padding: 10px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  border-radius: 12px;
  margin: 4px 2px;
  font-weight: bold;
  margin-bottom: 100px;

  box-shadow: 0px 1px 0px #ccc, 0px 2px 0px #c9c9c9, 0px 3px 0px #bbb,
    0px 4px 0px #b9b9b9, 0px 5px 0px #aaa, 0px 6px 1px rgba(0, 0, 0, 0.1),
    0px 0px 5px rgba(0, 0, 0, 0.1), 0px 1px 3px rgba(0, 0, 0, 0.3),
    0px 3px 5px rgba(0, 0, 0, 0.2), -4px 8px 8px rgba(0, 0, 0, 0.1),
    -8px 16px 16px rgba(0, 0, 0, 0.37), -16px 32px 32px rgba(0, 0, 0, 0.25),
    -32px 64px 64px rgba(0, 0, 0, 0.25);
}

button#create-account:visited,
button#sign-in:visited {
  text-decoration: none;
}

button#create-account:hover,
button#sign-in:hover {
  background-color: rgb(204, 250, 244);
}

a#router-account:visited {
  text-decoration: none;
  color: rgb(0, 0, 0);
}

a#router-account {
  text-align: center;
  text-decoration: none;
}

input#username,
input#password {
  font-family: monospace;
  margin-bottom: 20px;
  padding: 8px;
  border-radius: 8px;
  font-size: medium;
}

img#logo {
  width: 200px;
  height: auto;
  margin-bottom: 5px;
  margin-top: 60px;
}
</style>
