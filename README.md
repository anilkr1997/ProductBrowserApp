# Product Browser App — KMM
![App Screenshot](https://user-images.githubusercontent.com/12345678/abcd1234-example.png)

## Overview / Summary of business requirements
Revest requires a cross-platform product catalog prototype to evaluate feasibility of a KMM-based mobile product browsing experience. The app demonstrates the following business requirements:

- View a list of products showing **name, price, and thumbnail**.
- Tap a product to view **detailed information**: title, description, brand, price, rating.
- **Search** products by keyword using the public API.
- [Optional] Filter products by category (extension point).

The prototype is intended to be simple, maintainable, and demonstrate Clean Architecture patterns across Android and iOS using Kotlin Multiplatform.

---

## Project Overview / Architecture

**High level**
- Multi-module Kotlin Multiplatform project:
  - `:shared` — the mobile-shared KMM module (data, domain, presentation, DI factory, use-cases, mapping, networking). Contains platform-agnostic business logic and ViewModels.
  - `:androidApp` — Android application (Jetpack Compose) consuming the shared module.
  - `:iosApp` — Xcode/SwiftUI project that can consume the `:shared` XCFramework (instructions provided).

**Clean Architecture layers (inside `shared`)**
- **Data**: DTOs (`ProductDto`), Ktor `ProductApiImpl`, repository implementation `ProductRepositoryImpl`.
- **Domain**: domain models (`Product`), repository interfaces, use-cases (`GetProductsUseCase`, `SearchProductsUseCase`, `GetProductDetailUseCase`).
- **Presentation**: `ProductsViewModel` using `StateFlow` and coroutine scope for UI consumption.
- **DI**: simple factory (`createProductsViewModel()`) that wires engine → client → api → repo → use-cases → viewmodel.

**Key libraries**
- Kotlin Multiplatform (Kotlin 1.9+)
- Ktor client (OkHttp engine on Android; Darwin on iOS)
- kotlinx.serialization (JSON, `ignoreUnknownKeys = true`)
- kotlinx.coroutines and `StateFlow` (presentation)
- Jetpack Compose for Android UI
- kotlin.test for common unit tests

---

## How to build & run

### Prerequisites
- **Android:** Android Studio Flamingo / Electric Eel or newer (with Kotlin support), JDK 17.
- **iOS:** Xcode 14+ (or as required by your local environment) — for XCFramework integration.
- Gradle and the Kotlin plugin should be available in Android Studio; no extra CLI tools required unless you use the commands below.

### Android — run locally
1. Open Android Studio and open the project root `kmm-product-browser`.
2. Allow Gradle to sync (it will download Ktor, kotlinx.serialization, etc.).
3. Run the `androidApp` configuration on an emulator or device.
  - Or from CLI:
    ```bash
    ./gradlew :androidApp:assembleDebug
    ./gradlew :androidApp:installDebug   # requires connected device or emulator
    ```

### iOS — integrate shared module
There are two common integration approaches:

**A. XCFramework (recommended)**
1. Build the XCFramework from the `shared` module:
   ```bash
   ./gradlew :shared:assembleXCFramework

The artifact will be in shared/build/XCFramework (or similar path). Add the generated .xcframework to your Xcode project:
Add framework to Frameworks, Libraries, and Embedded Content (Embed & Link).

Add a small Swift adapter (or use the adapter provided in shared) to subscribe to StateFlow updates (shared code includes example callback adapters).

Use a SwiftUI ObservableObject wrapper to expose products and isLoading to SwiftUI views.

B. Compose Multiplatform host (experimental)

Host Compose UI on iOS directly. This is more setup-heavy and not required for this prototype.

**Tests**

Run shared module tests (common JVM tests):
   ```bash
    ./gradlew :shared:test
    
The tests include:

DTO → domain mapping tests

ProductRepositoryImpl behaviour using a fake API

GetProductsUseCase and SearchProductsUseCase tests
