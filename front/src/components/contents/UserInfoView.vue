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
        발송 버튼을 누르면 {{ this.modify.email }}으로 인증 번호가 발송됩니다.
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

  <!-- text -->
  <div class="userInfo-box text">
    <div class="row">
      <label for="txt_username">아이디</label>
    </div>
    <div class="row">
      <input
        type="text"
        id="txt_username"
        v-model="info.userInfo.username"
        readonly
      />
    </div>

    <div class="row">
      <label for="ro_pw">비밀번호</label>
    </div>
    <div class="row ro">
      <input
        type="password"
        name="ro_pw"
        id="ro_pw"
        value="00000000"
        readonly
      />
    </div>

    <div class="row">
      <label for="ro_emailId">이메일</label>
    </div>
    <div class="row ro">
      <input
        type="text"
        id="ro_emailId"
        v-model="info.userInfo.email"
        readonly
      />
    </div>

    <div id="userInfo-button-box" class="row">
      <a id="userInfoModify" class="modify" @click="modifyInfo(0)">수정하기</a>
      <a id="userInfoModify" class="modify" @click="signout()">탈퇴하기</a>
    </div>
  </div>

  <!-- modify -->
  <div class="userInfo-box modify">
    <div class="row">
      <label for="username">아이디</label>
    </div>
    <div class="row">
      <input
        type="text"
        id="username"
        v-model="info.userInfo.username"
        readonly
      />
    </div>

    <div class="row">
      <label for="pw">현재 비밀번호</label>
      <div class="info-box">
        <span id="passwordChk" class="info-chk unchkd">
          <font-awesome-icon :icon="['far', 'circle-check']" />
        </span>
      </div>
    </div>
    <div class="row">
      <input
        type="password"
        name="prevPw"
        id="prevPw"
        placeholder="비밀번호를 변경하려면 현재 비밀번호를 입력해주세요."
        v-model="modify.currPassword"
      />
      <a id="passwordChk" class="dupchk unchkd" @click="passwordChk">
        변경하기
      </a>
    </div>

    <div class="row new-pw">
      <label for="pw">새로운 비밀번호</label>
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
    <div class="row new-pw">
      <input
        type="password"
        name="pw"
        id="pw"
        v-model="modify.newPassword"
        @keyup="passwordValid($event)"
      />
    </div>

    <div class="row new-pw">
      <label for="pwChk">새로운 비밀번호 확인</label>
      <div class="info-box">
        <span class="info">
          영문 소문자, 숫자, 특수문자(`,~,!,@,$,%,*,#,^,?,&,(,),\,-,_,=,+)로
          조합된 8~20자
        </span>
        <span id="passwordChkRex" class="info-chk unchkd">
          <font-awesome-icon :icon="['far', 'circle-check']" />
        </span>
      </div>
    </div>
    <div class="row new-pw">
      <input
        type="password"
        name="pwChk"
        id="pwChk"
        @focus="passwordValueChk"
        @keyup="passwordChkValid"
      />
    </div>

    <div class="row">
      <label for="emailId">이메일</label>
      <div class="info-box">
        <span id="emailRex" class="info-chk chkd">
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
        v-model="modify.emailId"
        @keyup="modifyEmail()"
      />
      &nbsp;<font-awesome-icon :icon="['fas', 'at']" />&nbsp;
      <select
        name="domain"
        id="emailDomain"
        v-model="modify.emailDomain"
        @change="modifyEmail()"
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
      <input type="hidden" name="email" v-model="modify.email" />
      <a
        id="emailChk"
        class="dupchk chkd"
        @click="fnEmailDupChk(this.modify.email)"
        >중복확인</a
      >
    </div>

    <div id="userInfo-button-box" class="row">
      <a id="userInfoModify" class="modify" @click="modifyInfoSave()">
        저장하기
      </a>
      <a id="userInfoModify" class="modify" @click="modifyInfo(1)">취소하기</a>
      <a id="userInfoModify" class="modify" @click="signout()">탈퇴하기</a>
    </div>
  </div>
</template>
<script>
import { mapMutations } from "vuex";

export default {
  props: ["loginUser"],
  data() {
    return {
      info: {
        userInfo: this.loginUser,
      },
      modify: {
        email: "",
        currPassword: "",
        newPassword: "",
        emailId: "",
        emailDomain: "",
        chkdEmailId: "",
        chkdEmailDomain: "",
        emailDupChk: false,
        pwModiFlag: false,
        emModiFlag: false,
      },

      validCodeIpt: "",
    };
  },
  methods: {
    ...mapMutations({
      fnEmailDupChk: "fnEmailDupChk",
    }),
    modifyInfo(type) {
      var textBox = document.querySelector("div.userInfo-box.text");
      var modiBox = document.querySelector("div.userInfo-box.modify");
      if (type == 0) {
        textBox.style.display = "none";
        modiBox.style.display = "block";
        var email = this.info.userInfo.email;
        this.modify.email = email;
        this.modify.emailId = email.substring(0, email.indexOf("@"));
        this.modify.emailDomain = email.substring(email.indexOf("@") + 1);
      } else {
        textBox.style.display = "block";
        modiBox.style.display = "none";
      }
    },
    async signout() {
      if (confirm("모든 정보를 삭제하고 탈퇴할까요?")) {
        await this.axios
          .delete("/api/v1/signout/" + this.info.userInfo.username)
          .then((res) => {
            const jsonData = res.data;
            if (jsonData.resultCode == 200) {
              alert("회원 탈퇴가 완료되었습니다.");
              location.href = "/logout";
            } else {
              alert(
                "회원 탈퇴에 실패했습니다.\n다시 시도해주시고, 지속적으로 실패할 경우 관리자에게 문의해주세요."
              );
              return false;
            }
          });
      }
    },
    async passwordChk() {
      var param = {
        username: this.info.userInfo.username,
        password: this.modify.currPassword,
      };

      await this.axios
        .post("/api/v1/passwordChk", JSON.stringify(param))
        .then((res) => {
          var resCode = res.data;

          if (resCode.resultCode == 200) {
            var currPw = document.querySelector("input#prevPw");
            currPw.readOnly = "true";

            var passwordChkSpan = document.querySelector("span#passwordChk");
            var passwordChkA = document.querySelector("a#passwordChk");

            passwordChkSpan.className = "info-chk chkd";

            passwordChkA.className = "dupchk chkd";
            passwordChkA.innerText = "확인완료";

            var newPw = document.querySelectorAll("div.new-pw");

            for (var i = 0; i < newPw.length; i++) {
              newPw[i].className = "row";
            }
            this.modify.pwModiFlag = true;
          } else {
            alert("현재 비밀번호와 일치하지 않습니다.");
            return false;
          }
        });
    },
    passwordValueChk() {
      var chk =
        document.querySelector("span#passwordRex").className.indexOf("unchkd") >
        0;
      if (chk) {
        alert("먼저 새로운 비밀번호를 형식에 맞게 입력해주세요.");
        document.querySelector("input#pw").focus();
      }
    },
    passwordValid(evt) {
      var value = evt.target.value.trim();
      var chk = false;
      var regExp =
        /(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
      chk = regExp.test(value);
      this.modify.newPassword = value;
      if (chk) {
        document.querySelector("span#passwordRex").className = "info-chk chkd";
      } else {
        document.querySelector("span#passwordRex").className =
          "info-chk unchkd";
      }
      if (this.modify.newPassword != "") {
        this.passwordChkValid();
      }
    },
    passwordChkValid() {
      var value = document.querySelector("input#pwChk").value.trim();
      var chkValue = this.modify.newPassword;

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
    modifyEmail() {
      if (this.modify.chkdEmailId == "" || this.modify.chkdEmailDomain == "") {
        var email = this.info.userInfo.email;
        this.modify.chkdEmailId = email.substring(0, email.indexOf("@"));
        this.modify.chkdEmailDomain = email.substring(email.indexOf("@") + 1);
      }

      var chkdId = this.modify.chkdEmailId;
      var chkdDomain = this.modify.chkdEmailDomain;

      var newId = this.modify.emailId;
      var newDomain = this.modify.emailDomain;

      this.modify.email = newId + "@" + newDomain;

      var emailRex = document.querySelector("span#emailRex");
      var emailChk = document.querySelector("a#emailChk");

      if (chkdId != newId || chkdDomain != newDomain) {
        emailRex.className = "info-chk unchkd";

        emailChk.className = "dupchk unchkd";
        emailChk.innerText = "중복확인";
      } else {
        emailRex.className = "info-chk chkd";

        emailChk.className = "dupchk chkd";
        emailChk.innerText = "확인완료";
      }

      var currMail = this.info.userInfo.email;
      var newMail = this.modify.email;

      this.modify.emModiFlag = currMail != newMail;
    },
    modifyInfoSave() {
      var pwModiFlag = this.modify.pwModiFlag;
      var newPassword = this.modify.newPassword;

      if (pwModiFlag && newPassword != "") {
        var passwordRex = document.querySelector("span#passwordRex");
        var passwordChkRex = document.querySelector("span#passwordChkRex");
        if (passwordRex.className.indexOf("unchkd") > 0) {
          alert("새로운 비밀번호를 형식에 맞게 입력해주세요.");
          document.querySelector("input#pw").focus();
          return false;
        }

        if (passwordChkRex.className.indexOf("unchkd") > 0) {
          alert("새로운 비밀번호 확인 입력 값을 다시 확인해주세요.");
          document.querySelector("input#pwChk").focus();
          return false;
        }
      }

      this.modify.pwModiFlag = pwModiFlag && newPassword != "";

      var emModiFlag = this.modify.emModiFlag;
      var newMail = this.modify.email;

      if (emModiFlag && newMail != "") {
        var emailChk = document.querySelector("a#emailChk");
        if (emailChk.className.indexOf("unchkd") > 0) {
          alert("변경할 이메일 주소를 중복 확인해주세요.");
          return false;
        }
        document.querySelector("div#blocked").style.display = "block";
        document.querySelector("div#validEmail").style.display = "block";
        document.querySelector("input#validCode").focus();
      } else {
        this.fnSave();
      }
    },
    validEmailClose() {
      document.querySelector("div#blocked").style.display = "none";
      document.querySelector("div#validEmail").style.display = "none";
      document.querySelector("input#validCode").value = "";
      this.validCode = "";

      this.clearTimer();
    },
    async sendValidCode() {
      var mailAddress = this.modify.emailId + "@" + this.modify.emailDomain;
      await this.axios.get("/api/v1/emailValid/" + mailAddress).then((res) => {
        const jsonData = res.data;
        this.validCode = jsonData.token;
        this.setTimer(179);
        setTimeout(this.validCodeExpired, 179 * 1000);
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
          alert("메일 인증이 완료되었습니다.");
          this.fnSave();
        } else {
          alert("인증번호를 다시 확인해주세요.");
          return false;
        }
      }
    },
    async fnSave() {
      var param = {
        newPassword: this.modify.pwModiFlag ? this.modify.newPassword : "",
        newEmail: this.modify.emModiFlag ? this.modify.email : "",
      };

      await this.axios
        .post("/api/v1/modifyInfo", JSON.stringify(param))
        .then((res) => {
          var jsonResult = res.data;
          if (jsonResult.resultCode == 200) {
            alert("정보 수정이 완료되었습니다.");
            location.href = "/userInfo";
          } else {
            alert("정보 수정에 실패하였습니다.");
            return false;
          }
        });
    },
  },
};
</script>
<style></style>
