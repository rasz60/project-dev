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
  var frm = document.querySelector("form.login-box");
  frm.submit();
};

const storedLoginInfo = () => {
  storage.getLoginInfo();
};

const fnUsernameDupChk = async (state, val) => {
  var chk = false;
  await axios3.get("/api/v1/usernameDupChk/" + val).then((res) => {
    chk = res.data;
  });
  return chk;
};
const fnEmailDupChk = (val) => {
  var chk = false;

  axios3.get("/api/v1/emailDupChk/" + val).then((res) => {
    const jsonData = res.data;
    if (jsonData != null) {
      chk = true;
    }
  });
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
  storedLoginInfo,
  fnUsernameDupChk,
  fnEmailDupChk,
  fnPhoneNumberChk,
};
