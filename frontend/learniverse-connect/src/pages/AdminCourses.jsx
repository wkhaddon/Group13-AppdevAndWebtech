import styles from './AdminCourses.module.scss';
import { useEffect, useState } from 'react';
import CourseFilter from '@/components/CourseFilter';
import api from '@/api/axios';
import { useSearchParams } from 'react-router-dom';

function AdminCourses() {
	const MIN = 0;

	const [courses, setCourses] = useState([]);
	const [category, setCategory] = useState('');
	const [categories, setCategories] = useState([]);
	const [searchQuery, setSearchQuery] = useState('');
	const [maxCoursePrice, setMaxCoursePrice] = useState(null);
	const [priceRange, setPriceRange] = useState([MIN, null]);
	const [loading, setLoading] = useState(false);
	const [error, setError] = useState(null);
	const [searchParams, setSearchParams] = useSearchParams();

	useEffect(() => {
		api.get('/categories')
			.then(res => setCategories(res.data))
			.catch(err => console.error('Failed to load categories', err));
	}, []);

	useEffect(() => {
		api.get('/courses/maxPrice/admin')
		.then(res => {
			const max = Math.ceil(parseFloat(res.data));
			setMaxCoursePrice(max);
			setPriceRange([MIN, max]);
		})
		.catch(err => {
			console.error('Failed to determine max price', err);
			setMaxCoursePrice(10000);
			setPriceRange([MIN, 10000]);
		});
	}, []);

	useEffect(() => {
		if (maxCoursePrice === null) return;

		const query = searchParams.get('q') || '';
		const cat = searchParams.get('category') || '';
		const min = parseFloat(searchParams.get('minPrice'));
		const max = parseFloat(searchParams.get('maxPrice'));

		setSearchQuery(query);
		setCategory(cat);
		setPriceRange([
			!isNaN(min) ? min : MIN,
			!isNaN(max) ? max : maxCoursePrice
		]);

		const params = new URLSearchParams();
		if (query) params.append('q', query);
		if (cat) params.append('category', cat);
		if (!isNaN(min) && min > MIN) params.append('minPrice', min);
		if (!isNaN(max) && max < maxCoursePrice) params.append('maxPrice', max);

		setLoading(true);
		api.get(`/courses/search/admin?${params.toString()}`)
			.then(res => {
				setCourses(res.data);
				setError(null);
			})
			.catch(err => {
				console.error('Failed to fetch admin courses:', err);
				setError('Failed to load courses. Are you an admin?');
			})
			.finally(() => setLoading(false));
	}, [searchParams, maxCoursePrice]);

	const handleSearch = () => {
		const params = {};
		if (searchQuery) params.q = searchQuery;
		if (category) params.category = category;
		if (priceRange[0] > MIN) params.minPrice = priceRange[0];
		if (priceRange[1] < maxCoursePrice) params.maxPrice = priceRange[1];
		setSearchParams(params);
	};

	const handleReset = () => {
		setSearchQuery('');
		setCategory('');
		setPriceRange([MIN, maxCoursePrice]);
		setSearchParams({});
	};

	const toggleVisibility = async (id) => {
		try {
			await api.patch(`/courses/${id}/visibility`);
			handleSearch();
		} catch (err) {
			console.error('Failed to toggle visibility:', err);
		}
	};

	return (
		<div className={styles.container}>
			<CourseFilter
				categories={categories}
				category={category}
				setCategory={setCategory}
				searchQuery={searchQuery}
				setSearchQuery={setSearchQuery}
				priceRange={priceRange}
				setPriceRange={setPriceRange}
				maxCoursePrice={maxCoursePrice}
				onSearch={handleSearch}
				onReset={handleReset}
			/>

			<main>
				{loading && <p>Loading courses...</p>}
				{error ? (
				<p className={styles.error}>{error}</p>
				) : (
					<div className={styles.courseList}>
						{courses.map(course => (
							<div key={course.id} className={styles.courseCard}>
								<h3>{course.title}</h3>
								<p>{course.description}</p>
								<p>Hidden: {course.isHidden ? 'Yes' : 'No'}</p>
								<button onClick={() => toggleVisibility(course.id)}>
									{course.isHidden ? 'Unhide' : 'Hide'}
								</button>
							</div>
						))}
					</div>
				)}
			</main>
		</div>
	);
}

export default AdminCourses;
