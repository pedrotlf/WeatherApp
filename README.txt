This project was built using gradle 7.4 and embedded JDK from Android Studio Bumblebee

To run this project, just open it using Android Studio :)

SOME OBSERVATIONS:

- In order to follow some of the SOLID principles, I chose to use the MVVM and Clean architectures as well as a Dependency Injection (Dagger Hilt).
- For network request I used Retrofit. I believe it's the easiest to use.
- For observables/observers I used LiveData/Flow over RxJava. RxJava is a complex feature with a bad learning curve if compared to LiveData or Flow.

