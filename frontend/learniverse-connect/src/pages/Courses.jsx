import styles from './Courses.module.scss';
import { useEffect, useState } from 'react';
import api from '@/api/axios';

function Courses() {
	const [courses, setCourses] = useState([]);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const [searchQuery, setSearchQuery] = useState('');
	const [submittedQuery, setSubmittedQuery] = useState('');

	useEffect(() => {
		const endpoint = submittedQuery
			? `/courses/search?q=${encodeURIComponent(submittedQuery)}`
			: '/courses';

		setLoading(true);
		api.get(endpoint)
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
	}, [submittedQuery]);

	const handleSearch = () => {
		setSubmittedQuery(searchQuery);
	};

	return (
		<div className={styles.container}>
			<div className={styles.searchBox}>
				<h2 className={styles.searchTitle}>Search Courses</h2>
				<input
					type="text"
					placeholder="Search courses..."
					value={searchQuery}
					onChange={(e) => setSearchQuery(e.target.value)}
					className={styles.searchInput}
				/>
				<div className={styles.buttonRow}>
					<button onClick={handleSearch} className={styles.searchButton}>
						Search
					</button>
					<button
						onClick={() => {
							setSearchQuery('');
							setSubmittedQuery('');
						}}
						className={styles.showAllButton}
					>
						Show All Courses
					</button>
				</div>
			</div>

			{loading && <p>Loading courses...</p>}
			{error && <p>{error}</p>}
			{!loading && !error && courses.length === 0 && <p>No courses found.</p>}

			<div className={styles.courseList}>
				{courses.map((course) => (
					<article key={course.id} className={styles.courseCard}>
						<h2 className={styles.courseTitle}>{course.title}</h2>
						<p className={styles.courseDescription}>{course.description}</p>
						<p className={styles.courseLevel}>Level: {course.level}</p>
					</article>
				))}
			</div>
		</div>
	);
}

export default Courses;
