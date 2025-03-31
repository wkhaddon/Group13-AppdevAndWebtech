import { Link } from 'react-router-dom';

function Header() {
    return (
        <header className="header">
            <h2>Learniverse Connect</h2>
            <nav className="nav-buttons">
                <Link to='/'>Features</Link>
                <Link to='/courses'>Courses</Link>
                <Link to='/about'>About</Link>
                <Link to='/contact'>Contact</Link>
                <Link to='/login' className="login-btn">Login</Link>
            </nav>
        </header>
    );
}

export default Header;