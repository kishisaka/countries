# countries
<pre>
This project is build-able in Android studio. 

Retrofit is used to make the API calls to get the country data through the CountryDao 
and CountryService. The Country data class contains a model of the Country data.  There 
is a presenter (CountryContract.Presenter) and a view contract (CountryContract.View) 
that will post the Country data to a RecylerView using an adapter in the main activity
(CountryActivity) via an observer from the CountryViewModel. The CountryViewModel 
is used to persist the data obtained from the retrofit call. During configuration 
changes (such as an orientation change), the country data and location where the 
user scrolled to is maintained. 

A CountryCallBack is used to deal with both errors and successful responses. 
The callback is convenient in that onResponse() is fired on the main thread 
so as to not require an explicit change from the background thread to 
the main thread. 

Unit tests are provided for the CountryPresenter and retrofit CountryCallback. 

</pre>
