function setSection(section) {
    let sections = document.getElementsByTagName('section');
    for (let i = 0; i < sections.length; i++) {
        sections[i].style.display = 'none';
    }
    document.getElementById(section).style.display = 'block';
}