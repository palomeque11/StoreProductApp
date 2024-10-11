# Application Description
The WalmartApp is an Android application designed to provide users with a seamless shopping experience by displaying a comprehensive list of products sourced from an external API. Leveraging modern architecture and best practices, this application incorporates features such as:

## Architecture: 
Utilizing the MVVM pattern with clean architecture for better separation of concerns, enhancing testability and maintainability.

## Dependency Injection: 
Implementing Dagger Hilt to manage dependencies, ensuring a clean and efficient codebase while facilitating easier testing.

## Room Database: 
Storing product data locally using the Room persistence library, enabling offline access and improving app responsiveness.

## API Integration: 
Fetching product data from a remote service using Retrofit, providing real-time updates to the product list.

## State Management: 
Employing LiveData and Flow for managing UI state and updates, allowing for reactive and efficient UI updates based on data changes.

## Custom Views: 
Implementing custom UI components, such as a circular progress indicator, to enhance user experience and visual appeal.

## Error Handling: 
Utilizing a sealed class structure for managing application states, such as loading, success, and error states, ensuring clear communication with the user.
