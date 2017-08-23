# Local Json Data Read Example - Yerel Json Verilerini Okuyan Örnek


Local Json Data Read and Write Example
Herhangi bir servis çağrısı yapmadan data sahibi olmak adına mevcut uygulama içinde bulunan bir data kaynağından veri çekmek için res klasörü altında raw adı ile yeni bir klasör oluşturulması gerekmektedir. 
raw klasörü içine kullanacağımız data kaynağını “***.json” oluşturduktan sonra kod yazmaya başlayabiliriz. 

 

Oluşturduğumuz json dosyanın kriterleri uyumunu  http://jsonviewer.stack.hu/ sitesinden kontrol edebilirsiniz. Kullandığım json datayı https://github.com/serkansekman/local_json_data_read_example/tree/master/app/src/main/res/raw buradan ulaşabilirsiniz. 

Datanın okunup uygulamanın içinde kullanılması için modelleme POJO’larının yazılması gerekmektedir. Modelleri hızlıca oluşturmak için http://www.jsonschema2pojo.org/ sitesini kullanabilirsiniz.



Preview butonu ile oluşan class’ların hepsinin birarada bulunması için proje içinde model adında bir klasör oluşturabilirsiniz (zorunlu değil, kullanım amacına göre kümeleme işlemi içindir).


Oluşan POJO’yu kopyalayıp model klasörünün içine atabilrisiniz.
 

Json dosyadan okunan verilerin POJO’ya eşitlenmesi için kullanılan SerializedName ve Expose özelliklerinin aktif edilmesi için app build.gradle dosyasına aşağıdaki library’i compile etmesi için ekliyoruz.

```java
compile 'com.squareup.retrofit2:converter-gson:2.0.2'
 ```
 


Şimdi Lokal json datayı okuma zamanı!
Oluşturulan POJO’ya uygun local JSON dosyadan veri almak için aşağıdaki kod bloğunu kullanıyoruz.

 ```java
 
 CityList cityList = new CityList();

try {
    //Load File
    BufferedReader jsonReader = new BufferedReader(new InputStreamReader(this.getResources().openRawResource(R.raw.jsondata)));
    StringBuilder jsonBuilder = new StringBuilder();
    for (String line = null; (line = jsonReader.readLine()) != null; ) {
        jsonBuilder.append(line).append("\n");
    }

    Gson gson = new Gson(); //json’u parse etmek için Gson kütüphanesini kullanıyoruz
    cityList = gson.fromJson(jsonBuilder.toString(),CityList.class);

} catch (FileNotFoundException e) {
    Log.e("jsonFile", "file not found");
} catch (IOException e) {
    Log.e("jsonFile", "ioerror");
}
 ```
Local dosyadan okuduğumuz data artık elimizde.!
Örnek olarak Spinner’a İl adı aktarma işlemini aldığımız bu data ile yaptık. Bu aşamadan sonrası uygulamanızda kullanacağınız Local JSON data verisinin çeşitliliğine ve model yapısına kalmıştır. Veri çekmek bu kadar kolay :)

```java
Spinner spinner = (Spinner) findViewById(R.id.spinner);

List<String> spinnerData = new ArrayList<>();

for(int i=0;i<cityList.getCityDetail().size();i++){
    spinnerData.add(cityList.getCityDetail().get(i).getName());
}

ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_spinner_item, spinnerData);
dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
spinner.setAdapter(dataAdapter);
 ```

### Örnek Ekran Görüntüsü

<img src="https://media.giphy.com/media/xTcf1jm6Pw6He07m3C/giphy.gif">
