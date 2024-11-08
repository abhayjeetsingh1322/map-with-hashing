# Map with Hashing Implementation

## Description
The **Map with Hashing Implementation** is a Java project that builds a `Map` data structure using a hash table. The hash table is represented as an array of `Map` objects, where each "bucket" in the array is an instance of a different `Map` implementation. This approach leverages hashing for efficient storage and retrieval while maintaining a focus on modularity and performance.

This project emphasizes:
- Kernel-level programming with hashing.
- Designing robust, specification-based test plans.
- Debugging interconnected methods while maintaining a strict representation invariant.

---

## Objectives
1. Implement a `MapKernel` interface with hashing layered on top of an array of `Map` objects.
2. Develop constructors and kernel methods, including:
   - `add`
   - `remove`
   - `removeAny`
   - `value`
   - `hasKey`
   - `size`
3. Design and execute a systematic test plan for all constructors and kernel methods.

---

## Features
### 1. Efficient Map Representation
- Uses a hash table with each bucket implemented as a separate `Map` object.
- Hash table size is configurable, allowing for flexibility in testing and performance optimization.

### 2. Hashing Algorithm
- Private `mod` method handles hashing and ensures uniform distribution across buckets.

### 3. Comprehensive Testing
- Includes test cases for:
  - Constructors
  - Kernel methods
  - Multiple hash table sizes
- Supports extending tests to additional hash table sizes.

---

## Technologies Used
- **Java**: For implementing the hashing-based `Map` and kernel methods.
- **JUnit**: For unit testing and systematic test execution.

---

## How to Run
### Prerequisites
- Java Development Kit (JDK)
- Any Java-compatible IDE or terminal

### Steps
1. Clone the repository:
   ```bash
   git clone [repository URL]
