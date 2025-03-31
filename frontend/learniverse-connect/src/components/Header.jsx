function Header() {
    return (
        <header className="header">
            <h2>Learniverse Connect</h2>
            <nav className="nav-buttons">
                <button>Features</button>
                <button>Courses</button>
                <button>About</button>
                <button>Contact</button>
                <button className="login-btn">Login</button>
            </nav>
        </header>
    );
}

export default Header;