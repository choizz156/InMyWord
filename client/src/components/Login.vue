<template>

  <div class="container-sm">

    <div class="py-5 text-center">
      <h2>로그인</h2>
    </div>

    <form action="http://localhost:9090/users/login" method="post">
      <label class="col-form-label col-form-label-lg mt-4" for="email">Email</label>
      <input :required="true" @change="inputEmail($event.target.value)" name="username"
             class="form-control form-control-lg"
             type="text"
             placeholder="이메일을 입력해주세요">

      <label class="col-form-label col-form-label-lg mt-4" for="password">Password</label>
      <input :required="true"
             class="form-control form-control-lg" @change="inputPwd($event.target.value)" type="password"
             name="password"
             placeholder="비밀번호를 입력해주세요"
      >
      <div class="row">
        <div class="col">
          <button type="submit" class="btn btn-primary mt-3"> 로그인</button>
          <!--        <button class="btn btn-primary mt-3" @click="login"> 로그인</button>-->
          <span> <router-link to="/join">아직 회원이 아니세요?</router-link></span>
        </div>
        <div class="col">
          <button type="button" class="btn btn-primary mt-3"> 구글로그인</button>
        </div>
        <div class="col">
        </div>
      </div>
    </form>
  </div>
</template>

<script>

import axios from "axios";

export default {
  name: "Login",
  data() {
    return {
      email: '',
      password: '',
      success: false,
    }
  },
  methods: {

    inputEmail(input) {
      this.email = input;
    },

    inputPwd(input) {
      this.password = input;
    },

    login() {
      const param = new URLSearchParams();
      param.append('username', this.email)
      param.append('password', this.password)

      axios.post("http://localhost:9090/users/login", param, {
        headers: {
          'Accept': 'application/x-www-form-urlencoded',
        }
      }).then(
          (a) => {
            console.log(a);
          }
      )
    },

  },
}
</script>

<style scoped>

</style>