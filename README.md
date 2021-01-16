
# SplashApp
An SplashApp which showcases the collection of the photos from the [Unsplash Api](https://unsplash.com/documentation).



## About
- It contains 3 screens
- First screen showcases the collection of photos with total number of photos on the description
- It Also calculates the total number of photos from each collections and show them at top
- Second screen contains the specific collection when you tap on it with top 6 curated ones
- At the bottom of the second screen, where you can access the url of the collection
- On third screen you have the photos in full screen mode when you click on certain someone
- Supports DarkMode on android 10+ devices.

## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Glide](https://bumptech.github.io/glide/) - A fast and efficient image loading library for Android focused on smooth scrolling.
- [Moshi Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/moshi) - A Converter which uses Moshi for serialization to and from JSON.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android

### Apart from this the whole app is build using custom DI no external libraries are used.


### Some edge cases that will come in future
- [x] Handle Network State Changes
- [ ] Pagination
- [ ] Modern UI
- [ ] Unit test/Integrated Testing
