<script setup></script>

<template lang="">
  <div class="login-box">
    <form action="/loginProc" id="loginForm" method="post">
      <div>
        <label for="username">아이디</label>
      </div>

      <div>
        <input type="text" name="username" id="username" />
      </div>

      <div>
        <label for="pw">비밀번호</label>
      </div>

      <div>
        <input type="password" name="password" id="pw" />
      </div>

      <div id="login-button-box">
        <a class="fa" href="/oauth2/authorization/google">
          <font-awesome-icon :icon="['fab', 'google']" />
        </a>
        <a class="fa" href="#" v-on:click="loginFrmSubmit()">
          <font-awesome-icon :icon="['fas', 'right-to-bracket']" />
        </a>
      </div>
    </form>
  </div>
</template>

<script>
import { mapMutations } from "vuex";

export default {
  name: "loginFrmsubmit",
  methods: {
    ...mapMutations({
      loginFrmSubmit: "loginFrmSubmit",
    }),
  },
};
</script>
<style></style>
