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

export { storedTodoItems, storedName, storedTodoItemsCount, storedMenuItems };
