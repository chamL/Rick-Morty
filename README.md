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

ui/
screens/
components/
viewmodel/
data/
api/
local/
repository/
navigation/
theme/


---

## Setup Instructions

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle
4. Run on emulator or physical device (min SDK 24)

> Note: `local.properties` is not included in the repository. Android Studio will generate it automatically.

---

## API Used

Rick and Morty API  
https://rickandmortyapi.com/

---

## Author

Developed as part of an academic exam project in Android development.
