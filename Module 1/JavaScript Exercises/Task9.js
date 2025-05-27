// HTML Elements
const eventContainer = document.querySelector("#event-list");
const loader = document.querySelector("#loader");

// Mock API endpoint (replace with actual API if needed)
const apiURL = "https://jsonplaceholder.typicode.com/posts"; // mock endpoint for demo

// Using .then() and .catch()
function fetchEventsWithThen() {
  loader.style.display = "block";
  fetch(apiURL)
    .then(response => response.json())
    .then(data => {
      renderMockEvents(data.slice(0, 5)); // Simulate event rendering
    })
    .catch(error => {
      console.error("Fetch failed:", error);
    })
    .finally(() => {
      loader.style.display = "none";
    });
}

// Using async/await
async function fetchEventsAsync() {
  loader.style.display = "block";
  try {
    const response = await fetch(apiURL);
    const data = await response.json();
    renderMockEvents(data.slice(0, 5));
  } catch (error) {
    console.error("Async fetch failed:", error);
  } finally {
    loader.style.display = "none";
  }
}

// Render mock events
function renderMockEvents(events) {
  eventContainer.innerHTML = "";
  events.forEach((event, index) => {
    const card = document.createElement("div");
    card.className = "event-card";
    card.innerHTML = `
      <h3>${event.title}</h3>
      <p>Description: ${event.body}</p>
      <button>Register</button>
    `;
    eventContainer.appendChild(card);
  });
}

// Trigger async fetch
fetchEventsAsync();
