<script setup>
import { RouterView } from "vue-router";
</script>

<template>
  <div id="contents-div">
    <div id="topMenu">
      <ul>
        <li class="loginUser" v-if="this.loginchk"></li>
        <li>
          <a class="fa" href="/signup" v-if="!this.loginchk">
            <font-awesome-icon :icon="['fas', 'user-plus']" />
          </a>
        </li>
        <li>
          <a class="fa" href="/formLogin" v-if="!this.loginchk">
            <font-awesome-icon :icon="['fas', 'right-to-bracket']" />
          </a>
        </li>
        <li>
          <a class="fa" href="/logout" v-if="this.loginchk">
            <font-awesome-icon :icon="['fas', 'right-from-bracket']" />
          </a>
        </li>
      </ul>
    </div>
    <div id="content">
      <RouterView />
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loginChk: false,
      loginUser: null,
    };
  },
  methods: {
    async getLoginInfo() {
      await this.axios.get("/api/v1/loginInfo/").then((res) => {
        const jsonData = res.data;

        if (jsonData != null) {
          this.loginChk = true;
          this.loginUser = jsonData.username;
        }
      });
    },
  },
};
</script>
<style></style>
