<!-- TodoInput.vue -->
<template>
  <div class="add">
    <div class="main-input">
      <input
        type="text"
        class="add__input"
        placeholder="Enter your task here"
        v-model="newTodoItem"
        v-on:keypress.enter="addTodoItem"
        ref="taskInput"
      />
      <button class="add__buttonn" v-on:click="addTodoItem">
        <span class="blind">Add</span>
      </button>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      newTodoItem: "",
    };
  },
  computed: {
    ...mapGetters([
      "storedTodoItems",
      "storedTodoItemsCount",
      "storedMenuItems",
    ]),
  },
  methods: {
    addTodoItem() {
      // 중복되는 내용인 경우
      const oldItems = this.storedTodoItems;
      for (let i = 0; i < this.storedTodoItemsCount; i++) {
        if (oldItems[i].item === this.newTodoItem) {
          const text = "I think you've already had the task.";
          this.$emit("alertModal", text);
          return false;
        }
      }
      // 빈 내용인 경우
      if (this.newTodoItem === "") {
        const text = "The form is empty. Please note your task.";
        this.$emit("alertModal", text);
        this.clearInput();
        return false;
      }
      this.$store.commit("addOneItem", this.newTodoItem);
      this.clearInput();
      this.$refs.taskInput.focus();
    },
    clearInput() {
      this.newTodoItem = "";
    },
  },
};
</script>

<style lang="scss">
.add {
  position: relative;
  //max-width: $max-width;
  max-width: 720px;
  margin: 0 auto;

  .main-input {
    //@include animation(fadeShow, 800ms, 1, 900ms);
    -webkit-animation-delay: 900ms;
    -webkit-animation-duration: 800ms;
    -webkit-animation-name: fadeShow;
    -webkit-animation-fill-mode: both;
    -moz-animation-delay: 900ms;
    -moz-animation-duration: 800ms;
    -moz-animation-name: fadeShow;
    -moz-animation-fill-mode: both;
    -o-animation-delay: 900ms;
    -o-animation-duration: 800ms;
    -o-animation-name: fadeShow;
    -o-animation-fill-mode: both;
    animation-delay: 900ms;
    animation-duration: 800ms;
    animation-name: fadeShow;
    animation-fill-mode: both;
    animation-iteration-count: 1;
  }
}
</style>
