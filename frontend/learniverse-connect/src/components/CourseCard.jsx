import styles from './CourseCard.module.scss';

function CourseCard({ course }) {
	const handleEmailClick = () => {
		const email = "orders@learniverse.com";
		const subject = encodeURIComponent(`Inquiry about ${course.title}`);
		const body = encodeURIComponent(
			`Hi,\n\nI'm interested in the course "${course.title}" (${course.level}).\n\nDescription:\n${course.description}\n\nPrice: €${course.price} \n\nPlease send me more details.\n\nThank you!`
		);
		window.location.href = `mailto:${email}?subject=${subject}&body=${body}`;
	};

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
			<button className={styles.emailButton} onClick={handleEmailClick}>
				Contact about this course
			</button>
		</article>
	);
}

export default CourseCard;
