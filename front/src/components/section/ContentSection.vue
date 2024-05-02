<script setup>
import { RouterView } from "vue-router";
</script>

<template>
  <div id="contents-div">
    <div id="topMenu">
      <ul>
        <li>
          <a class="fa" href="/signup" v-if="loginChk">
            <font-awesome-icon :icon="['fas', 'user-plus']" />
          </a>
        </li>
        <li>
          <a class="fa" href="/formLogin" v-if="loginChk">
            <font-awesome-icon :icon="['fas', 'right-to-bracket']" />
          </a>
        </li>
        <li>
          <a class="fa" href="/logout" v-if="!loginChk">
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
import { mapGetters } from "vuex";

export default {
  computed: {
    ...mapGetters(["storedLoginInfo"]),
    loginChk() {
      return this.storedLoginInfo.username == null;
    },
  },
  methods: {},
};
</script>
<style></style>
