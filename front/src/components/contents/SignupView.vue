<template lang="">
  <div class="signup-box">
    <form action="/signup/join" method="post" class="joinFrm">
      <div>
        <label for="username">
          아이디
          <span class="required"
            ><font-awesome-icon :icon="['fas', 'asterisk']" />
          </span>
        </label>
      </div>

      <div>
        <input
          type="text"
          name="userName"
          id="username"
          placeholder="id"
          @keyup="usernameValid($event)"
          v-model="username.last"
        />
        <a id="usernameChk" class="chk" @click="usernameDupChk">중복확인</a>
      </div>

      <div>
        <label for="pw">
          비밀번호
          <span class="required"
            ><font-awesome-icon :icon="['fas', 'asterisk']" />
          </span>
        </label>
      </div>

      <div>
        <input
          type="password"
          name="password"
          id="pw"
          placeholder="password"
          @keyup="passwordValid($event)"
          v-model="password"
        />
      </div>

      <div>
        <label for="pw">
          비밀번호 확인
          <span class="required"
            ><font-awesome-icon :icon="['fas', 'asterisk']" />
          </span>
        </label>
      </div>

      <div>
        <input
          type="passwordChk"
          name="passwordChk"
          id="pwChk"
          placeholder="password"
          @keyup="passwordValid($event)"
          v-model="passwordChk"
        />
      </div>

      <div>
        <label for="email">
          이메일
          <span class="required"
            ><font-awesome-icon :icon="['fas', 'asterisk']" />
          </span>
        </label>
      </div>

      <div>
        <input
          type="text"
          name="email"
          id="email"
          placeholder="email_ID"
          v-model="mail.lastId"
          @keyup="emailValid()"
        />
        &nbsp;<font-awesome-icon :icon="['fas', 'at']" />&nbsp;
        <select name="domain" id="emailDomain" @change="emailValid()">
          <option value="0">--------------</option>
          <option value="google.com">google.com</option>
          <option value="naver.com">naver.com</option>
          <option value="hanmail.net">hanmail.net</option>
          <option value="daum.net">daum.net</option>
          <option value="kakao.com">kakao.com</option>
          <option value="hotmail.com">hotmail.com</option>
          <option value="nate.com">nate.com</option>
        </select>
        <a id="emailChk" class="chk" @click="emailDupChk()">중복확인</a>
      </div>

      <div>
        <label for="phoneNumber">
          휴대폰
          <span class="required"
            ><font-awesome-icon :icon="['fas', 'asterisk']" />
          </span>
        </label>
      </div>

      <div>
        <input
          type="text"
          name="phoneNumber"
          id="phoneNumber"
          placeholder="###-#####-####"
          @keyup="phoneNumberValid($event)"
        />
        <a id="phoneNumberChk" class="chk" @onclick="phoneNumberChk"
          >인증하기</a
        >
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
<script>
import { mapMutations } from "vuex";
export default {
  name: "signUp",
  data() {
    return {
      username: {
        before: "",
        last: "",
      },
      password: "",
      passwordChk: "",
      mail: {
        beforeId: "",
        beforeDomain: "",
        lastId: "",
        lastDomain: "",
        mail: "",
      },
      usernameChk: false,
    };
  },
  methods: {
    ...mapMutations({
      fnUsernameDupChk: "fnUsernameDupChk",
      fnEmailDupChk: "fnEmailDupChk",
      fnPhoneNumberChk: "fnPhoneNumberChk",
    }),
    usernameValid(evt) {
      var value = evt.target.value.trim();
      this.username.last = value;

      if (this.username.before != value) {
        var idDupChk = document.querySelector("a#usernameChk.chkd");

        if (idDupChk != null) {
          idDupChk.className = "chk";
          idDupChk.text = "중복확인";
        }

        this.username.before = value;
      }
    },
    passwordValid(evt) {
      var target = evt.target.name;
      var value = evt.target.value.trim();

      var orgVal = this.password;
      var chkVal = this.passwordChk;
      console.log(target);

      if (target == "password") {
        orgVal = value;
      } else {
        chkVal = value;
      }

      var chk = document.querySelector("input#pwChk");
      if (orgVal != chkVal) {
        chk.className = "unchk";
      } else {
        chk.className = "chkd";
      }
    },
    emailValid() {
      var mailId = document.querySelector("input#email").value.trim();
      var mailDomain = document.querySelector("select#emailDomain").value;

      if (
        mailId != this.mail.beforeId ||
        mailDomain != this.mail.beforeDomain
      ) {
        var emailDupChk = document.querySelector("a#emailChk.chkd");
        if (emailDupChk != null) {
          emailDupChk.className = "chk";
          emailDupChk.text = "중복확인";
        }
      }

      this.mail.lastId = mailId;
      this.mail.lastDomain = mailDomain;
    },
    async usernameDupChk() {
      var username = this.username.last;

      if (username == "") {
        document.querySelector("input#username").focus();
        alert("id를 입력해주세요");
        return false;
      }
      //2024.05.03 api async 처리
    },
    emailDupChk() {
      var mailId = this.mail.lastId;
      var mailDomain = this.mail.lastDomain;

      if (mailId == "" || mailDomain == "0") {
        alert("메일을 입력해주세요.");

        if (mailId == "") {
          document.querySelector("input#email").focus();
        } else {
          document.querySelector("select#emailDomain").focus();
        }
        return false;
      }
      this.fnEmailDupChk(mailId + "@" + mailDomain);
    },
  },
};
</script>
<style></style>
