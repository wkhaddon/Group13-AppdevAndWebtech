import styles from './Header.module.scss';
import { Link } from 'react-router-dom';
import { useAuth, Role } from '@/context/AuthContext';
import { useState, useEffect } from 'react';
import { toggleTheme } from '@/config/theme.js';

function Header() {
	const { isLoggedIn, logout, role } = useAuth();
	const [menuOpen, setMenuOpen] = useState(false);
	const [themeIcon, setThemeIcon] = useState('🌙');

	useEffect(() => {
		setThemeIcon(document.body.dataset.theme === 'dark' ? '🌙' : '☀️');
	}, []);

	const handleThemeToggle = () => {
		toggleTheme();
		setThemeIcon(document.body.dataset.theme === 'dark' ? '🌙' : '☀️');
	};

	const closeMenu = () => setMenuOpen(false);

	return (
		<header className={styles.header}>
			<div className={styles.content}>
				<h2>
					<Link to="/" className={styles.logo}>
						Learniverse Connect
					</Link>
				</h2>

				<button
					className={styles.hamburger}
					onClick={() => setMenuOpen(!menuOpen)}
					aria-label="Toggle menu"
				>
					☰
				</button>

				<nav className={`${styles.nav} ${menuOpen ? styles.open : ''}`}>
					<Link to="/" onClick={closeMenu}>Home</Link>
					<Link to="/courses" onClick={closeMenu}>Courses</Link>
					<Link to="/about" onClick={closeMenu}>About</Link>

					{role === Role.User && (
						<Link to="/favorites" onClick={closeMenu}>Favorites</Link>
					)}

					{role === Role.Provider && (
						<Link to="/provider" onClick={closeMenu}>Provider Panel</Link>
					)}

					{role === Role.Support && (
						<Link to="/support" onClick={closeMenu}>Support Tools</Link>
					)}

					{role === Role.Admin && (
						<Link to="/admin" onClick={closeMenu}>Admin Dashboard</Link>
					)}

					{isLoggedIn ? (
						<>
							<Link to="/profile" onClick={closeMenu}>Account</Link>
							<button onClick={() => { logout(); closeMenu(); }} className={styles.logout}>
								Logout
							</button>
						</>
					) : (
						<>
							<Link to="/login" className={styles.login} onClick={closeMenu}>Login</Link>
							<Link to="/register" className={styles.register} onClick={closeMenu}>Register</Link>
						</>
					)}
				</nav>

				<button
					onClick={handleThemeToggle}
					className={styles.themeToggle}
					id="theme-toggle"
					aria-label="Toggle theme"
				>
					{themeIcon}
				</button>
			</div>
		</header>
	);
}

export default Header;
