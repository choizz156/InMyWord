<template>
  로그인하 홈
  <div>{{$store.state.email}}</div>

</template>

<script>
export default {
  name: "LoginHome",
  data() {
    return {
      login: ' ',
    }
  },
  mounted() {
    this.$store.commit('setEmail', this.$route.query.email);
  }
}
</script>

<style scoped>

</style>