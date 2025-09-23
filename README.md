# Wikipedia Revision Tracker

## Project Summary
A Java desktop application that retrieves and displays the most recent revisions of Wikipedia articles in the command terminal. The program retrives information from the Wikipedia API and sorts them in reverse order by timestamp. Finally, it outputs the information nicely formatted to the command line. 

## Team Members
- Jack Wagner (Added README, implemented JSON interpretation)
- Ashelyn Reilly (Repository owner, Handled user Interaction and JSON Creation)

## Build Instructions
1. Clone the repository
2. Ensure Java 11+ is installed
3. Run Main method in the Controller Class

## Usage
Run the controller and enter a Wikipedia article name when prompted. The program will display the 4 most recent revisions with timestamps and usernames.

## Features
- Fetches recent revisions from Wikipedia API
- Handles article redirects
- Sorts revisions chronologically (newest first)
- Error handling for network issues and invalid articles
- Comprehensive tests

## Dependencies
- Java 11+
- Gradle 8.14
- JUnit 5 (testing)
- JSONPath (JSON parsing)
