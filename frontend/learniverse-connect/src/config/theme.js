function setTheme(theme) {
  document.body.dataset.theme = theme;
  localStorage.setItem('theme', theme);
}

function initTheme() {
  const stored = localStorage.getItem('theme');
  if (stored) {
    setTheme(stored);
  } else {
    const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;
    setTheme(prefersDark ? 'dark' : 'light');
  }
}

initTheme();

function toggleTheme() {
  const current = document.body.dataset.theme;
  setTheme(current === 'dark' ? 'light' : 'dark');
}

export { toggleTheme };
