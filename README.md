# NewsFeedAggregator

NewsFeedAggregator is a news aggregation app that fetches news articles from the [NewsAPI](https://newsapi.org/) service and presents them in a user-friendly interface. This app utilizes the Paging3 library to implement features such as infinite scrolling, caching, and offline support.

## Features

- **Infinite Scrolling**: The app supports infinite scrolling, allowing users to seamlessly load more news articles as they scroll through the list.
- **Caching**: The app utilizes a local database for caching news articles, ensuring a smooth user experience even in offline mode.
- **Offline Support**: Users can access previously loaded news articles even without an active internet connection, thanks to the caching mechanism.
- **Customizable Sources**: The app fetches news articles from the NewsAPI service, which provides a wide range of news sources. Users can customize the sources to display news from their preferred providers.

## Dependencies

- [Paging3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview): The Paging3 library is used to implement pagination and infinite scrolling for fetching news articles.
- [Room](https://developer.android.com/training/data-storage/room): Room is utilized as a local database for caching news articles.
- [Retrofit](https://square.github.io/retrofit/): Retrofit is used for making API requests to the NewsAPI service.
- [Glide](https://github.com/bumptech/glide): Glide library is used for efficient image loading and caching.

## Setup

To run the app locally, follow these steps:

1. Clone this repository to your local machine using the following command:
git clone https://github.com/your-username/NewsFeedAggregator.git

2. Open the project in Android Studio.

3. Obtain an API key from [NewsAPI](https://newsapi.org/) by signing up for an account.

4. Add your API key in local.properties file in a variable `API_KEY`.

5. Build and run the app on an emulator or physical device.

## Contributing

Contributions are welcome! If you encounter any issues or have suggestions for improvements, please open an issue on the GitHub repository. If you'd like to contribute code, you can open a pull request with your changes.

When contributing to this project, please ensure that your code adheres to the existing coding style and conventions. Also, make sure to test your changes thoroughly before submitting a pull request.

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgements

- [NewsAPI](https://newsapi.org/): The NewsAPI service provides the data source for this app.
- [Paging3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview): The Paging3 library is used to implement pagination and infinite scrolling.
- [Room](https://developer.android.com/training/data-storage/room): Room is used as a local database for caching news articles.
- [Retrofit](https://square.github.io/retrofit/): Retrofit library is used for making API requests to fetch news articles.
- [Glide](https://github.com/bumptech/glide): Glide library is used for efficient image loading and caching.

## Contact

For any inquiries or feedback, please contact [vinodghai713@gmail.com](mailto:vinodghai713@gmail.com).
