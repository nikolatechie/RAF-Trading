# RAF Trading

RAF Trading is a university project developed in Kotlin using Android Studio. It is an Android application that facilitates trading activities in a simulated environment. The app includes features such as discovering stocks, managing a portfolio, and viewing user profiles. Followed <a href="https://developer.android.com/topic/architecture">Google's architectural principles</a>.

## Features

The RAF Trading app encompasses the following features:

### Splash/Login Screen

The app begins with a splash screen that checks whether the user is already logged in. If the user is logged in, they are redirected to the main screen; otherwise, they are directed to the login screen.

### Main Screen

The main screen consists of three segments:

1. **Discover**: This segment allows users to search for news and popular stocks related to trading.

2. **Portfolio**: Users can view their portfolio information, including account balance, portfolio value, and a chart showing the history of portfolio values. It also displays a list of purchased stocks, including the quantity of each stock in the portfolio.

3. **Profile**: Users can view their profile information and log out from the application.

### Discover Screen

The Discover screen provides the following functionalities:

- News section: Displays news cards in a horizontal list, showing the date, title, and image retrieved from the provided API endpoint. Clicking on a card opens the corresponding website.
- Popular stocks section: Displays stock cards in a vertical list, showing the stock symbol, name, last price, and a chart representing the stock's performance. The chart color is set to green if the change from the previous close is positive and red otherwise. Clicking on a card opens the detailed view of the stock.

### Portfolio Screen

The Portfolio screen offers the following features:

- Displays basic portfolio information, including the user's initial account balance and portfolio value.
- Tracks and displays the history of portfolio values over time using a chart.
- Lists the purchased stocks, including the quantity of each stock in the portfolio.
- Clicking on a stock opens the detailed view of the stock.

### User Profile Screen

The User Profile screen presents the user's profile information. It includes a logout button that logs the user out of the application. The application remembers the user's state upon subsequent logins, including their portfolio.

### Detailed Stock View

The Detailed Stock View screen provides comprehensive information about a specific stock. It retrieves data directly from the server. The screen displays the stock symbol, current value, and a chart showing the stock's history. Additional details such as OPEN, BID, CLOSE, ASK, DIV YIELD, P/E, MKT CAP, EPS, EBIT, and BETA are also shown. The screen includes Buy and Sell buttons, which lead to the Buy/Sell screen.

### Buy/Sell Screen

The Buy/Sell screen enables users to buy or sell stocks. It provides the following functionalities:

- Buy: Users specify the stock and the quantity they want to purchase. The screen calculates the total value in terms of shares and displays the user's account balance. Clicking the Buy button adds the stock to the user's portfolio, updates the account balance, and adjusts the portfolio value. These changes are saved locally.
- Sell: Users can choose to sell a specific number of shares or all of their shares. The screen displays the user's current stock quantity. Clicking the Sell button removes the sold shares from the portfolio, updates the account balance, and adjusts the portfolio value. These changes are also saved locally.

## Technologies Used

The RAF Trading app utilizes the following technologies:

- **Kotlin**: The programming language used for development.
- **Android Studio**: The integrated development environment (IDE) used for Android app development.
- **Room**: A local database library used for data persistence.
- **Retrofit**: A networking library used for communication with the server.

## Getting Started

To run the RAF Trading app locally, follow these steps:

1. Clone the repository.
2. Open the project in Android Studio.
3. Build and run the app on an Android emulator or physical device.
