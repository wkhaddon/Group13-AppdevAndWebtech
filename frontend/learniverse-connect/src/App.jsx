import { Routes, Route, useLocation } from 'react-router-dom';

import Header from './components/Header';
import Footer from './components/Footer';
import Hero from './components/Hero';

import Features from './pages/Features';
import Courses from './pages/Courses';
import About from './pages/About';
import Contact from './pages/Contact';

import Login from './pages/Login';

function App() {
	const location = useLocation();
	const showHero = location.pathname === '/';

	return (
		<>
			<Header />
			{showHero && <Hero />}

			<main>
				<Routes>
					<Route path="/" element={<Features />} />
					<Route path="/courses" element={<Courses />} />
					<Route path="/about" element={<About />} />
					<Route path="/contact" element={<Contact />} />
					<Route path="/login" element={<Login />} />
				</Routes>
			</main>

			<Footer />
		</>
	);
}

export default App;
