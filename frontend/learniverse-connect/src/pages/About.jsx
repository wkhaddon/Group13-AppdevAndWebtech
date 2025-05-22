import styles from './About.module.scss';

function About() {
	return (
		<div className={styles.about}>
			<section className={styles.hero}>
				<h1>About Learniverse Connect</h1>
				<p>We build bridges between learning and possibilities</p>
			</section>

			<section className={styles.cards}>
				<section className={styles.story}>
					<h2>Our History</h2>
					<p>Started as a school project in 2025</p>
				</section>

				<section className={styles.values}>
					<h2>Where we are located</h2>
					<ul>
						<li>Larsgårdsvegen 2</li>
						<li>6009 Ålesund</li>
						<li>Norway</li>
					</ul>
				</section>
			</section>

			<section className={styles.team}>
				<h2>Our Team</h2>
				<div className={styles.teamGrid}>
					<article className={styles.teamMember}>
						<h3>William Haddon</h3>
						<p>Developer</p>
					</article>
					<article className={styles.teamMember}>
						<h3>Elias Einarsen</h3>
						<p>Developer</p>
					</article>
				</div>
			</section>
		</div>

	);
}

export default About;
