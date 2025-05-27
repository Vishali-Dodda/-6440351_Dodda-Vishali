// Using const for event name and date (immutable)
const eventName = "Community Music Festival";
const eventDate = "2025-06-15";

// Using let for available seats (mutable)
let availableSeats = 100;

// Function to register a seat (decrement availableSeats)
function registerSeat() {
    if (availableSeats > 0) {
        availableSeats--; // decrement seats by 1
        console.log(`Registered successfully! Seats left: ${availableSeats}`);
    } else {
        console.log("Sorry, no seats available.");
    }
}

// Concatenate event details with template literals
const eventInfo = `Event: ${eventName} | Date: ${eventDate} | Seats Available: ${availableSeats}`;
console.log(eventInfo);

// Simulate registrations
registerSeat(); // availableSeats becomes 99
registerSeat(); // availableSeats becomes 98
