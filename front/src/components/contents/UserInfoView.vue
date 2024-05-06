<template lang="">
  <div class="userInfo-box text">
    <div class="row">
      <label for="ro_username">아이디</label>
    </div>
    <div class="row">
      <input
        type="text"
        id="ro_username"
        v-model="userInfo.username"
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
      <input type="text" id="ro_emailId" v-model="userInfo.email" readonly />
    </div>

    <div id="userInfo-button-box" class="row">
      <a id="userInfoModify" class="modify" @click="modifyInfo()">수정하기</a>
      <a id="userInfoModify" class="modify" @click="signout()">탈퇴하기</a>
    </div>
  </div>

  <div class="userInfo-box modify">
    <div class="row">
      <label for="ro_username">아이디</label>
    </div>
    <div class="row">
      <input
        type="text"
        id="ro_username"
        v-model="userInfo.username"
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
      <input type="text" id="ro_emailId" v-model="userInfo.email" readonly />
    </div>

    <div id="userInfo-button-box" class="row">
      <a id="userInfoModify" class="modify" @click="modifyInfoSubmit()">
        저장하기
      </a>
      <a id="userInfoModify" class="modify" @click="modifyInfoCancle()">
        취소하기
      </a>
    </div>
  </div>
</template>
<script>
export default {
  props: ["loginUser"],
  data() {
    return {
      userInfo: this.loginUser,
      emailId: "",
      emailDomain: "",
    };
  },
  methods: {
    emailChk() {
      var email = this.loginUser.email;
      console.log(email);
      //this.userInfo.emailId = email.substring(0, email.indexOf("@"));
      return true;
    },
    modifyInfo() {},
    async signout() {
      if (confirm("모든 정보를 삭제하고 탈퇴할까요?")) {
        await this.axios
          .get("/api/v1/signout/" + this.userInfo.username)
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
  },
};
</script>
<style></style>
