const storedTodoItems = (state) => {
  return state.todoItems;
};
const storedName = (state) => {
  return state.userName;
};
const storedTodoItemsCount = (state, getters) => {
  return getters.storedTodoItems.length;
};

// menu 불러오기
const storedMenuItems = (state) => {
  return state.menuItems;
};

const storedLoginInfo = (state) => {
  return state.loginInfo;
};

const storedUserInfo = (state) => {
  return state.userInfo;
};

export {
  storedTodoItems,
  storedName,
  storedTodoItemsCount,
  storedMenuItems,
  storedLoginInfo,
  storedUserInfo,
};
