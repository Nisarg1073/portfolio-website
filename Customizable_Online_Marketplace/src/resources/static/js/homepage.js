document.addEventListener("DOMContentLoaded", function () {
    fetchCategories();
    window.addEventListener("resize", adjustCategoriesDisplay);
});

    function fetchCategories() {
    const categoriesDiv = document.getElementById("categories");
    if (!categoriesDiv) {
    console.error("Categories container not found.");
    return;
}

    fetch("/categories")
    .then(response => {
    if (!response.ok) {
    throw new Error(`HTTP error! Status: ${response.status}`);
}
    return response.json();
})
    .then(data => {
    categoriesDiv.innerHTML = ""; // Clear previous content

    if (!Array.isArray(data) || data.length === 0) {
    console.warn("No categories found.");
    categoriesDiv.innerHTML = "<span>No categories available</span>";
    return;
}

    // Append categories to the bottom bar
    data.forEach(category => {
    let categoryLink = document.createElement("a");
    categoryLink.href = `/category/${encodeURIComponent(category)}`;
    categoryLink.textContent = category;
    categoryLink.classList.add("category-item");
    categoriesDiv.appendChild(categoryLink);
});

    adjustCategoriesDisplay();
})
    .catch(error => {
    console.error("Error loading categories:", error);
    categoriesDiv.innerHTML = "<span>Error loading categories</span>";
});
}

    // Adjust category display based on screen width
    function adjustCategoriesDisplay() {
    const categoriesDiv = document.getElementById("categories");
    if (!categoriesDiv) return;

    const allCategories = [...categoriesDiv.children];
    const maxVisible = Math.max(3, Math.floor(window.innerWidth / 150)); // Ensures at least 3 visible

    allCategories.forEach((cat, index) => {
    cat.style.display = index < maxVisible ? "inline-block" : "none";
});

    categoriesDiv.style.justifyContent = "flex-start"; // Ensures categories are aligned left
}
