import styles from './Courses.module.scss';
import { useEffect, useState } from 'react';
import CourseFilter from '@/components/CourseFilter';
import api from '@/api/axios';
import { useSearchParams } from 'react-router-dom';

import CourseCard from '@/components/CourseCard';

const MIN = 0;

function Courses() {
	const [courses, setCourses] = useState([]);
	const [category, setCategory] = useState('');
	const [categories, setCategories] = useState([]);
	const [searchQuery, setSearchQuery] = useState('');
	const [maxCoursePrice, setMaxCoursePrice] = useState(null); // start with null
	const [priceRange, setPriceRange] = useState([MIN, null]);
	const [loading, setLoading] = useState(false);
	const [error, setError] = useState(null);
	const [searchParams, setSearchParams] = useSearchParams();

	// Fetch categories for filter dropdown
	useEffect(() => {
		api.get('/categories')
			.then(res => setCategories(res.data))
			.catch(err => console.error('Failed to load categories', err));
	}, []);

	// Fetch max price to initialize slider range
	useEffect(() => {
		api.get('/courses/maxPrice')
			.then(res => {
				const max = Math.ceil(parseFloat(res.data));
				setMaxCoursePrice(max);
				setPriceRange([MIN, max]);
			})
			.catch(err => {
				console.error('Failed to determine max price', err);
				setMaxCoursePrice(10000); // fallback
				setPriceRange([MIN, 10000]);
			});
	}, []);

	// Search courses when filters change — only after max price is loaded
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

		const endpoint = `/courses/search?${params.toString()}`;
		setLoading(true);
		api.get(endpoint)
			.then((response) => {
				setCourses(response.data);
				setError(null);
			})
			.catch((err) => {
				console.error('Failed to fetch courses:', err);
				setError('Failed to load courses.');
			})
			.finally(() => setLoading(false));
	}, [searchParams, maxCoursePrice]);

	// Handle user filter actions
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
					<>
						{!loading && courses.length === 0 && <p>No courses found.</p>}

						<div className={styles.courseList}>
							{courses.map((course) => (
								<CourseCard key={course.id} course={course} />
							))}
						</div>
					</>
				)}
			</main>
		</div>
	);
}

export default Courses;
