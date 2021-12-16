<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <label for="firstName" class="sr-only">First name</label>
      <input
        type="text"
        id="firstName"
        class="form-control"
        placeholder="First name"
        v-model="user.firstName"
        required
        autofocus
      />
      <br>
      <label for="lastName" class="sr-only">Last name</label>
      <input
        type="text"
        id="lastName"
        class="form-control"
        placeholder="LastName"
        v-model="user.lastName"
        required
        autofocus
      />
      <br>


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
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <br>
      <div class="am-a-teacher">
        <input class="am-teacher-check" type="checkbox" id="isTeacher" v-model="user.isTeacher">
        <label for="isTeacher">I am registering as a teacher</label>
      </div>
      <br>
      <br>
      <router-link class="need-account-btn" :to="{ name: 'login' }">Have an account?</router-link>
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        firstName: '',
        lastName: '', 
        isTeacher: false,
        role: 'user'
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
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
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
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>

.form-register
{

  display: grid;
	width: 380px;
	height: 500px;
	position: relative;
	margin: 6% auto;
	background: rgb(49, 49, 49);
  border-style: solid;
  border-width: 1px;
	border-radius: 8px;
	padding: 15px;
  box-shadow: 5px 5px 10px 2px rgba(219, 219, 219, 0.178);
  text-align: left;
  text-decoration-color: rgb(212, 207, 186);
  opacity: 0.7;
  color: rgb(221, 221, 197);
}

.need-account-btn {
     color: rgb(221, 221, 197);
   }

input {
  width: 98%;
  margin-left: auto;
  margin-right: auto;
}

.am-a-teacher {
  display: inline-block;
  margin-left: 0px;
  text-align: left;
}

.am-teacher-check {
  display: flex;
  align-content: left;
  align-items: left;
}


</style>
