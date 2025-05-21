import styles from './CourseCard.module.scss';

function CourseCard({ course }) {
	return (
		<article className={styles.card}>
			<header className={styles.header}>
				<h2 className={styles.title}>{course.title}</h2>
				<p className={styles.level}>{course.level}</p>
			</header>
			<p className={styles.description}>{course.description}</p>
			<div className={styles.meta}>
				<span className={styles.price}>€{course.price}</span>
				{course.ectsCredits && (
					<span className={styles.ects}>{course.ectsCredits} ECTS</span>
				)}
			</div>
		</article>
	);
}

export default CourseCard;
