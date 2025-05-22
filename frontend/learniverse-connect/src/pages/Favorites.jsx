import React, { useEffect, useState } from "react";
import { useAuth } from "@/context/AuthContext";

const Favorites = () => {
	const { userId } = useAuth();
	const [favorites, setFavorites] = useState([]);

	useEffect(() => {
		if (!userId) { return; }
		fetch(`/api/favorites/user/${userId}`)
			.then(res => res.json())
			.then(data => setFavorites(data))
			.catch(err => console.error("Failed to fetch favorites:", err));
	}, [userId]);

	return (
		<section>
			<p>Current userId: {userId}</p>
			<h1>Favorites</h1>
			{favorites.length === 0 ? (
				<p>Your favorite courses will be displayed here.</p>
			) : (
				<ul>
					{favorites.map(fav => (
						<li key={fav.id}>
							{fav.course?.title || "Untitled Course"}
						</li>
					))}
				</ul>
			)}
		</section>
	);
};

export default Favorites;
