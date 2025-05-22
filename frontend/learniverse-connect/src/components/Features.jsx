import styles from './Features.module.scss';
import { Link } from 'react-router-dom';

function Features() {
	return (
		<section className={styles.features}>
			<Link to="/courses" className={styles.link}>
			<article className={styles.feature}>
				<h2>Expert Instructors</h2>
				<p>Learn from industry leaders with real-world experience.</p>
			</article>
			</Link>
			<Link to="/courses" className={styles.link}>
			<article className={styles.feature}>
				<h2>Flexible Learning</h2>
				<p>Study at your own pace, anytime, anywhere.</p>
			</article>
			</Link>
			<Link to="/courses" className={styles.link}>
			<article className={styles.feature}>
				<h2>Community Support</h2>
				<p>Join a community of learners and get support from peers.</p>
			</article>
			</Link>
			<Link to="/courses" className={styles.link}>
			<article className={styles.feature}>
				<h2>Affordable Prices</h2>
				<p>Access high-quality courses at an affordable cost.</p>
			</article>
			</Link>
			<Link to="/courses" className={styles.link}>
			<article className={styles.feature}>
				<h2>Interactive Learning</h2>
				<p>Engage with interactive content and hands-on projects.</p>
			</article>
			</Link>
			<Link to="/courses" className={styles.link}>
			<article className={styles.feature}>
				<h2>Mobile Friendly</h2>
				<p>Learn on the go with our mobile-friendly platform.</p>
			</article>
			</Link>
		</section>
	);
}

export default Features;
