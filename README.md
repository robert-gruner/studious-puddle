# Studious Puddle

This is a Kotlin Multiplatform project targeting `Desktop` & `Server`.

# Structure
* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - `desktopMain` is for code that’s for the `desktop` target.
  
> Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
  For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
  `iosMain` would be the right folder for such calls.

* `/server` is for the Ktor server application.

* `/shared` is for the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the platform-specific folders here too.

# Functionality
## Database
- Schema: TODO Create a database schema to store the pets, pet stores and users.
- `Redis` serves as cache for JSON/NoSQL data

## API Implementation
- use OpenAPI v3
- based on `Ktor` framework

## Testing
- Unit testing via JUnit
- Server testing: https://ktor.io/docs/server-testing.html
- TODO: https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-run-tests.html

## Frontend
- Is available for at least `macOS` desktop platform
- Talks to the API via HTTP
- Create a nice UI for the Pet Catalog with a list and a detail-view.
- The list view can be filtered with the given endpoints.

# References
* PetStore API source: https://editor.swagger.io/?url=https://raw.githubusercontent.com/swagger-api/swagger-petstore/master/src/main/resources/openapi.yaml
* Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)