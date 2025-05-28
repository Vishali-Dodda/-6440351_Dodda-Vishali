-- Table Creation 

CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    city VARCHAR(100) NOT NULL,
    registration_date DATE NOT NULL
);

CREATE TABLE Events (
    event_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    city VARCHAR(100) NOT NULL,
    start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL,
    status ENUM('upcoming', 'completed', 'cancelled'),
    organizer_id INT,
    FOREIGN KEY (organizer_id) REFERENCES Users(user_id)
);

CREATE TABLE Sessions (
    session_id INT PRIMARY KEY AUTO_INCREMENT,
    event_id INT,
    title VARCHAR(200) NOT NULL,
    speaker_name VARCHAR(100) NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

CREATE TABLE Registrations (
    registration_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    event_id INT,
    registration_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

CREATE TABLE Feedback (
    feedback_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    event_id INT,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comments TEXT,
    feedback_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

CREATE TABLE Resources (
    resource_id INT PRIMARY KEY AUTO_INCREMENT,
    event_id INT,
    resource_type ENUM('pdf','image','link'),
    resource_url VARCHAR(255) NOT NULL,
    uploaded_at DATETIME NOT NULL,
    FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

--Exercise 1: User Upcoming Events

SELECT u.full_name, e.title, e.start_date
FROM Users u
JOIN Registrations r ON u.user_id = r.user_id
JOIN Events e ON r.event_id = e.event_id
WHERE e.status = 'upcoming' AND u.city = e.city
ORDER BY e.start_date;

--Exercise 2: Top Rated Events

SELECT e.title, AVG(f.rating) AS avg_rating
FROM Events e
JOIN Feedback f ON e.event_id = f.event_id
GROUP BY e.event_id
HAVING COUNT(f.feedback_id) >= 10
ORDER BY avg_rating DESC;

--Exercise 3: Inactive Users (last 90 days)

SELECT u.full_name
FROM Users u
LEFT JOIN Registrations r ON u.user_id = r.user_id AND r.registration_date >= CURDATE() - INTERVAL 90 DAY
WHERE r.registration_id IS NULL;

--Exercise 4: Peak Session Hours (10 AM - 12 PM)

SELECT e.title, COUNT(*) AS peak_sessions
FROM Sessions s
JOIN Events e ON s.event_id = e.event_id
WHERE TIME(s.start_time) BETWEEN '10:00:00' AND '11:59:59'
GROUP BY e.event_id;

--Exercise 5: Most Active Cities

SELECT u.city, COUNT(DISTINCT r.user_id) AS users_count
FROM Users u
JOIN Registrations r ON u.user_id = r.user_id
GROUP BY u.city
ORDER BY users_count DESC
LIMIT 5;

--Exercise 6: Event Resource Summary

SELECT e.title,
       SUM(resource_type = 'pdf') AS pdfs,
       SUM(resource_type = 'image') AS images,
       SUM(resource_type = 'link') AS links
FROM Events e
LEFT JOIN Resources r ON e.event_id = r.event_id
GROUP BY e.event_id;

--Exercise 7: Low Feedback Alerts

SELECT u.full_name, e.title, f.comments, f.rating
FROM Feedback f
JOIN Users u ON f.user_id = u.user_id
JOIN Events e ON f.event_id = e.event_id
WHERE f.rating < 3;

--Exercise 8: Sessions per Upcoming Event

SELECT e.title, COUNT(s.session_id) AS session_count
FROM Events e
LEFT JOIN Sessions s ON e.event_id = s.event_id
WHERE e.status = 'upcoming'
GROUP BY e.event_id;

--Exercise 9: Organizer Event Summary

SELECT u.full_name, e.status, COUNT(e.event_id) AS event_count
FROM Events e
JOIN Users u ON e.organizer_id = u.user_id
GROUP BY u.user_id, e.status;

--Exercise 10: Feedback Gap

SELECT e.title
FROM Events e
JOIN Registrations r ON e.event_id = r.event_id
LEFT JOIN Feedback f ON r.event_id = f.event_id
WHERE f.feedback_id IS NULL
GROUP BY e.event_id;

--Exercise 11: Daily New User Count (last 7 days)

SELECT registration_date, COUNT(*) AS new_users
FROM Users
WHERE registration_date >= CURDATE() - INTERVAL 7 DAY
GROUP BY registration_date;

--Exercise 12: Event with Maximum Sessions

SELECT e.title, COUNT(s.session_id) AS session_count
FROM Events e
JOIN Sessions s ON e.event_id = s.event_id
GROUP BY e.event_id
ORDER BY session_count DESC
LIMIT 1;

--Exercise 13: Average Rating per City

SELECT e.city, AVG(f.rating) AS avg_rating
FROM Feedback f
JOIN Events e ON f.event_id = e.event_id
GROUP BY e.city;

--Exercise 14: Most Registered Events

SELECT e.title, COUNT(r.user_id) AS registration_count
FROM Events e
JOIN Registrations r ON e.event_id = r.event_id
GROUP BY e.event_id
ORDER BY registration_count DESC
LIMIT 3;

--Exercise 15: Event Session Time Conflict

SELECT s1.event_id, s1.title AS session1, s2.title AS session2
FROM Sessions s1
JOIN Sessions s2 ON s1.event_id = s2.event_id AND s1.session_id < s2.session_id
WHERE s1.start_time < s2.end_time AND s2.start_time < s1.end_time;

--Exercise 16: Unregistered Active Users

SELECT full_name
FROM Users
WHERE registration_date >= CURDATE() - INTERVAL 30 DAY
AND user_id NOT IN (SELECT user_id FROM Registrations);

--Exercise 17: Multi-Session Speakers

SELECT speaker_name, COUNT(*) AS session_count
FROM Sessions
GROUP BY speaker_name
HAVING session_count > 1;

--Exercise 18: Resource Availability Check

SELECT title
FROM Events
WHERE event_id NOT IN (SELECT DISTINCT event_id FROM Resources);

--Exercise 19: Completed Events with Feedback Summary

SELECT e.title, COUNT(r.user_id) AS total_registrations, AVG(f.rating) AS avg_rating
FROM Events e
LEFT JOIN Registrations r ON e.event_id = r.event_id
LEFT JOIN Feedback f ON e.event_id = f.event_id
WHERE e.status = 'completed'
GROUP BY e.event_id;

--Exercise 20: User Engagement Index

SELECT u.full_name,
       COUNT(DISTINCT r.event_id) AS events_attended,
       COUNT(DISTINCT f.feedback_id) AS feedback_given
FROM Users u
LEFT JOIN Registrations r ON u.user_id = r.user_id
LEFT JOIN Feedback f ON u.user_id = f.user_id
GROUP BY u.user_id;

--Exercise 21: Top Feedback Providers

SELECT u.full_name, COUNT(f.feedback_id) AS feedbacks
FROM Feedback f
JOIN Users u ON f.user_id = u.user_id
GROUP BY u.user_id
ORDER BY feedbacks DESC
LIMIT 5;

--Exercise 22: Duplicate Registrations Check

SELECT user_id, event_id, COUNT(*) AS reg_count
FROM Registrations
GROUP BY user_id, event_id
HAVING reg_count > 1;

--Exercise 23: Registration Trends

SELECT DATE_FORMAT(registration_date, '%Y-%m') AS month, COUNT(*) AS registrations
FROM Registrations
WHERE registration_date >= CURDATE() - INTERVAL 12 MONTH
GROUP BY month
ORDER BY month;

--Exercise 24: Average Session Duration per Event

SELECT e.title,
       AVG(TIMESTAMPDIFF(MINUTE, s.start_time, s.end_time)) AS avg_duration
FROM Sessions s
JOIN Events e ON s.event_id = e.event_id
GROUP BY e.event_id;

--Exercise 25: Events Without Sessions

SELECT title
FROM Events
WHERE event_id NOT IN (SELECT DISTINCT event_id FROM Sessions);
