<script setup>
import LogoSection from "../header/LogoSection";
import MenuSection from "../header/MenuSection";
</script>

<template lang="">
  <header id="header" role="banner">
    <LogoSection />
    <MenuSection />
  </header>
</template>
<script>
import { mapGetters } from "vuex";

export default {
  name: "loginInfo",
  computed: {
    ...mapGetters(["storedLoginInfo"]),
  },
  mounted() {
    this.$store.commit("storedLoginInfo");
  },
};
</script>
<style lang=""></style>
