import axios from 'axios';

const api = axios.create({
	baseURL: 'https://localhost:8443/api',
	withCredentials: true,
});

// Global request interceptor
api.interceptors.request.use(
	response => response,
	error => {
		console.error('Request error:', error);

		alert(error.response?.data || 'An error occurred.');

		return Promise.reject(error);
	}
);

export default api;
