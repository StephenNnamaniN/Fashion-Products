# Fashion Products App

## Overview
This project is a simple Android application that loads and displays a list of products.
It was implemented with a focus on clean, scalable architecture, testability, and accessibility.

## Tech Stack
- Kotlin
- Jetpack Compose
- Coroutines
- StateFlow
- JUnit
- Turbine
- Clean Architecture

## Architecture
The project follows a layered architecture:

- `domain`  
  Contains core business models, repository contracts, result types, and use cases.

- `data`  
  Contains repository implementations and data-source handling.

- `presentation`  
  Contains UI state, ViewModel, and Compose screens.

This separation keeps business logic independent from Android framework concerns and makes the app easier to test and evolve.

## TDD Approach
A test-driven development approach was used for the core application logic.

The workflow followed:
1. Write a failing test
2. Implement the smallest amount of code required to pass
3. Refactor safely with test coverage in place

TDD was primarily applied to:
- `GetProductsUseCase`
- `ProductViewModel`

For the ViewModel, Turbine was used to verify the emitted `StateFlow` sequence, such as:
- `Idle -> Loading -> Success`
- `Idle -> Loading -> Error`

## State Management
`StateFlow` was used in the ViewModel to expose UI state in a lifecycle-friendly and testable way.

UI states are represented using a sealed interface:
- `Idle`
- `Loading`
- `Success`
- `Error`

## Accessibility Considerations
Accessibility was considered as part of the UI implementation.

Measures included:
- meaningful semantic descriptions for loading and product content
- explicit text for stock state rather than relying only on visual styling
- Material components to support adequate touch target sizing
- readable typography to better support text scaling

Example:
Each product row provides a spoken summary including product name, price, and stock status.

## Assumptions
The following assumptions were made:
- a flat list of products is sufficient for the exercise
- pagination is not required
- prices are already formatted for display
- product identifiers are unique and stable
- offline caching is out of scope unless explicitly requested

## Key Considerations
The main implementation considerations were:
- keeping business logic independent of framework code
- ensuring ViewModel state transitions are testable
- using repository abstraction to support scalability
- making error handling explicit and easy to map to UI state
- keeping the UI simple and accessible

## Trade-offs / What I Would Improve With More Time
With more time, I would consider:
- adding repository integration tests
- adding dependency injection with Hilt or Koin
- introducing design system components
- supporting pull-to-refresh
- adding offline caching
- improving localisation and string resource coverage

## How to Run
1. Clone the repository
2. Open in Android Studio
3. Sync Gradle
4. Run the app on an emulator or device

## Running Tests
- Unit tests: `./gradlew test`
- Instrumented / Compose UI tests: `./gradlew connectedAndroidTest`
