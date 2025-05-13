import styles from './CallToAction.module.scss';

function CallToAction() {
	return (
		<section className={styles.cta}>
			<div className={styles.container}>
				<h2 className={styles.title}>Contact Us Today</h2>
				<p className={styles.description}>
					Discover new opportunities and take the first step toward your future with Learniverse Connect.
				</p>
				<button className={styles.button}>
					Contact us
				</button>
			</div>
		</section>
	);
}

export default CallToAction;
