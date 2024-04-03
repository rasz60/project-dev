<script setup>
import { headerMenus } from "../../data/header";
</script>

<template lang="">
  <div className="header__menu">
    <ul className="menu">
      <li v-for="(nav, key) in headerMenus" :key="key">
        <a :href="nav.url">{{ nav.title }}</a>
      </li>
    </ul>
  </div>
</template>

<script></script>
