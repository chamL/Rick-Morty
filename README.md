# Rick and Morty Android App

This project is an Android application developed as part of an exam in Android Programming (PGR208).  
The app allows users to retrieve character data from the Rick and Morty API and manage locally created characters using Room database.

## Features

- Fetch characters from the Rick and Morty API (Retrofit)
- Display characters in a single-column list (LazyColumn)
- Search characters by name
- Filter characters by status (Alive, Dead, Unknown)
- View detailed information about each character
- Create new characters (stored locally in Room)
- Edit saved characters
- Delete saved characters
- View all locally saved characters
- Loading states, error handling, and empty state feedback

---

## Technologies Used

- **Kotlin**
- **Jetpack Compose (Material3)**
- **MVVM Architecture**
- **Retrofit** (HTTP requests)
- **Room Database** (local storage)
- **Compose Navigation**
- **Coil** (image loading)

---

## Architecture

The project follows the **MVVM (Model–View–ViewModel)** pattern:

- **UI Layer** – Jetpack Compose screens and reusable components  
- **ViewModel Layer** – Handles state and business logic  
- **Repository Layer** – Manages data sources (API + Room)  
- **Data Layer** – Retrofit API + Room database  

This structure ensures separation of concerns and improves scalability and maintainability.

---

## Project Structure
