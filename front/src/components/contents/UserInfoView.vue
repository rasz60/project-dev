<template lang="">
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
      <input type="password" name="pw" id="pw" v-model="modify.newPassword" />
    </div>

    <div class="row new-pw">
      <label for="pw">새로운 비밀번호 확인</label>
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
      <input type="password" name="pwChk" id="pwChk" />
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
        @click="fnEmailDupChk(this.modify.userInfo.mail)"
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
    };
  },
  methods: {
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
      await this.axios
        .get(
          "/api/v1/passwordChk/" +
            this.info.username +
            "/" +
            this.modify.currPassword
        )
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
            console.log(newPw);
            for (var i = 0; i < newPw.length; i++) {
              newPw[i].className = "row";
            }
          } else {
            alert("현재 비밀번호와 일치하지 않습니다.");
            return false;
          }
        });
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
    },
  },
};
</script>
<style></style>
