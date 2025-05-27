// Event constructor function
function Event(name, date, seats, category) {
    this.name = name;
    this.date = date;
    this.seats = seats;
    this.category = category;
  }
  
  // Add checkAvailability method to prototype
  Event.prototype.checkAvailability = function () {
    const isUpcoming = new Date(this.date) >= new Date();
    return isUpcoming && this.seats > 0;
  };
  
  // Create event instances
  const event1 = new Event("Music Festival", "2025-08-15", 50, "Entertainment");
  const event2 = new Event("Health Camp", "2024-04-10", 0, "Health");
  
  // Check availability
  console.log(`${event1.name} available:`, event1.checkAvailability());
  console.log(`${event2.name} available:`, event2.checkAvailability());
  
  // List object keys and values
  console.log("Event 1 details:");
  Object.entries(event1).forEach(([key, value]) => {
    console.log(`${key}: ${value}`);
  });
  