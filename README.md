# countries
<pre>
This project is build-able in Android studio. 

Retrofit is used to make the API calls to get the country data through the CountryDao and CountryService.
The Country data class contains a model of the Country data.  There is a presenter (CountryContract.Presenter) 
and a view contract (CountryContract.View) that will post the Country data to a RecylerView using an adapter in 
the main activity (CountryActivity) via an observer from the CountryVieModel. The CountryViewModel 
is used to persist the data obtained from the retrofit call so that during configuration changes 
(such as an orientation change), the country and location where the user scrolled to is maintained. The retrofit CountryCallBack
is used to deal with both errors and successful responses. The callback is convenient in that onResponse() is fired on the 
main thread so as to not require an explicit contxt change from the background thread to the main thread. 

Unit tests are prvided for the CountrPresenter and retrofit CountryCallback. 

</pre>
