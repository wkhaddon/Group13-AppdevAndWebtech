import { createContext, useContext, useEffect, useState } from 'react';
import api from '@/api/axios';

const AuthContext = createContext({
	isLoggedIn: false,
	login: () => {},
	logout: () => {},
});

async function checkAuth() {
	try {
		await api.get('/auth/validate');
		return true;
	} catch {
		return false;
	}
}

export const AuthProvider = ({ children }) => {
	const [isLoggedIn, setIsLoggedIn] = useState(false);

	const login = () => setIsLoggedIn(true);
	const logout = () => {
		api.post('/auth/logout')
		setIsLoggedIn(false);
	}

	useEffect(() => {
		const validateAuth = async () => {
			const isAuthenticated = await checkAuth();
			setIsLoggedIn(isAuthenticated);
		};

		validateAuth();

		const interval = setInterval(validateAuth, 60000); // Check every minute
		return () => clearInterval(interval);
	}, []);

	return (
		<AuthContext.Provider value={{ isLoggedIn, login, logout }}>
			{children}
		</AuthContext.Provider>
	);
};

export const useAuth = () => useContext(AuthContext);
