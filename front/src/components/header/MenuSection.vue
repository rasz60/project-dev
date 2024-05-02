<template lang="">
  <div className="header__menu">
    <ul className="menu">
      <li v-for="menuItem in this.storedMenuItems" v-bind:key="menuItem.title">
        <router-link
          v-bind:to="menuItem.url"
          v-bind:title="menuItem.descriptions"
          >{{ menuItem.title }}</router-link
        >
      </li>
    </ul>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "menuItems",
  computed: {
    ...mapGetters(["storedMenuItems"]),
  },
};
</script>
