<script setup>
import HeaderSection from "./components/section/HeaderSection";
import ContentSection from "./components/section/ContentSection";
</script>

<template lang="">
  <HeaderSection />
  <ContentSection />
</template>
<script>
export default {};
</script>
<style lang=""></style>
<!--
<template>
  <div id="app">
    <div class="top">
      <TodoHeader />
      <div v-if="this.storedName">
        <TodoTitle />
        <TodoInput v-on:alertModal="showModal" @reload="reload" />
      </div>
      <div v-else>
        <TodoHello />
      </div>
    </div>
    <div class="body">
      <TodoController />
      <TodoList ref="list" />
    </div>
    <TodoFooter />
  </div>
  <TodoModal v-show="modalVisible" v-on:close="modalVisible = false">
    <template v-slot:modal-text>{{ modalText }}</template>
  </TodoModal>
</template>

<script>
import TodoHeader from "./components/TodoHeader";
import TodoTitle from "./components/TodoTitle";
import TodoHello from "./components/TodoHello";
import TodoInput from "./components/TodoInput";
import TodoController from "./components/TodoController";
import TodoList from "./components/TodoList";
import TodoFooter from "./components/TodoFooter";
import TodoModal from "./components/common/TodoModal";
import { mapGetters } from "vuex";

export default {
  name: "App",
  data() {
    return {
      modalVisible: false,
      modalText: "",
    };
  },
  computed: {
    ...mapGetters(["storedName"]),
  },
  methods: {
    showModal(text) {
      this.modalText = text;
      this.modalVisible = !this.modalVisible;
    },
    reload() {
      this.$refs.list.getBoardList();
    },
  },
  components: {
    TodoHeader,
    TodoTitle,
    TodoInput,
    TodoHello,
    TodoController,
    TodoList,
    TodoFooter,
    TodoModal,
  },
};
</script>

<style lang="scss">
@import "./assets/style/reset";

.top {
  width: 100%;
  min-height: 43.6rem;
  padding: 0 30px 4.5rem;
  background-image: linear-gradient(145deg, #3770cc 20%, #ce91c9 84%);
}

.body {
  padding: 3rem 30px;
  background-color: #efefef;
}
</style>
-->
