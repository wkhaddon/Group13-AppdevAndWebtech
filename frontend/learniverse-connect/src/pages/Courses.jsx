import styles from './Courses.module.scss';
import { useEffect, useState } from 'react';
import Slider from '@mui/material/Slider';
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
		api.get('/courses/max-price')
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
			<aside className={styles.searchBox}>
				<h2 className={styles.searchTitle}>Filter Courses</h2>

				<input
					type="text"
					placeholder="Search courses..."
					value={searchQuery}
					onChange={(e) => setSearchQuery(e.target.value)}
					className={styles.searchInput}
				/>

				<select
					value={category}
					onChange={(e) => setCategory(e.target.value)}
					className={styles.dropdown}
				>
					<option value="">All Categories</option>
					{categories.map((cat) => (
						<option key={cat.id} value={cat.id}>{cat.name}</option>
					))}
				</select>

				{maxCoursePrice !== null && (
					<div className={styles.rangeSlider}>
						<label>Price Range: {priceRange[0]} - {priceRange[1]}</label>
						<Slider
							value={priceRange}
							onChange={(_, newValue) => setPriceRange(newValue)}
							valueLabelDisplay="auto"
							min={MIN}
							max={maxCoursePrice}
						/>
					</div>
				)}

				<div className={styles.buttonRow}>
					<button onClick={handleSearch} className={styles.searchButton}>Search</button>
					<button onClick={handleReset} className={styles.showAllButton}>Reset Filters</button>
				</div>
			</aside>

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
