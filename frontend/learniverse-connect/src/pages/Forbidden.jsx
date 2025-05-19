import styles from './Forbidden.module.scss';

function Forbidden() {
	return (
		<div className={styles.forbidden}>
			<h1>403 Forbidden</h1>
			<p>You do not have permission to access this page.</p>
			<a href="/">Go back to the homepage</a>
		</div>
	);
}

export default Forbidden;
