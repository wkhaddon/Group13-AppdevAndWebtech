import styles from './Header.module.scss';
import { Link } from 'react-router-dom';
import { useAuth } from '@/context/AuthContext'; // adjust path as needed

function Header() {
	const { isLoggedIn, logout } = useAuth();

	return (
		<header className={styles.header}>
			<div className={styles.content}>
				<h2>
					<Link to="/" className={styles.logo}>
						Learniverse Connect
					</Link>
				</h2>
				<nav className={styles.nav}>
					<Link to="/">Home</Link>
					<Link to="/courses">Courses</Link>
					<Link to="/about">About</Link>

					{isLoggedIn ? (
						<>
							<Link to="/favorites">Favorites</Link>
							<Link to="/profile">Account</Link>
							<button onClick={logout} className={styles.logout}>
								Logout
							</button>
						</>
					) : (
						<>
							<Link to="/login" className={styles.login}>
								Login
							</Link>
							<Link to="/register" className={styles.register}>
								Register
							</Link>
						</>
					)}
				</nav>
			</div>
		</header>
	);
}

export default Header;
