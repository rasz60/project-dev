<script setup>
import { RouterView } from "vue-router";
</script>

<template>
  <div id="contents-div">
    <div id="topMenu">
      <ul>
        <li>
          <a class="fa" href="/signup">
            <font-awesome-icon :icon="['fas', 'user-plus']" />
          </a>
        </li>
        <li>
          <a class="fa" href="/login">
            <font-awesome-icon :icon="['fas', 'right-to-bracket']" />
          </a>
        </li>
        <li>
          <a class="fa" href="/logout">
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

<script></script>
<style></style>
