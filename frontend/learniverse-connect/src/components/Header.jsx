import styles from './Header.module.scss';
import { Link } from 'react-router-dom';
import { useAuth } from '@/context/AuthContext'; // adjust path as needed
import { useState } from 'react';

function Header() {
	const { isLoggedIn, logout } = useAuth();
	const [menuOpen, setMenuOpen] = useState(false);

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

				<nav
					className={`${styles.nav} ${menuOpen ? styles.open : ''}`}
				>
					<Link to="/" onClick={() => setMenuOpen(false)}>Home</Link>
					<Link to="/courses" onClick={() => setMenuOpen(false)}>Courses</Link>
					<Link to="/about" onClick={() => setMenuOpen(false)}>About</Link>

					{isLoggedIn ? (
						<>
							<Link to="/favorites" onClick={() => setMenuOpen(false)}>Favorites</Link>
							<Link to="/profile" onClick={() => setMenuOpen(false)}>Account</Link>
							<button onClick={() => { logout(); setMenuOpen(false); }} className={styles.logout}>
								Logout
							</button>
						</>
					) : (
						<>
							<Link to="/login" className={styles.login} onClick={() => setMenuOpen(false)}>Login</Link>
							<Link to="/register" className={styles.register} onClick={() => setMenuOpen(false)}>Register</Link>
						</>
					)}
				</nav>
			</div>
		</header>
	);
}

export default Header;
