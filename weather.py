import os
import urllib.request
import json
import configTokenWeather
import url_weather

def getWeather(city, apiKey):
    url = (url_weather.url_weather_address)
    with urllib.request.urlopen(url) as result:
        weacherJson = json.loads(result.read())
        #print(weacherJson)
        return {
            "region_BY": weacherJson["name"],
            "description_weather": weacherJson["weather"][0]["description"],
            "temp": weacherJson["main"]["temp"],
            "feels_like": weacherJson["main"]["feels_like"],
            "wind_speed": weacherJson["wind"]["speed"],
        }

if __name__ == "__main__":
    cityRegionBY = "Minsk"
    apiKeyWeather = (configTokenWeather.BOT_TOKEN_WEATHER_API)
    weatherData = getWeather(cityRegionBY, apiKeyWeather)
    print(weatherData)

#response: {'region_BY': 'Минск', 'description_weather': 'облачно с прояснениями', 'temp': -5.14, 'feels_like': -9.76, 'wind_speed': 3.08}
