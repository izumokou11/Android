package com.example.currentweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.et_ipcity)
    EditText etIpCity;
    @BindView(R.id.bt_ok)
    Button btOK;
    @BindView(R.id.iv_weather)
    ImageView ivWeather;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_country)
    TextView tvCountry;
    @BindView(R.id.tv_wind)
    TextView tvWind;
    @BindView(R.id.tv_temperature)
    TextView tvTemperature;
    @BindView(R.id.tv_description)
    TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_ok)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_ok:
                APIService service = RetrofitConfiguration.getInstance().create(APIService.class);
                Call<GetWeatherResponse> call = service.getWeather(
                        etIpCity.getText().toString(),
                        APIService.temperatureType,
                        APIService.apiId
                );
                call.enqueue(new Callback<GetWeatherResponse>() {
                    @Override
                    public void onResponse(Call<GetWeatherResponse> call, Response<GetWeatherResponse> response) {
                        if (response.code() == 200) {
                            Log.d("wtf", "onResponse: " + response.body().getTimezone());
                            GetWeatherResponse quick = response.body();
                            String country = quick.getSys().getCountry();
                            tvCountry.setText("Country: " + country);
                            int date1 = quick.getDt();
                            Date date2 = new Date(date1 * 1000L);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd");
                            String day = simpleDateFormat.format(date2);
                            tvDate.setText(day);

                            GetWeatherResponse.WeatherBean quickWeather = response.body().getWeather().get(0);
                            String status = quickWeather.getMain();
                            Log.d("wtf", "onResponse: " + status);
                            tvStatus.setText(status);
                            String icon = quickWeather.getIcon();
                            Glide.with(MainActivity.this)
                                    .load("http://openweathermap.org/img/w/" + icon + ".png").into(ivWeather);
                            String description = quickWeather.getDescription();
                            tvDescription.setText("Description: "+description);
                            GetWeatherResponse.MainBean quickMain = response.body().getMain();
                            double temperature = quickMain.getTemp();
                            tvTemperature.setText("Temperature: " + temperature);
                            double wind = response.body().getWind().getSpeed();
                            tvWind.setText("SpeedWind: " + wind);
                        } else if (response.code() == 404) {
                            Toast.makeText(MainActivity.this, "City not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GetWeatherResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                });
                break;
        }
    }
}
