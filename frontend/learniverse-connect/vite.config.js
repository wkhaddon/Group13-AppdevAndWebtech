import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import path from 'path';

// https://vite.dev/config/
export default defineConfig({
	base: '/Group13-AppdevAndWebtech/',
	plugins: [react()],
	resolve: {
		alias: {
			'@': path.resolve(__dirname, 'src'),
		},
	},
	css: {
		preprocessorOptions: {
			scss: {
				// Enables `@use '@/styles/xyz'` in .scss files
				includePaths: [path.resolve(__dirname, 'src')],
			},
		},
	},
});
