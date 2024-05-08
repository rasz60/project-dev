<script setup></script>

<template lang="">
  <div class="search-box">
    <label for="searchIpt" class="searchIpt">
      <font-awesome-icon :icon="['fas', 'magnifying-glass']" />
    </label>
    <input
      type="search"
      id="searchIpt"
      class="searchIpt"
      name="searchKeyword"
      @focus="searchBoxCtrl('f')"
      @blur="searchBoxCtrl('b')"
      placeholder="search..."
    />
  </div>
</template>

<script>
export default {
  methods: {
    searchBoxCtrl(type) {
      var searchBox = document.querySelector("div.search-box");
      var searchLabel = document.querySelector("label.searchIpt");
      if (type == "f") {
        searchBox.style.backgroundColor = "#5F5F5F";

        searchLabel.style.backgroundColor = "transparent";
        searchLabel.style.color = "white";
        searchLabel.style.border = "none";
      } else {
        searchBox.style.backgroundColor = "transparent";

        searchLabel.style.backgroundColor = "transparent";
        searchLabel.style.color = "#5F5F5F";
        searchLabel.style.border = "2px solid #5F5F5F";
      }
    },
  },
};
</script>
<style lang=""></style>
