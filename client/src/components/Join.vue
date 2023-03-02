<template>

  <div v-if="register == true" class="position-relative">
    <div class="position-absolute top-0 start-50 translate-middle">
      <div class="sticky-top">
        <div class="toast show  " role="alert" aria-live="assertive" aria-atomic="true">
          <div class="toast-header">
            <strong class="me-auto">InMyWord</strong>
            <button @click="$router.push('/')" type="button" class="btn-close ms-2 mb-1" data-bs-dismiss="toast"
                    aria-label="Close">
              <span aria-hidden="true"></span>
            </button>
          </div>
          <div class="toast-body">
            {{ welcomeComment }}
          </div>
        </div>
      </div>
    </div>
  </div>


  <div class="container-sm">

    <div class="py-5 text-center">
      <h2>회원 가입</h2>
    </div>

    <div v-if="checkName == false" class="form-group">
      <label class="col-form-label col-form-label-lg mt-4" for="displayName">Display Name</label>
      <input autofocus required="true" @input="nameCheck($event.target.value)" class="form-control form-control-lg"
             type="text"
             :value="displayName" placeholder="닉네임을 입력해주세요" id="displayName">
    </div>

    <div v-if="checkName == true" class="form-group has-danger">
      <label class="col-form-label col-form-label-lg mt-4" for="displayName">Display Name</label>
      <input :required="true" @input="nameCheck($event.target.value)" type="text"
             class="form-control form-control-lg is-invalid"
             id="inputInvalid">
      <div class="invalid-feedback">문자를 입력해주세요!</div>
    </div>

    <div v-if="checkEmail == false" class="form-group">
      <label class="col-form-label col-form-label-lg mt-4" for="email">Email</label>
      <input ref="email2" :required="true" @input="emailCheck($event.target.value)" class="form-control form-control-lg"
             type="text"
             :value="email" placeholder="이메일을 입력해주세요" id="email">
    </div>

    <div v-if="checkEmail == true" class="form-group has-danger">
      <label class="col-form-label col-form-label-lg mt-4" for="displayName">Email</label>
      <input ref="email1" :required="true" @input="emailCheck($event.target.value)" type="text"
             class="form-control form-control-lg is-invalid"
             :value="email" id="inputInvalid">
      <div class="invalid-feedback">이메일 형식을 입력해주세요!(~@~.com 또는 .net)</div>
    </div>

    <div v-if="checkPwd==false" class="form-group">
      <label class="col-form-label col-form-label-lg mt-4" for="password">Password</label>
      <input ref="pwd2" :required="true" :value="pwd" @input="pwdCheck($event.target.value)"
             class="form-control form-control-lg" type="password"
             placeholder="비밀번호를 입력해주세요"
             id="password">
      <input v-if="pwdDbCheck == 0" @input="doubleCheck($event.target.value)"
             class="form-control form-control-lg mt-2"
             type="password"
             placeholder="비밀번호를 한 번 더 입력해주세요">
      <div v-if="pwdDbCheck==1" class="valid">비밀번호가 일치합니다!</div>
      <div v-if="pwdDbCheck== 2" class="form-group has-danger">
        <input ref="pwd3" :required="true" type="password" @input="doubleCheck($event.target.value)"
               :value="dbpwd" class="form-control form-control-lg mt-4 is-invalid">
        <div class="invalid-feedback">비밀번호가 일치하지 않습니다.</div>
      </div>

    </div>
    <div v-if="checkPwd == true" class="form-group has-danger">
      <label class="col-form-label col-form-label-lg mt-4" for="password">Password</label>
      <input ref="pwd1" @input="pwdCheck($event.target.value)" class="form-control form-control-lg is-invalid"
             :value="pwd" type="password" change="비밀번호를 입력해주세요">
      <div class="invalid-feedback">비밀번호는 숫자+영문자+특수문자 조합으로 8자리 이상 입력해 주세요!</div>
      <input class="form-control form-control-lg" type="password"
             placeholder="비밀번호를 한 번 더 입력해주세요">
    </div>

    <div class="container text-center">
      <div class="row">
        <div class="col">
          <button type="button" class="btn btn-primary mt-3" @click="join">회원 가입</button>
        </div>
      </div>
    </div>


  </div>

</template>

<script>
import VerEx from "verbal-expressions";
import axios from "axios";


export default {
  name: "Join",
  data() {
    return {
      checkPwd: false,
      checkName: false,
      checkEmail: false,
      displayName: '',
      email: '',
      pwd: '',
      pwdDbCheck: 0,
      welcomeComment: '',
      date: '',
      register: false,
      dbpwd: '',
    }
  },

  methods: {

    doubleCheck(input) {

      if (this.pwd === input) {
        this.pwdDbCheck = 1;
      } else {
        this.pwdDbCheck = 2;
        this.dbpwd = input;
        this.$nextTick(() => {
          this.$refs.pwd3.focus();
        })
      }
    },

    pwdCheck(input) {
      console.log(input)
      const regx = /^(?=.*[a-zA-Z])(?=.*[!@#$%^+=-])(?=.*[0-9]).{8,25}$/;
      if (!regx.test(input)) {
        console.log(input)
        this.checkPwd = true;
        this.pwd = input;
        this.$nextTick(() => {
          this.$refs.pwd1.focus();
        })
        return;
      }
      this.pwd = input;
      this.checkPwd = false;
      this.$nextTick(() => {
        this.$refs.pwd2.focus();
      })
    },

    emailCheck(input) {
      const regx = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{3}$/
      if (!regx.test(input)) {
        this.checkEmail = true;
        this.email = input;
        this.$nextTick(() => {
          this.$refs.email1.focus();
        })
        return;
      }

      this.checkEmail = false;
      this.email = input;
      this.$nextTick(() => {
        this.$refs.email2.focus();
      })
    },
    nameCheck(input) {
      const regx = VerEx().startOfLine()
          .find(' ')
          .endOfLine();

      if (regx.test(input) || input.length == 0) {
        this.checkName = true;
        return;
      }

      this.displayName = input;
      this.checkName = false;

    },

    join() {
      const obj = {
        displayName: this.displayName,
        password: this.pwd,
        email: this.email,
      }

      axios.post('http://localhost:9090/users', obj).then(
          (a) => {
            this.welcomeComment = a.data;
            this.register = true;
          }
      )
    },
  },

}
</script>

<style scoped>
.valid {
  font-size: large;
  color: dodgerblue;
}
</style>