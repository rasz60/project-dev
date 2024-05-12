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
    /*
    proxy: {
      "^/api": {
        target: "http://localhost:5858",
        changeOrigin: true,
      },
      "^/loginProc": {
        target: "http://localhost:5858",
        changeOrigin: true,
      },
      "^/logout": {
        target: "http://localhost:5858",
        changeOrigin: true,
      },
      "^/signup/join": {
        target: "http://localhost:5858",
        changeOrigin: true,
      },
    },
    */
  },
  outputDir: "../src/main/resources/static/vue",
  indexPath: "../../templates/vue/index.html",
  publicPath: "/vue",
};
