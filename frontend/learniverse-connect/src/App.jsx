import { Routes, Route } from 'react-router-dom';

import ProtectedRoute from './components/ProtectedRoute';

import Header from './components/Header';
import CallToAction from './components/CallToAction';
import Footer from './components/Footer';

import Home from './pages/Home';
import Courses from './pages/Courses';
import Favorites from './pages/Favorites';
import About from './pages/About';

import Login from './pages/Login';
import Register from './pages/Register';

import NotFound from './pages/NotFound';

function App() {
	return (
		<>
			<Header />

			<main>
				<Routes>
					<Route path="/" element={<Home />} />
					<Route path="/courses" element={<Courses />} />
					<Route
						path="/favorites"
						element={
							<ProtectedRoute>
								<Favorites />
							</ProtectedRoute>
						}
					/>
					<Route path="/about" element={<About />} />

					{/* Authentication */}
					<Route path="/login" element={<Login />} />
					<Route path="/register" element={<Register />} />

					{/* 404 Not Found */}
					<Route path="*" element={<NotFound />} />
				</Routes>
			</main>
			<CallToAction />
			<Footer />
		</>
	);
}

export default App;
