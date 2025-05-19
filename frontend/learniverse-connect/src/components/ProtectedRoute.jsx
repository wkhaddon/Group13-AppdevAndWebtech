import { Navigate, useLocation } from 'react-router-dom';
import { useAuth } from '@/context/AuthContext';

const ProtectedRoute = ({ children, requiredRoles = [] }) => {
	const { isLoggedIn, role } = useAuth();
	const location = useLocation();

	// Not logged in → redirect to login, with return URL
	if (!isLoggedIn) {
		return <Navigate to={`/login?redirect=${encodeURIComponent(location.pathname)}`} replace />;
	}

	// Role is missing or doesn't match -> show 403 or redirect
	if (requiredRoles.length && !requiredRoles.includes(role)) {
		return <Navigate to="/403" replace />;
	}

	return children;
};

export default ProtectedRoute;
