import styles from './CourseFilter.module.scss';
import Slider from '@mui/material/Slider';

function CourseFilter({
	categories = [],
	category,
	setCategory,
	searchQuery,
	setSearchQuery,
	priceRange,
	setPriceRange,
	maxCoursePrice,
	onSearch,
	onReset
	}) {
	const MIN = 0;

	return (
		<aside className={styles.searchBox}>
			<h2 className={styles.searchTitle}>Course Filter</h2>
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
				{categories.map(cat => (
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
				<button onClick={onSearch} className={styles.searchButton}>Search</button>
				<button onClick={onReset} className={styles.showAllButton}>Reset</button>
			</div>
		</aside>
	);
}

export default CourseFilter;