import styles from './Register.module.scss';

import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import api from '@/api/axios';
import { useAuth } from '@/context/AuthContext';

function Register() {
	const [form, setForm] = useState({
		username: '',
		email: '',
		password: '',
	});

	const [error, setError] = useState(null);
	const { login } = useAuth();
	const navigate = useNavigate();

	const handleChange = e => {
		setForm({
			...form,
			[e.target.name]: e.target.value,
		});
		setError(null);
	}

	const handleSubmit = async e => {
		e.preventDefault();

		try {
			const response = await api.post('/auth/register', form);
			if (response.status === 200) {
				login();
				navigate('/');
			}
		} catch (error) {
			if (error.response) {
				// The request was made and the server responded with a status code
				setError(error.response.data.message || 'Registration failed');
			} else if (error.request) {
				// The request was made but no response was received
				setError('No response from server');
			} else {
				// Something happened in setting up the request that triggered an Error
				setError('Error: ' + error.message);
			}
		}
	}

	return (
		<section className={styles.register}>
			<h1 className={styles.title}>Register</h1>
			<form className={styles.form} onSubmit={handleSubmit}>
				{error && <div className={styles.error}>{error}</div>}
				<input
					className={styles.input}
					type='text'
					name='username'
					placeholder='Username'
					value={form.username}
					onChange={handleChange}
					required
				/>
				<input
					className={styles.input}
					type='email'
					name='email'
					placeholder='Email'
					value={form.email}
					onChange={handleChange}
					required
				/>
				<input
					className={styles.input}
					type='password'
					name='password'
					placeholder='Password'
					value={form.password}
					onChange={handleChange}
					required
				/>

				<button type="submit" className={styles.submit}>Register</button>
			</form>
		</section>
	);
}

export default Register;
