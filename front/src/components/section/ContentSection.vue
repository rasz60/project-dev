<script setup>
import { RouterView } from "vue-router";
</script>

<template>
  <div id="contents-div">
    <div id="topMenu">
      <ul>
        <li>1</li>
        <li>2</li>
        <li>3</li>
      </ul>
    </div>
    <div id="content">
      <RouterView />
    </div>
  </div>
</template>

<script></script>
