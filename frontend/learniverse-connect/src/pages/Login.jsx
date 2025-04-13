import { useAuth } from '@/context/AuthContext';
import { Navigate } from 'react-router-dom';

function Login() {
	const { isLoggedIn, login } = useAuth();

	// Redirect if already logged in
	if (isLoggedIn) {
		return <Navigate to="/" replace />;
	}

	return (
		<section>
			<h1>Login</h1>
			<p>This is a mock login. Click below to simulate logging in.</p>
			<button onClick={login}>Simulate Login</button>
		</section>
	);
}

export default Login;
