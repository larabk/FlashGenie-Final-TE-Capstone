<template>
  <div id="register" class="text-center">
     <img id="logo" src="/flash-genie-logo.png" alt=""/>
    
    <form class="form-register" @submit.prevent="register">
     <h1 id="h1-register">Create Account</h1>

    <label for="email" class="sr-only"></label>
      <input
        type="text"
        id="email"
        class="form-control"
        placeholder="Email"
        v-model="userDetails.email"
        required
        autofocus
      />

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
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />

      <div class="alert alert-danger" id="alert" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>

      <div id="register-buttons">
      <button id="create-account"><router-link :to="{ name: 'login' }" id="router-account">
        Have an account?</router-link></button>
      <button id="sign-in" type="submit">
        Create Account
      </button>

      </div>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';
import userDetailsService from '../services/UserDetailsService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      userDetails: {
        email: ''
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      }else if (!this.validate()){
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Please enter a valid email address.'
      }
      
      else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              userDetailsService.create(this.userDetails, this.user.username);
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Username already exists. Please select another one.';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
    validate(){
      return (this.userDetails.email.includes('@') && this.userDetails.email.includes('.'));
    }
  },
};
</script>

<style scoped> 

h1#h1-register {
  font-family: monospace;
  text-align: left;
  margin-left: 3px;
  margin-top: 50px;
  font-size: large;
  margin-bottom: 15px;
}

div#register {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 0px 50px 50px 50px;

}

form.form-register {
  display: flex;
  flex-direction: column;
  max-width: 400px;
}

div#register-buttons {
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
  font-size: 2vh;
  font-family: monospace;
  background-color: #537895;
  background-image: linear-gradient(315deg, 
  #537895 0%, #09203f 74%);
  border: none;
  color: white;
  padding: 15px 10px 15px 10px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  border-radius: 12px;
  margin: 4px 2px;
  font-weight: bold;
  margin-bottom: 100px;
  box-shadow: 5px 5px 18px 0px rgba(0,0,0,0.7);
  -webkit-box-shadow: 5px 5px 18px 0px rgba(0,0,0,0.7);
  -moz-box-shadow: 5px 5px 18px 0px rgba(0,0,0,0.7);
}

a#router-account {
  text-decoration: none;
  color: white
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
input#email, 
input#password, 
input#confirmPassword {
  font-family: monospace;
  margin-bottom: 20px;
  padding: 12px;
  border-radius: 8px;
  font-size: larger;
  border-color: #05445e;
  background-color: rgba(241, 241, 241, 0.959);
}

input#username:hover,
input#email:hover, 
input#password:hover, 
input#confirmPassword:hover {
  background-color: white;
}

div#alert {
  font-size: x-small;
  text-align: center;
  margin-bottom: 7px;
}

img#logo {
    width: 350px;
    height: auto;
    margin-bottom: 5px;
    margin-top: 40px;
}

</style>
