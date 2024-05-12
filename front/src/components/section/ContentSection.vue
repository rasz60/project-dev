<script setup>
import { RouterView } from "vue-router";
</script>

<template>
  <div id="contents-div">
    <div id="topMenu">
      <ul>
        <li>
          <a class="fa" href="/signup" v-if="!chk">
            <font-awesome-icon :icon="['fas', 'user-plus']" />
          </a>
        </li>
        <li>
          <a class="fa" href="/formLogin" v-if="!chk">
            <font-awesome-icon :icon="['fas', 'right-to-bracket']" />
          </a>
        </li>
        <li class="loginUser" v-if="chk">
          {{ loginUser.username }}님, 방문을 환영합니다.
        </li>
        <li>
          <a class="fa" href="/userInfo" v-if="chk">
            <font-awesome-icon :icon="['far', 'circle-user']" />
          </a>
        </li>
        <li>
          <a class="fa" href="#" v-if="chk">
            <font-awesome-icon :icon="['far', 'bell']" />
          </a>
        </li>
        <li>
          <a class="fa" href="/logout" v-if="chk">
            <font-awesome-icon :icon="['fas', 'right-from-bracket']" />
          </a>
        </li>
      </ul>
    </div>
    <div id="content">
      <RouterView :loginUser="loginUser" />
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  data() {
    return {
      loginUser: {
        username: "",
        email: "",
        picture: "",
      },
    };
  },
  computed: {
    ...mapGetters(["storedLoginInfo"]),
    chk() {
      var chk = this.storedLoginInfo.username != null;
      if (chk) {
        this.loginUser.username = this.storedLoginInfo.username;
        this.loginUser.email = this.storedLoginInfo.email;
        this.loginUser.picture = this.storedLoginInfo.picture;
      }
      return chk;
    },
  },
  methods: {
    searchBoxCtrl(type) {
      var searchBox = document.querySelector("div.search-box");
      var searchLabel = document.querySelector("label.searchIpt");
      if (type == "f") {
        searchBox.style.backgroundColor = "#5F5F5F";

        searchLabel.style.backgroundColor = "transparent";
        searchLabel.style.color = "white";
        searchLabel.style.border = "none";
      } else {
        searchBox.style.backgroundColor = "white";

        searchLabel.style.backgroundColor = "transparent";
        searchLabel.style.color = "#5F5F5F";
        searchLabel.style.border = "2px solid #5F5F5F";
      }
    },
  },
};
</script>
<style></style>
