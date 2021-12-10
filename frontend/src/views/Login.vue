<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal White--text">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username</label>
      
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <br>
      <label for="password" class="sr-only">Password</label>
      
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
    <br>
      <router-link class="need-account-btn" :to="{ name: 'register' }">Need an account?</router-link>
      
      <button type="submit">Sign in</button>
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
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);

            // set user role to teacher or student
            this.$store.commit("SET_USER_ROLE", response.data.user.teacher);
            
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = false;
          }
        });
    }
  }
};
</script>

<style scoped>

.form-signin
{
  display: grid;
	width: 380px;
	height: 500px;
	position: relative;
	margin: 6% auto;
	background: rgb(49, 49, 49);
	padding: 15px;
	border-radius: 5px;
  box-shadow: 0px 0px 100px rgba(219, 219, 219, 0.178);
  text-align: left;
  opacity: 0.7;
  color: rgb(221, 221, 197);
  font-family: sans-serif;
   }

   .need-account-btn {
     color: rgb(221, 221, 197);
   }
</style>







 

