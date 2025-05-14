import styles from './Courses.module.scss';

import { useEffect, useState } from 'react';
import api from '@/api/axios';

function Courses() {
	const [courses, setCourses] = useState([]);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);

	useEffect(() => {
		api.get('/courses')
			.then((response) => {
				setCourses(response.data);
			})
			.catch((err) => {
				console.error('Failed to fetch courses:', err);
				setError('Failed to load courses.');
			})
			.finally(() => {
				setLoading(false);
			});
	}, []);

	if (loading) return <p>Loading courses...</p>;
	if (error) return <p>{error}</p>;

	return (
		<section className={styles.courses}>
			{courses.map((course) => (
				<article key={course.id} className={styles.course}>
					<h2 className={styles.courseTitle}>{course.title}</h2>
					<p className={styles.courseDescription}>{course.description}</p>
					<p className={styles.courseLevel}>{course.level}</p>
				</article>
			))}
		</section>
	);
}

export default Courses;
