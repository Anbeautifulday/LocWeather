## 先完成
1. 地图定位
2. 天气查询

### 实现细节
1. 搜索框
2. AlertDialog实现搜索展示列表
3. 使用高德地图API

#### 接口地址http://www.sojson.com/open/api/weather/json.shtml?city=北京


``` java
public interface WeatherService {
        @GET("open/api/weather/json.shtml")
        Call<Weather> getWeather(@Query("city") String city);
}
```


``` java
//Alertdialog写在了网络请求中
public void sendRetrofit(String place) {
	Retrofit retrofit = new Retrofit.Builder()
		.baseUrl("http://www.sojson.com/")
		.addConverterFactory(GsonConverterFactory.create())
		.build();
	WeatherService request_api = retrofit.create(WeatherService.class);

	Call<Weather> call = request_api.getWeather(place);

	call.enqueue(new Callback<Weather>() {
	    @Override
	    public void onResponse(Call<Weather> call, Response<Weather> response) {
		    try {
			final AlertDialog builder = new AlertDialog.Builder(MapActivity.this).create();
			View view = View.inflate(MapActivity.this, R.layout.item_show, null);
			builder.setView(view);

			ImageView back = (ImageView) view.findViewById(R.id.show_back);
			ImageView head = (ImageView) view.findViewById(R.id.show_head);
			TextView name = (TextView) view.findViewById(R.id.show_name);
			TextView temp = (TextView) view.findViewById(R.id.show_temperature);
			TextView humi = (TextView) view.findViewById(R.id.show_humidity);
			TextView qual = (TextView) view.findViewById(R.id.show_quilty);
			TextView pm25 = (TextView) view.findViewById(R.id.show_pm25);
			TextView pm10 = (TextView) view.findViewById(R.id.show_pm10);
			TextView tips = (TextView) view.findViewById(R.id.show_tips);

			back.setOnClickListener(new View.OnClickListener() {
			    @Override
			    public void onClick(View v) {
			        builder.cancel();
			    }
			});

			if(response.body().getData().getForecast().get(0).getType().equals("多云")) {
			    Glide.with(MapActivity.this).load("http://119.23.252.121/weather/cloudy.png").into(head);
			} else if (response.body().getData().getForecast().get(0).getType().equals("阴")) {
			    Glide.with(MapActivity.this).load("http://119.23.252.121/weather/overcast.png").into(head);
			} else if (response.body().getData().getForecast().get(0).getType().equals("阵雨")) {
			    Glide.with(MapActivity.this).load("http://119.23.252.121/weather/shower.png").into(head);
			} else if (response.body().getData().getForecast().get(0).getType().equals("雷阵雨")) {
			    Glide.with(MapActivity.this).load("http://119.23.252.121/weather/thunder.png").into(head);
			} else if (response.body().getData().getForecast().get(0).getType().equals("晴")) {
			    Glide.with(MapActivity.this).load("http://119.23.252.121/weather/sun.png").into(head);
			} else if (response.body().getData().getForecast().get(0).getType().equals("暴雨")) {
			    Glide.with(MapActivity.this).load("http://119.23.252.121/weather/rainstorm.png").into(head);
			} else if (response.body().getData().getForecast().get(0).getType().equals("中雨")) {
			    Glide.with(MapActivity.this).load("http://119.23.252.121/weather/midrain.png").into(head);
			} else {
			    Glide.with(MapActivity.this).load("http://119.23.252.121/weather/wait.png").into(head);
			}
			name.setText(response.body().getCity());
			temp.setText(response.body().getData().getWendu());
			humi.setText(response.body().getData().getShidu());
			qual.setText(response.body().getData().getWendu());
			pm25.setText(response.body().getData().getPm25());
			pm10.setText(response.body().getData().getPm10());
			tips.setText(response.body().getData().getGanmao());

			RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.show_forecast);
			recyclerView.setLayoutManager(new LinearLayoutManager(MapActivity.this));
			recyclerView.setAdapter(new ForecastAdapter(response.body().getData().getForecast()));

			builder.setCancelable(true);
			builder.show();
		    } catch (Exception e) {
			Toast.makeText(MapActivity.this, "Error!!!", Toast.LENGTH_SHORT).show();
		    }
	    }

	    @Override
	    public void onFailure(retrofit2.Call<Weather> call, Throwable t) {
		Toast.makeText(MapActivity.this, "Error!!!", Toast.LENGTH_SHORT).show();
	    }
	});
}
```
![image](https://github.com/Anbeautifulday/LocWeather/blob/master/weathersort/show1.png)
![image](https://github.com/Anbeautifulday/LocWeather/blob/master/weathersort/show2.png)
![image](https://github.com/Anbeautifulday/LocWeather/blob/master/weathersort/show3.png)

