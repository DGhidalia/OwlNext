<h1> OwlNext </h1>

The goal of this project is primarly to be able to give the rate between two currency by typing them in the console.</br>
The available pairs are : </br>
-EURCHF</br>
-EURGBP</br>
-EURUSD</br>
-USDCHF</br>
-USDEUR</br>
-USDGBP</br>

The application stores each pairs' rate every 10 minutes.</br>
Then the information is still available by making a GET request at the URL : </br>
http://localhost:8124/rate/[CurrencyPair]  </br>
Where [CurrencyPair] is one of the pair listed before.

It is also possible to get the last stored rate by typing the currency pair in command line.
