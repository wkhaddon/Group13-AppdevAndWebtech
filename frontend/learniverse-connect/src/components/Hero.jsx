import styles from './Hero.module.scss';
import { Link } from 'react-router-dom';

function Hero() {
	return (
		<section className={styles.hero}>
			<h1>Learn From The Best Courses</h1>
			<p>Join thousands of students gaining new skills every day.</p>
			<Link to="/courses" className={styles.cta}>
				Explore Courses
			</Link>
		</section>
	);
}

export default Hero;
