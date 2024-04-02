import { fileURLToPath, URL } from 'node:url'

export const defineConfig = {
    transpileDependencies: true,
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    },
};
