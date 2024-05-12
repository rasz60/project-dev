export default () => {
  const now = new Date();
  const month = now.getMonth() + 1;
  const date = now.getDate();
  const weekList = new Array(
    "Sun.",
    "Mon.",
    "Tue.",
    "Wed.",
    "Thu.",
    "Fri.",
    "Sat."
  );
  const week = weekList[now.getDay()];
  const time = now.getTime();
  const hour = now.getHours();
  let daytime = "";

  if (hour < 12) {
    daytime = "morning";
  } else if (hour < 18) {
    daytime = "afternoon";
  } else {
    daytime = "evening";
  }

  const dateInfo = {
    month,
    date,
    week,
    time,
    daytime,
  };
  return dateInfo;
};

import getDate from "./../../assets/common/getDate";
import axios3 from "axios";
import storage from "@/store/modules/storage";

// 아이템 하나 추가
const addOneItem = async (state, todoItem) => {
  /* 서버 통신 */
  var jsonValue = {
    item: todoItem,
    date: `${getDate().date} ${getDate().week}`,
    time: getDate().time,
    completed: false,
  };

  await axios3
    .post("/api/v1/todos/save", JSON.stringify(jsonValue))
    .then((res) => {
      if (res.data == "ok") {
        storage.fetch(state.todoOldestOrder);
      } else {
        alert("등록 실패");
      }
    });
};
// 아이템 하나 삭제
const removeOneItem = (state, payload) => {
  /* 서버 통신 */
  axios3.put("/api/v1/todos/delete/" + payload.todoItem.id).then((res) => {
    if (res.data == "ok") {
      storage.fetch(state.todoOldestOrder);
    } else {
      alert("삭제 실패");
    }
  });
};
// 아이템 하나 완료 토글
const toggleOneItem = (state, payload) => {
  /* 서버 통신 */
  var jsonValue = {
    id: payload.todoItem.id,
    completed: !payload.todoItem.completed,
  };

  axios3
    .put("api/v1/todos/" + payload.todoItem.id, JSON.stringify(jsonValue))
    .then((res) => {
      if (res.data == "ok") {
        storage.fetch(state.todoOldestOrder);
      } else {
        alert("업데이트 실패");
      }
    });
};
// 모든 아이템 삭제
const clearAllItem = (state) => {
  var todoItems = state.todoItems;
  if (todoItems.length > 0) {
    axios3.put("api/v1/todos/clear").then((res) => {
      if (res.data == "ok") {
        storage.fetch(state.todoOldestOrder);
      } else {
        alert("클리어 실패");
      }
    });
  }
};
// 최신순 정렬
const sortTodoLatest = (state) => {
  state.todoOldestOrder = false;

  storage.fetch(state.todoOldestOrder);
};
// 오래된 순 정렬
const sortTodoOldest = (state) => {
  state.todoOldestOrder = true;

  storage.fetch(state.todoOldestOrder);
};
// 사용자 이름 추가
const setUserName = (state, userName) => {
  localStorage.setItem("userName", userName);
  state.userName = userName;
};

const storedMenuItems = () => {
  storage.fetchMenus();
};

const loginFrmSubmit = () => {
  var frm = document.querySelector("form#login-frm");
  frm.submit();
};

const fnUsernameDupChk = async (state, val) => {
  console.log("----------usernameDupChk() : start");
  var chk = val == "";
  if (chk) {
    document.querySelector("input#username").focus();
    alert("id를 입력해주세요");
    return false;
  }

  var rexChk = document.querySelector("span#usernameRex").className;
  chk = rexChk.indexOf("unchkd") > 0;
  if (chk) {
    alert("허용되지 않는 아이디 형식입니다.");
    return false;
  }

  await axios3.get("/api/v1/usernameDupChk/" + val).then((res) => {
    chk = res.data;
  });
  var idDupChk = document.querySelector("a#usernameChk");
  if (chk) {
    alert("이미 사용 중인 아이디입니다.");
    document.querySelector("input#username").focus();
    idDupChk.className = "unchkd";
  } else {
    alert("사용할 수 있는 아이디입니다.");
    idDupChk.className = "chkd";
    idDupChk.text = "중복확인완료";
  }

  return chk;
};
const fnEmailDupChk = async (state, mail) => {
  var chk = false;
  var mailId = mail.id;
  var mailDomain = mail.domain;

  var emailDupChk = document.querySelector("a#emailChk");

  if (emailDupChk.className.indexOf("unchkd") >= 0) {
    if (mailId == "" || mailDomain == "" || mailDomain == "0") {
      alert("메일을 입력해주세요.");

      if (mailId == "") {
        document.querySelector("input#email").focus();
      } else {
        document.querySelector("select#emailDomain").focus();
      }
      return false;
    }

    await axios3
      .get("/api/v1/emailDupChk/" + mailId + "@" + mailDomain)
      .then((res) => {
        chk = res.data;
      });

    var emailRex = document.querySelector("span#emailRex");
    if (chk) {
      alert("이미 사용 중인 메일 주소입니다.");
      document.querySelector("input#email").focus();
      emailDupChk.className = "unchkd";
      emailRex.className = "info-chk unchkd";
    } else {
      alert("가입되지 않은 메일 주소입니다.");
      emailDupChk.className = "chkd";
      emailDupChk.text = "중복확인완료";
      emailRex.className = "info-chk chkd";
    }

    console.log(mailId + "@" + mailDomain + ":" + chk);
  }
  return chk;
};

const fnPhoneNumberChk = (val) => {
  var chk = false;

  axios3.get("/api/v1/phoneDupChk/" + val).then((res) => {
    const jsonData = res.data;

    if (jsonData != null) {
      chk = true;
    }
  });
  return chk;
};

const storedLoginInfo = () => {
  storage.getLoginInfo();
};
export {
  addOneItem,
  removeOneItem,
  toggleOneItem,
  clearAllItem,
  sortTodoLatest,
  sortTodoOldest,
  setUserName,
  storedMenuItems,
  loginFrmSubmit,
  fnUsernameDupChk,
  fnEmailDupChk,
  fnPhoneNumberChk,
  storedLoginInfo,
};
