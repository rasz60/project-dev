<script setup></script>

<template lang="">
  <div class="signup-box">
    <form action="/signup/join" method="post" class="joinFrm">
      <div>
        <label for="username">아이디</label>
      </div>

      <div>
        <input type="text" name="userName" id="username" placeholder="id" />
        <a class="usernameChk">중복확인</a>
      </div>

      <div>
        <label for="pw">비밀번호</label>
      </div>

      <div>
        <input type="password" name="password" id="pw" placeholder="password" />
      </div>

      <div>
        <label for="pw">비밀번호 확인</label>
      </div>

      <div>
        <input
          type="passwordChk"
          name="passwordChk"
          id="pwChk"
          placeholder="password"
        />
      </div>

      <div>
        <label for="email">이메일</label>
      </div>

      <div>
        <input
          type="email"
          name="email"
          id="email"
          placeholder="email_ID@domain.com"
        />
      </div>

      <div id="signup-button-box">
        <a class="fa fa-check" title="가입하기">
          <font-awesome-icon :icon="['fas', 'square-check']" />
        </a>

        <a class="fa fa-close" href="/" title="가입 취소">
          <font-awesome-icon :icon="['fas', 'square-xmark']" />
        </a>
      </div>
    </form>
  </div>
</template>
<style></style>
