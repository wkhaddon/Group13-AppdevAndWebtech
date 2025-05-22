import { createContext, useContext, useEffect, useState } from 'react';
import api from '@/api/axios';

const AuthContext = createContext({
	isLoggedIn: false,
	role: null,
	userId: null,
	login: () => {},
	logout: () => {},
});

export const Role = {
	User: 'USER',
	Provider: 'PROVIDER',
	Support: 'SUPPORT',
	Admin: 'ADMIN',
};

async function checkAuth() {
	try {
		const response = await api.get('/auth/validate');
		return {
			authenticated: true,
			role: response.data?.role || 'ROLE_USER',
			userId: response.data?.userId || null,
		};
	} catch {
		return {
			authenticated: false,
			role: null,
			userId: null,
		};
	}
}

export const AuthProvider = ({ children }) => {
	const [isLoggedIn, setIsLoggedIn] = useState(false);
	const [role, setRole] = useState(null);
	const [userId, setUserId] = useState(null);

	const login = async (credentials) => {
		try {
			await api.post('/auth/login', credentials);
			const { authenticated, role, userId } = await checkAuth();
			setIsLoggedIn(authenticated);
			setRole(role);
			setUserId(userId);
		} catch {
			setIsLoggedIn(false);
			setRole(null);
			setUserId(null);
		}
	};

	const logout = () => {
		api.post('/auth/logout');
		setIsLoggedIn(false);
		setRole(null);
		setUserId(null);
	};

	useEffect(() => {
		const validateAuth = async () => {
			const { authenticated, role, userId } = await checkAuth();
			setIsLoggedIn(authenticated);
			setRole(role);
			setUserId(userId);
		};

		validateAuth();

		const interval = setInterval(validateAuth, 60000);
		return () => clearInterval(interval);
	}, []);

	return (
		<AuthContext.Provider value={{ isLoggedIn, role, userId, login, logout }}>
			{children}
		</AuthContext.Provider>
	);
};

export const useAuth = () => useContext(AuthContext);
