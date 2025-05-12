Elevator System Simulation

A fullstack application simulating the operation of an elevator system in a building, with multiple elevators, real-time updates, and user interaction. Built with **Spring Boot** (backend) and **Angular** (frontend).

---

## Project Structure
/backend → Spring Boot application
/frontend → Angular application


## Rest endpoints 

GET /api/elevators

POST /api/elevators/call

POST /api/elevators/{id}

## Functionality Overview

- Multiple elevators with independent state and logic
- Call elevators from floors (up/down)
- Select destination floors from inside elevators
- Real-time updates via WebSocket (STOMP)
- UI: grid-based visual representation of elevators and building


## How to improve

### Backend
- In-memory state  - use Redis for fast, shared, durable data storage
- naive elevator assignment - could be improved according to needs 
- only one websocket connection - Redis Pub/Sub instead
- We could add a queueing system such as Kafka or RabbitMQ 
- spring WebFlux for reactive non-blocking I/O
- add tests
- add error handling
- add Open Api specification
- contenerization
- orchestration
- monitoring

### Frontend 
- frontend is not scalable - should be designed to meet specific needs, virtual scrolling could help with large sets 
- could be prettier (small elevator buttons)
- add mobile device support
- add state management like ngrx 
- add tests
- add authentication
- contenerization
- error handling
- add nginx