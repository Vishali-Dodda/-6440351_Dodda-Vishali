// Initial array of community events
let communityEvents = [
    { name: "Music Night", category: "Music", date: "2025-06-10" },
    { name: "Yoga Session", category: "Health", date: "2025-06-12" },
  ];
  
  // Add new events using .push()
  communityEvents.push(
    { name: "Rock Concert", category: "Music", date: "2025-07-01" },
    { name: "Baking Workshop", category: "Cooking", date: "2025-06-20" }
  );
  
  // Filter only music events
  const musicEvents = communityEvents.filter(event => event.category === "Music");
  console.log("Music Events:", musicEvents);
  
  // Use .map() to format display cards
  const displayCards = communityEvents.map(event => `Workshop on ${event.name}`);
  console.log("Display Cards:", displayCards);
  