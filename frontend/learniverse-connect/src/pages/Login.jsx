import styles from './Login.module.scss';
import { Link } from 'react-router-dom';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import api from '@/api/axios';
import { useAuth } from '@/context/AuthContext';

function Login() {
	const [form, setForm] = useState({
		identifier: '',
		password: '',
	});

	const handleChange = e => {
		setForm({
			...form,
			[e.target.name]: e.target.value,
		});
	}

	const navigate = useNavigate();
	const { login } = useAuth();

	const handleSubmit = async e => {
		e.preventDefault();

		try {
			const response = await api.post('/auth/login', form);
			if (response.status === 200) {
				login();
				navigate('/');
			}
		} catch (error) {
			// Handled by global interceptor
		}
	}

	return (
		<section className={styles.login}>
			<h1 className={styles.title}>Login</h1>
			<form className={styles.form} onSubmit={handleSubmit}>
				<input
					className={styles.input}
					type='text'
					name='identifier'
					placeholder='Username or Email'
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
				<button className={styles.submit} type='submit'>Login</button>
			</form>
			<p className={styles.register}>
				Dont have an account? <Link to="/register">
				Register
			</Link>
			</p>
		</section>
	);
}

export default Login;
