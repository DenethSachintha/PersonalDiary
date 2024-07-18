# Personal Diary App

The Personal Diary App is an Android application designed to allow users to maintain a personal diary. The app features user authentication, secure data storage using Room Database, and an intuitive interface for managing diary entries. Users can create, update, and view or delete their diary entries, as well as update their profile information.

## Features

### User Authentication:
- **Sign Up**: New users can create an account by providing a username and password.
- **Login**: Existing users can log in using their credentials.
- **Logout**: Users can securely log out from the app.

### Profile Management:
- **Update Username**: Users can update their username.
- **Update Password**: Users can change their password, ensuring it meets the security criteria.

### Diary Management:
- **Create Entry**: Users can create new diary entries with titles and content.
- **View Entries**: Users can view their previous diary entries in a list format.
- **Update Entry**: Users can edit their existing diary entries.
- **Delete Entry**: Users can delete diary entries they no longer need.

## Technologies Used

### Programming Language:
- **Kotlin**: For developing the Android application.

### Development Environment:
- **Android Studio**: The official IDE for Android development.

### Database:
- **Room Database**: For local data storage and management.

### Architecture:
- **MVVM (Model-View-ViewModel)**: For a clean separation of concerns and ease of testing.

### UI Components:
- **XML**: For designing the user interface.
- **Material Design**: For a modern and consistent user experience.

## Installation

To install and run the Personal Diary App, follow these steps:

1. Clone the repository:
    ```bash
    git clone https://github.com/DenethSachintha/PersonalDiary.git
    ```
2. Open the project in Android Studio.
3. Build the project and run it on an Android device or emulator.

## Usage

1. Sign up for a new account or log in with your existing credentials.
2. Use the diary management features to create, view, update, or delete diary entries.
3. Update your profile information as needed.

## License

