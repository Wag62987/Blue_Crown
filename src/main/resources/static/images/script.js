function performSearch() {
    const query = document.getElementById('search').value;
    alert('You searched for: ' + query);
}

// Function to switch between pages
function showPage(pageId) {
    const pages = document.querySelectorAll('.container');
    pages.forEach(page => {
        page.style.display = 'none'; // Hide all pages
    });
    document.getElementById(pageId).style.display = 'block'; // Show the selected page
}

// Event listeners for navigation links
document.querySelectorAll('.navbar a').forEach(link => {
    link.addEventListener('click', function(event) {
        event.preventDefault(); // Prevent default anchor behavior
        const pageId = this.getAttribute('href').substring(1); // Get the page ID from href
        showPage(pageId); // Show the selected page
    });
});

// Show the home page by default
showPage('home');