import styles from './Favorites.module.scss';
import { useEffect, useState } from 'react';
import api from '@/api/axios';
import { useAuth } from '@/context/AuthContext';

function Favorites() {
	const { userId } = useAuth();
	const [favorites, setFavorites] = useState([]);
	const [error, setError] = useState(null);
	const [loading, setLoading] = useState(false);

	useEffect(() => {
		if (!userId) return;

		setLoading(true);
		api.get(`/favorites/user/${userId}`)
			.then(res => setFavorites(res.data))
			.catch(err => {
				console.error('Failed to load favorites:', err);
				setError('Could not load favorites.');
			})
			.finally(() => setLoading(false));
	}, [userId]);

	const removeFavorite = async (favoriteId) => {
		try {
			await api.delete(`/favorites/${favoriteId}`);
			setFavorites(prev => prev.filter(fav => fav.id !== favoriteId));
		} catch (err) {
			console.error('Failed to remove favorite:', err);
		}
	};

	if (!userId) return <p className={styles.error}>You must be logged in to view favorites.</p>;

	return (
		<div className={styles.container}>
			<h2>Your Favorites</h2>
			{loading ? (
				<p>Loading favorites...</p>
			) : error ? (
				<p className={styles.error}>{error}</p>
			) : favorites.length === 0 ? (
				<p>You have no favorite courses yet.</p>
			) : (
				<div className={styles.favoriteList}>
					{favorites.map(fav => (
						<div key={fav.id} className={styles.favoriteCard}>
							<h3>{fav.course.title}</h3>
							<p>{fav.course.description}</p>
							<button onClick={() => removeFavorite(fav.id)}>Remove</button>
						</div>
					))}
				</div>
			)}
		</div>
	);
}

export default Favorites;