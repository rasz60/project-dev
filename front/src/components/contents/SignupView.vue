<template lang="">
  <div id="blocked"></div>
  <div id="validEmail">
    <div class="validEmail-title">
      메일 인증
      <span id="validEmail-close" @click="validEmailClose">
        <font-awesome-icon :icon="['far', 'circle-xmark']" />
      </span>
    </div>
    <div class="validEmail-body">
      <p>
        발송 버튼을 누르면 {{ this.signup.mail.id }}@{{
          this.signup.mail.domain
        }}으로 인증 번호가 발송됩니다.
      </p>
      <div class="validCode-box">
        <p class="timer">00:00</p>
        <label for="validCode">인증번호 : </label>
        <input
          type="text"
          name="validCode"
          id="validCode"
          class="validEmail-input"
          v-model="validCodeIpt"
        />
        <a id="sendCode" @click="sendValidCode">발송</a>
      </div>
      <div class="validEmail-bottom">
        <a id="validSubmit" @click="validCodeChk">인증하기</a>
        <a id="validClose" @click="validEmailClose">취소하기</a>
      </div>
    </div>
  </div>
  <div class="signup-box">
    <form action="/signup/join" method="post" class="joinFrm">
      <div class="row">
        <label for="username">
          아이디
          <span class="required"
            ><font-awesome-icon :icon="['fas', 'asterisk']" />
          </span>
        </label>
        <div class="info-box">
          <span class="info">
            영문 소문자, 숫자, 특수문자(-,_)로 조합된 6~20자
          </span>
          <span id="usernameRex" class="info-chk unchkd">
            <font-awesome-icon :icon="['far', 'circle-check']" />
          </span>
        </div>
      </div>

      <div class="row">
        <input
          type="text"
          name="userName"
          id="username"
          placeholder="id"
          @keyup="usernameValid($event)"
          v-model="signup.username.last"
        />
        <a
          id="usernameChk"
          class="dupchk unchkd"
          @click="fnUsernameDupChk(this.signup.username.last)"
          >중복확인</a
        >
      </div>

      <div class="row">
        <label for="pw">
          비밀번호
          <span class="required"
            ><font-awesome-icon :icon="['fas', 'asterisk']" />
          </span>
        </label>
        <div class="info-box">
          <span class="info">
            영문 소문자, 숫자, 특수문자(`,~,!,@,$,%,*,#,^,?,&,(,),\,-,_,=,+)로
            조합된 8~20자
          </span>
          <span id="passwordRex" class="info-chk unchkd">
            <font-awesome-icon :icon="['far', 'circle-check']" />
          </span>
        </div>
      </div>

      <div class="row">
        <input
          type="password"
          name="password"
          id="pw"
          placeholder="password"
          @keyup="passwordValid($event)"
          v-model="signup.password"
        />
      </div>

      <div class="row">
        <label for="pw">
          비밀번호 확인
          <span class="required"
            ><font-awesome-icon :icon="['fas', 'asterisk']" />
          </span>
        </label>
        <div class="info-box">
          <span id="passwordChkRex" class="info-chk unchkd">
            <font-awesome-icon :icon="['far', 'circle-check']" />
          </span>
        </div>
      </div>

      <div class="row">
        <input
          type="password"
          name="passwordChk"
          id="pwChk"
          placeholder="password"
          @keyup="passwordChkValid()"
          @focus="passwordValueChk()"
        />
      </div>

      <div class="row">
        <label for="email">
          이메일
          <span class="required">
            <font-awesome-icon :icon="['fas', 'asterisk']" />
          </span>
        </label>
        <div class="info-box">
          <span id="emailRex" class="info-chk unchkd">
            <font-awesome-icon :icon="['far', 'circle-check']" />
          </span>
        </div>
      </div>

      <div class="row">
        <input
          type="text"
          name="emailId"
          id="emailId"
          placeholder="email_ID"
          v-model="signup.mail.id"
          @keyup="emailValid()"
        />
        &nbsp;<font-awesome-icon :icon="['fas', 'at']" />&nbsp;
        <select
          name="domain"
          id="emailDomain"
          @change="emailValid()"
          v-model="signup.mail.domain"
        >
          <option value="">--------------</option>
          <option value="gmail.com">gmail.com</option>
          <option value="naver.com">naver.com</option>
          <option value="hanmail.net">hanmail.net</option>
          <option value="daum.net">daum.net</option>
          <option value="kakao.com">kakao.com</option>
          <option value="hotmail.com">hotmail.com</option>
          <option value="nate.com">nate.com</option>
        </select>
        <input type="hidden" name="email" v-model="signup.mail.fullAddress" />
        <a
          id="emailChk"
          class="dupchk unchkd"
          @click="fnEmailDupChk(this.signup.mail)"
        >
          중복확인
        </a>
      </div>

      <div id="signup-button-box">
        <a class="fa fa-check" title="가입하기" @click="signupFormSubmit">
          <font-awesome-icon :icon="['fas', 'square-check']" />
        </a>

        <a class="fa fa-close" title="가입 취소" @click="cancle">
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
      signup: {
        username: {
          before: "",
          last: "",
        },
        password: "",
        mail: {
          beforeId: "",
          beforeDomain: "",
          id: "",
          domain: "",
          fullAddress: "",
        },
        phonenumber: {
          beforeNumber: "",
          phonenumber: "",
        },
      },
      validCode: "",
      validCodeIpt: "",
      validCodeTime: 0,
    };
  },
  methods: {
    ...mapMutations({
      fnUsernameDupChk: "fnUsernameDupChk",
      fnEmailDupChk: "fnEmailDupChk",
      fnPhoneNumberChk: "fnPhoneNumberChk",
    }),
    usernameValid(evt) {
      var chk = false;
      var value = evt.target.value.trim();

      var regExp = /[a-z0-9-_]{6,20}$/;
      chk = regExp.test(value);
      var regChk = document.querySelector("span#usernameRex");
      console.log(regExp, value, chk);
      if (chk) {
        regChk.className = "info-chk chkd";
        this.signup.username.last = value;
      } else {
        regChk.className = "info-chk unchkd";
      }

      if (this.signup.username.before != value) {
        var idDupChk = document.querySelector("a#usernameChk.chkd");

        if (idDupChk != null) {
          idDupChk.className = "unchkd";
          idDupChk.text = "중복확인";
        }

        this.signup.username.before = value;
      }
    },
    passwordValueChk() {
      var chk =
        document.querySelector("span#passwordRex").className.indexOf("unchkd") >
        0;
      if (chk) {
        alert("먼저 비밀번호를 형식에 맞게 입력해주세요.");
        document.querySelector("input#pw").focus();
      }
    },
    passwordValid(evt) {
      var value = evt.target.value.trim();
      var chk = false;
      var regExp =
        /(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
      chk = regExp.test(value);
      this.signup.password = value;
      if (chk) {
        document.querySelector("span#passwordRex").className = "info-chk chkd";
      } else {
        document.querySelector("span#passwordRex").className =
          "info-chk unchkd";
      }
      if (this.signup.password != "") {
        this.passwordChkValid();
      }
    },
    passwordChkValid() {
      var value = document.querySelector("input#pwChk").value.trim();
      var chkValue = this.signup.password;

      var passwordChk = document.querySelector("input#pwChk");
      var passwordChkRex = document.querySelector("span#passwordChkRex");

      if (value == chkValue) {
        passwordChkRex.className = "info-chk chkd";
        passwordChk.className = "chkd";
      } else {
        passwordChkRex.className = "info-chk unchkd";
        passwordChk.className = "unchkd";
      }
    },
    emailValid() {
      var mailId = document.querySelector("input#emailId").value.trim();
      var mailDomain = document.querySelector("select#emailDomain").value;
      if (
        mailId != this.signup.mail.beforeId ||
        mailDomain != this.signup.mail.beforeDomain
      ) {
        var emailDupChk = document.querySelector("a#emailChk.chkd");
        var emailRex = document.querySelector("span#emailRex.chkd");
        if (emailDupChk != null) {
          emailDupChk.className = "unchkd";
          emailDupChk.text = "중복확인";
          emailRex.className = "info-chk unchkd";
        }
      }
      this.signup.mail.beforeId = mailId;
      this.signup.mail.beforeDomain = mailDomain;
      this.signup.mail.id = mailId;
      this.signup.mail.domain = mailDomain;
      this.signup.mail.fullAddress = mailId + "@" + mailDomain;
    },
    signupFormSubmit() {
      var chk = this.confirmedChkd();

      if (chk.chk) {
        document.querySelector("div#blocked").style.display = "block";
        document.querySelector("div#validEmail").style.display = "block";
        document.querySelector("input#validCode").focus();
      } else {
        alert(chk.msg);
        document.querySelector(chk.ele).focus();
        return false;
      }
    },
    confirmedChkd() {
      var unchkd = document.querySelectorAll(".unchkd");
      var chk = unchkd.length > 0;

      var valid = {
        chk: !chk,
        ele: null,
        msg: "",
      };

      if (chk) {
        var target = unchkd[0].id;
        if (target == "usernameRex") {
          valid.msg = "아이디가 형식에 맞지 않습니다.";
          valid.ele = "input#username";
        } else if (target == "usernameChk") {
          valid.msg = "아이디 중복 여부를 체크해주세요.";
          valid.ele = "input#username";
        } else if (target == "passwordRex") {
          valid.msg = "비밀번호가 형식에 맞지 않습니다.";
          valid.ele = "input#pw";
        } else if (target == "passwordChkRex") {
          valid.msg =
            "비밀번호 확인란의 입력한 값이 비밀번호와 동일하지 않습니다.";
          valid.ele = "input#pwChk";
        } else if (target == "emailRex") {
          valid.msg = "이메일 주소 중복 여부를 체크해주세요.";
          valid.ele = "input#emailId";
        }
      }

      return valid;
    },
    validEmailClose() {
      document.querySelector("div#blocked").style.display = "none";
      document.querySelector("div#validEmail").style.display = "none";
      document.querySelector("input#validCode").value = "";
      this.validCode = "";

      this.clearTimer();
    },
    async sendValidCode() {
      var mailAddress = this.signup.mail.id + "@" + this.signup.mail.domain;
      await this.axios.get("/api/v1/emailValid/" + mailAddress).then((res) => {
        const jsonData = res.data;
        this.validCode = jsonData.token;
        this.setTimer(179);
        setTimeout(this.validCodeExpired, 180 * 1000);
      });
    },
    validCodeExpired() {
      this.validCode = "";
      alert(
        "인증번호 유효시간이 만료되었습니다.\n다시 인증번호를 발송해주세요."
      );
    },
    setTimer(time) {
      this.validCodeTime = setInterval(function () {
        if (time <= 0) {
          this.clearTimer();
        }
        var minutes = "0" + Math.trunc(time / 60);
        var seconds =
          time % 60 < 10 ? "0" + Math.trunc(time % 60) : Math.trunc(time % 60);
        var timerString = minutes + ":" + seconds;
        document.querySelector("p.timer").innerText = timerString;
        time--;
      }, 1000);
    },
    clearTimer() {
      document.querySelector("p.timer").innerText = "00:00";
      clearInterval(this.validCodeTime);
    },
    validCodeChk() {
      var validCode = this.validCode;
      if (validCode == "") {
        alert("인증 번호를 발송하지 않았거나, 만료된 인증번호입니다.");
        return false;
      } else {
        var validCodeIpt = window.btoa(this.validCodeIpt);
        if (validCode == validCodeIpt) {
          alert("메일 인증이 완료되었습니다.\n가입을 감사드립니다.");
          var frm = document.querySelector("form.joinFrm");
          frm.submit();
        } else {
          alert("인증번호를 다시 확인해주세요.");
        }
      }
    },
    cancle() {
      if (confirm("가입을 취소할까요?")) {
        location.href = "/";
      }
    },
  },
};
</script>
<style></style>
