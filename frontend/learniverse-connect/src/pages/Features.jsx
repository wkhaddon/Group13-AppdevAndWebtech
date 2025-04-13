import styles from './Features.module.scss';

function Features() {
	return (
		<section className={styles.features}>
			<article className={styles.feature}>
				<h2>Expert Instructors</h2>
				<p>Learn from industry leaders with real-world experience.</p>
			</article>
			<article className={styles.feature}>
				<h2>Flexible Learning</h2>
				<p>Study at your own pace, anytime, anywhere.</p>
			</article>
			<article className={styles.feature}>
				<h2>Community Support</h2>
				<p>Join a community of learners and get support from peers.</p>
			</article>
			<article className={styles.feature}>
				<h2>Affordable Prices</h2>
				<p>Access high-quality courses at an affordable cost.</p>
			</article>
		</section>
	);
}

export default Features;
