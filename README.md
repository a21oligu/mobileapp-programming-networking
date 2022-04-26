# Rapport

När data skulle hämtas från den externa källan på urlen _https://mobprog.webug.se/json-api?login=brom_, uppstod flera problem. Dessa problem kunde relativt enkelt lösas med hjälp av debugging av loggen. Det första problemet var att responsen blev null vilket resulterade i att `onPostExecute(String json)` som innehöll funktionen `Log.d("tag", json)` throwade en execption då json hade värdet null. Denna ändrades så att den inte crashade appen vi null. Det andra problemet som uppstod var att SSL handskakningen inte lyckades. Detta eftersom emulatorns tid och datum var satt till 1 April 2022. Genom att manuellt ändra tiden till dagens datum (26/4/22) lyckades handskakningen och appen kunde hämta json-stringen fårn den externa urlen. 

När json-stringen hämtats från servern, omvandlades den till en lista av objekt varpå listan lades till i _mountains_, den lista som Adaptern har referns till. För att sedan uppdatera appen med den nya datan kallades funktionen `mountainAdapter.notifyDataSetChanged()`. Detta går att se i kodblocket nedan:

```
@Override
public void onPostExecute(String json) {
    Gson gson = new Gson();
    Type type = new TypeToken<ArrayList<MountainListItem>>() {}.getType();

    Log.d("Response", String.format("Got response from GET: %b", json != null));

    ArrayList<MountainListItem> newMountains =  gson.fromJson(json, type);

    mountains.addAll(newMountains);
    mountainAdapter.notifyDataSetChanged();
}
```

<img src="app_fetching.png" height="350px" />
<img src="app_fetched.png" height="350px" />
