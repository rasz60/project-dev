import { fileURLToPath, URL } from "node:url";
import path from "path";

export const defineConfig = {
  transpileDependencies: true,
  resolve: {
    alias: {
      "@": fileURLToPath(new URL("./src", import.meta.url)),
    },
  },
};

export default {
  devServer: {
    proxy: "http://localhost:5858",
  },
  outputDir: "../src/main/resources/static/vue",
  indexPath: "../../templates/vue/index.html",
  publicPath: "/vue",
};
