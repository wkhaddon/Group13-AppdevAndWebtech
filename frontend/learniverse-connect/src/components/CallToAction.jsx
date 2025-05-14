import styles from './CallToAction.module.scss';

function CallToAction() {
	return (
		<section className={styles.cta}>
			<div className={styles.container}>
				<h2 className={styles.title}>Contact Us Today</h2>
				<p className={styles.description}>
					Discover new opportunities and take the first step toward your future with Learniverse Connect.
				</p>
				<a href="mailto:contact@learniverse.com" className={styles.button}>
					Contact us
				</a>
			</div>
		</section>
	);
}

export default CallToAction;
