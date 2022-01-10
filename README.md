<h1> OwlNext </h1>

In order to use this project, check your maven dependencies and install them if needed. Then, launch the main in the OwlNext.java class.

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

The missing part is having the get on the api that return the best and worst rate for a given currency pair on a particular date.
My issue on this was the date format, converting a String into Date and then comparing the two of them. 
My idea was to make the conversion and then compare using native java.
It was also possible to manage a database on a local server using JPA.