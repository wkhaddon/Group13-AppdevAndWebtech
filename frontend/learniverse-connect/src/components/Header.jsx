import styles from './Header.module.scss';
import { Link } from 'react-router-dom';

function Header() {
	return (
		<header className={styles.header}>
			<content className={styles.content}>
				<h2>Learniverse Connect</h2>
				<nav className={styles.nav}>
					<Link to="/">Features</Link>
					<Link to="/courses">Courses</Link>
					<Link to="/about">About</Link>
					<Link to="/contact">Contact</Link>
					<Link to="/login" className={styles.login}>
						Login
					</Link>
				</nav>
			</content>
		</header>
	);
}

export default Header;
