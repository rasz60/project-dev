<template lang="">
  <div class="login-box">
    <div>
      <label for="username">아이디</label>
    </div>

    <div>
      <input type="text" name="userName" id="userName" />
    </div>

    <div>
      <label for="pw">비밀번호</label>
    </div>

    <div>
      <input type="password" name="password" id="pw" />
    </div>

    <div id="login-button-box">
      <!--
      <a class="fa" href="/oauth2/authorization/google">
        <font-awesome-icon :icon="['fab', 'google']" />
      </a>
      -->
      <a class="fa" href="#" v-on:click="loginFrmSubmit()">
        <font-awesome-icon :icon="['fas', 'right-to-bracket']" />
      </a>
    </div>
    error : {{ error }}
  </div>
</template>

<script>
import { mapMutations } from "vuex";

var url = window.location.href;

if (url.indexOf("?") > 0) {
  var query = url.substring(url.indexOf("?") + 1);

  if (query == "error") {
    alert("로그인 정보를 다시 확인해주세요.");
  }
}

export default {
  name: "loginFrmsubmit",
  data() {
    return {
      loginSuccess: false,
      loginError: false,
      user: "",
      password: "",
      error: false,
    };
  },
  methods: {
    ...mapMutations({
      loginFrmSubmit: "loginFrmSubmit",
    }),
  },
};
</script>
<style></style>
