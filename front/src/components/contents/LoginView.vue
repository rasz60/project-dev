<script setup></script>

<template lang="">
  <div class="login-box">
    <form action="/loginProc">
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
        <a class="fa" href="">
          <font-awesome-icon :icon="['fab', 'google']" />
        </a>
        <a class="fa" href="/loginProc">
          <font-awesome-icon :icon="['fas', 'right-to-bracket']" />
        </a>
      </div>
    </form>
  </div>
</template>

<script></script>
<style></style>
