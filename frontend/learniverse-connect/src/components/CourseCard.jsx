import styles from './CourseCard.module.scss';
import api from '@/api/axios';
import { useAuth } from '@/context/AuthContext';
import { useState } from 'react';

function CourseCard({ course }) {
	const { userId } = useAuth();
	const [isFavorited, setIsFavorited] = useState(false);
	const [error, setError] = useState(null);

	const handleFavorite = async () => {
		if (!userId) {
			setError('You must be logged in to favorite courses.');
			return;
		}

		try {
			await api.post('/favorites/add', { courseId: course.id });
			setIsFavorited(true);
			setError(null);
		} catch (err) {
			console.error('Failed to add favorite:', err);
			setError('Could not add to favorites.');
		}
	};

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

			<img src={course.imageUrl} alt={course.title} className={styles.image} />

			<p className={styles.description}>{course.description}</p>

			<div className={styles.meta}>
				<span className={styles.price}>€{course.price}</span>
				{course.ectsCredits && (
					<span className={styles.ects}>{course.ectsCredits} ECTS</span>
				)}
			</div>

			<div className={styles.buttons}>
				{!isFavorited ? (
					<button className={styles.favoriteButton} onClick={handleFavorite}>
						❤️ Add to Favorites
					</button>
				) : (
					<span className={styles.added}>✔ Added to Favorites</span>
				)}

				<button className={styles.emailButton} onClick={handleEmailClick}>
					Contact about this course
				</button>
			</div>

			{error && <p className={styles.error}>{error}</p>}
		</article>
	);
}

export default CourseCard;
