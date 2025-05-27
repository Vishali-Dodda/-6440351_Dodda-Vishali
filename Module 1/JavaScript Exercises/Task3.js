// Sample events array
const events = [
    { name: "Community Cleanup", date: "2025-06-30", seats: 10 },
    { name: "Charity Run", date: "2024-05-01", seats: 0 }, // past event or full
    { name: "Book Fair", date: "2025-07-15", seats: 5 },
  ];
  
  // Function to check if an event is upcoming
  function isUpcoming(eventDate) {
    const today = new Date();
    return new Date(eventDate) >= today;
  }
  
  // Display valid events
  events.forEach(event => {
    if (isUpcoming(event.date) && event.seats > 0) {
      console.log(`Upcoming Event: ${event.name} on ${event.date} - Seats: ${event.seats}`);
    } else {
      console.log(`Event "${event.name}" is not available for registration.`);
    }
  });
  
  // Registration function with error handling
  function register(event) {
    try {
      if (!isUpcoming(event.date)) {
        throw new Error("Cannot register for past events.");
      }
      if (event.seats <= 0) {
        throw new Error("No seats available.");
      }
      // If all good, decrement seats
      event.seats--;
      console.log(`Registered successfully for ${event.name}. Seats left: ${event.seats}`);
    } catch (error) {
      console.error(`Registration failed for ${event.name}: ${error.message}`);
    }
  }
  
  // Example registrations
  register(events[0]); // valid registration
  register(events[1]); // should throw error (past or full)
  register(events[2]); // valid registration
  