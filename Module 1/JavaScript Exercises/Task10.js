// Sample event list
const events = [
    { name: "Music Night", date: "2025-06-10", seats: 5, category: "Music" },
    { name: "Yoga Session", date: "2025-06-12", seats: 3, category: "Health" },
    { name: "Art Workshop", date: "2025-06-15", seats: 4, category: "Art" }
  ];
  
  // Function to display events with default parameter
  const displayEvents = (eventList = []) => {
    eventList.forEach(({ name, date, seats, category }) => {
      console.log(`Event: ${name} | Date: ${date} | Seats: ${seats} | Category: ${category}`);
    });
  };
  
  // Clone event list using spread operator
  const clonedEvents = [...events];
  
  // Filter Music events from cloned list
  const musicEvents = clonedEvents.filter(({ category }) => category === "Music");
  
  // Display filtered events
  displayEvents(musicEvents);
  