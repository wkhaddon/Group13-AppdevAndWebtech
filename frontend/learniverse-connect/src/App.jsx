import { Routes, Route } from 'react-router-dom';

import ProtectedRoute from './components/ProtectedRoute';
import { Role } from './context/AuthContext';

import Header from './components/Header';
import CallToAction from './components/CallToAction';
import Footer from './components/Footer';

import Home from './pages/Home';
import Courses from './pages/Courses';
import Favorites from './pages/Favorites';
import AdminCourses from './pages/AdminCourses';
import About from './pages/About';

import Login from './pages/Login';
import Register from './pages/Register';

import NotFound from './pages/NotFound';
import Forbidden from './pages/Forbidden';

function App() {
	return (
		<>
			<Header />

			<main>
				<Routes>
					<Route path="/" element={<Home />} />
					<Route path="/courses" element={<Courses />} />

					{/* User-specific routes */}
					<Route
						path="/favorites"
						element={
							<ProtectedRoute requiredRoles={[Role.User]}>
								<Favorites />
							</ProtectedRoute>
						}
					/>

					{/* Provider-specific routes */}
					<Route
						path="/provider"
						element={
							<ProtectedRoute requiredRoles={[Role.Provider]}>
								{/* Provider Panel Component */}
							</ProtectedRoute>
						}
					/>

					{/* Support-specific routes */}
					<Route
						path="/support"
						element={
							<ProtectedRoute requiredRoles={[Role.Support]}>
								{/* Support Tools Component */}
							</ProtectedRoute>
						}
					/>

					{/* Admin-specific routes */}
					<Route
						path="/admin"
						element={
							<ProtectedRoute requiredRoles={[Role.Admin]}>
								<AdminCourses />
							</ProtectedRoute>
						}
					/>

					{/* Public routes */}
					<Route path="/about" element={<About />} />

					{/* Authentication */}
					<Route path="/login" element={<Login />} />
					<Route path="/register" element={<Register />} />

					{/* Error */}
					<Route path="*" element={<NotFound />} />
					<Route path="/403" element={<Forbidden />} />
				</Routes>
			</main>

			<CallToAction />
			<Footer />
		</>
	);
}

export default App;