This project is licensed under the MIT License. See the [LICENSE](https://github.com/DenethSachintha/PersonalDiary/blob/master/LICENSE) file for details.

## User Interfaces
<p align="center">
  <img src="https://private-user-images.githubusercontent.com/115437909/350065580-9589a99d-e6b7-435b-b0b9-95c9f82e6750.jpg?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjEzMjI5NTIsIm5iZiI6MTcyMTMyMjY1MiwicGF0aCI6Ii8xMTU0Mzc5MDkvMzUwMDY1NTgwLTk1ODlhOTlkLWU2YjctNDM1Yi1iMGI5LTk1YzlmODJlNjc1MC5qcGc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQwNzE4JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MDcxOFQxNzEwNTJaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1mZmI5MWY4MzUxYjA3YThhNzg2ODI3ZGY5MzVmNjUyMGJlMjQxNmVhOTY3YWEwYjVmOTE1NDliZThhZjNlNjAyJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZhY3Rvcl9pZD0wJmtleV9pZD0wJnJlcG9faWQ9MCJ9.WMYwwCDAaIYKS-aPdH4DB9d51enxEQyRW7evW2enmTI" alt="Login" width="49%" >
  <img src="https://private-user-images.githubusercontent.com/115437909/350065585-1a5acc9c-827f-4160-8b93-6752bcf28ed7.jpg?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjEzMjI5NTIsIm5iZiI6MTcyMTMyMjY1MiwicGF0aCI6Ii8xMTU0Mzc5MDkvMzUwMDY1NTg1LTFhNWFjYzljLTgyN2YtNDE2MC04YjkzLTY3NTJiY2YyOGVkNy5qcGc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQwNzE4JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MDcxOFQxNzEwNTJaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1hOTIwMTc3NmFkNjA3YTEzZTRkZDRiYjAxMjA4MjY5OGYwODc1YzQ4ZWVjMzU4NmU0M2Y0NmY2NDZhYzcyNjFlJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZhY3Rvcl9pZD0wJmtleV9pZD0wJnJlcG9faWQ9MCJ9.wdorv10aGoYK2waZvPdq2wG6vOmqmLGdfMOVwL1VEtk" alt="Register" width="49%">
</p>
<p align="center">
  <img src="https://private-user-images.githubusercontent.com/115437909/350065574-2a6a5b34-cfa9-4bca-b7e6-f540780c2732.jpg?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjEzMjMzNTAsIm5iZiI6MTcyMTMyMzA1MCwicGF0aCI6Ii8xMTU0Mzc5MDkvMzUwMDY1NTc0LTJhNmE1YjM0LWNmYTktNGJjYS1iN2U2LWY1NDA3ODBjMjczMi5qcGc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQwNzE4JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MDcxOFQxNzE3MzBaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0zNGI4OTY4MTU0NjcxNDQwYWY2Njc1MWRlYTUxNjE5N2E5Nzk0NGRiMzEzNWZlMTI0MzVkMjRiY2ZmMDUyOTBhJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZhY3Rvcl9pZD0wJmtleV9pZD0wJnJlcG9faWQ9MCJ9.uwlFy2v-o2lTYtpgwboJ7DW8MgVZUdp5ptY_6K_Lpho" alt="Home" width="49%" >
  <img src="https://private-user-images.githubusercontent.com/115437909/350065599-6c51d7e2-de5f-4d98-b5ce-6facc3cba2d7.jpg?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjEzMjMzNTAsIm5iZiI6MTcyMTMyMzA1MCwicGF0aCI6Ii8xMTU0Mzc5MDkvMzUwMDY1NTk5LTZjNTFkN2UyLWRlNWYtNGQ5OC1iNWNlLTZmYWNjM2NiYTJkNy5qcGc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQwNzE4JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MDcxOFQxNzE3MzBaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT05N2ZjMmUyYTFjM2MzM2Q1YzkxOTEwZTZhZDZkODk0MjcwZGQzMzIyYTZhZGIzNjExODk3NGYzMWVkNjYzODZkJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZhY3Rvcl9pZD0wJmtleV9pZD0wJnJlcG9faWQ9MCJ9.wS_7G38TgYyEJ_pP2XOCVVmLakPqXcmQfNL_DjZLOLE" alt="View" width="49%">
</p>
<p align="center">
  <img src="https://private-user-images.githubusercontent.com/115437909/350065589-fea5109b-8369-441a-b149-cb11fe95a260.jpg?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjEzMjMzNTAsIm5iZiI6MTcyMTMyMzA1MCwicGF0aCI6Ii8xMTU0Mzc5MDkvMzUwMDY1NTg5LWZlYTUxMDliLTgzNjktNDQxYS1iMTQ5LWNiMTFmZTk1YTI2MC5qcGc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQwNzE4JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MDcxOFQxNzE3MzBaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1kMDJhMTZjNGYwZWE0MzE4NTM1YzBkMGExNTcyOGFkMzdkZTk5YmJiOTRkMDQ3Y2VkYWFmMzQ4M2Y3ODE3ODZkJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZhY3Rvcl9pZD0wJmtleV9pZD0wJnJlcG9faWQ9MCJ9.4Ti-K4uDCY87V9DMZ3nYEaQCGVICFgaGhYB-qEjnBAs" alt="Add" width="49%" >
  <img src="https://private-user-images.githubusercontent.com/115437909/350065592-a16b93d9-5c16-4bb3-ab22-6a9028b63ec4.jpg?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjEzMjMzNTAsIm5iZiI6MTcyMTMyMzA1MCwicGF0aCI6Ii8xMTU0Mzc5MDkvMzUwMDY1NTkyLWExNmI5M2Q5LTVjMTYtNGJiMy1hYjIyLTZhOTAyOGI2M2VjNC5qcGc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQwNzE4JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MDcxOFQxNzE3MzBaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT03OGYzOGY2NWRiNjY3ODgyOGQyYTFjY2U4ZGVmN2M2YjUxOGI0Y2RmNDM1MGMxZTEyMWU1ZmM4ZDYzMGQ5ZGU0JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZhY3Rvcl9pZD0wJmtleV9pZD0wJnJlcG9faWQ9MCJ9.PQ8GKBQwhb65uvupixduxf1hkuviHzL_XXHexiwCDN4" alt="Sort" width="49%">
</p>
<p align="center">
  <img src="https://private-user-images.githubusercontent.com/115437909/350065595-bad38794-5762-461c-92b8-c416f955652e.jpg?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjEzMjMzNTAsIm5iZiI6MTcyMTMyMzA1MCwicGF0aCI6Ii8xMTU0Mzc5MDkvMzUwMDY1NTk1LWJhZDM4Nzk0LTU3NjItNDYxYy05MmI4LWM0MTZmOTU1NjUyZS5qcGc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQwNzE4JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MDcxOFQxNzE3MzBaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0yMjViYzQ4MDg2YTcxMTRmYjhiNDdkMTYyMDM0MTE4MTUxNmJmNzEyMGRkYWQ4NjE0YWE4NDIyYzU2ZjU2NGI5JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZhY3Rvcl9pZD0wJmtleV9pZD0wJnJlcG9faWQ9MCJ9.yyaHsnuMzAo0WGs60aj9WfgCpREjZiu2QgVwPiUdqQU" alt="Settings" width="49%" >
</p>