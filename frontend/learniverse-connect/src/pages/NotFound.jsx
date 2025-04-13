import styles from './NotFound.module.scss';

const NotFound = () => (
	<section className={styles.notfound}>
		<h1>404</h1>
		<h2>Page Not Found</h2>
		<p>
			The page you are looking for does not exist. Please check the URL or return to the
			homepage.
		</p>
		<a href="/">Go to Homepage</a>
	</section>
);

export default NotFound;
