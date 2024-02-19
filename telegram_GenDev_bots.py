import telebot
import weather
import configTokenTGDevGenBY
import configTokenWeather



avtoken_tg_gendevBY = (configTokenTGDevGenBY.BOT_TOKEN_TG_DEV_GEN_BY)
bot=telebot.TeleBot(avtoken_tg_gendevBY)
weatherApiKey = (configTokenWeather.BOT_TOKEN_WEATHER_API)


@bot.message_handler(commands=['start'])
def start(m):
     
     bot.reply_to(m,f'ID: {m.from_user.id}')
     bot.send_message(m.chat.id, {f'Приветствую Вас на моем телеграмм боте, {m.from_user.first_name} {m.from_user.last_name}'})    
     bot.send_message(m.chat.id, "Меня зовут Аля. И я буду Вашим ботом погоды твоего региона BY")
     bot.send_message(m.chat.id, "Для вывода погоды. Напишите Погода")


    
     
@bot.message_handler(content_types=["text"])
def handle_text(message):
    #print(message)
    if message.text.strip() == 'Погода':
        list_region_BY = ['Минск', 'Брест', 'Витебск', 'Гродно', 'Гомель', 'Могилев']
        bot.send_message(message.chat.id,"Список доступных регионов")
        for list_BY_Region in list_region_BY: # Проходимся по элементам list_BY_Region циклом for 
         bot.send_message(message.chat.id, list_BY_Region)
        answer = f'Напиши город, в котором ты хочешь узнать погоду'
        bot.send_message(message.chat.id, answer) 


    if message.text.strip() == 'Минск':
     answer_json = weather.getWeather('Minsk', weatherApiKey)
     answer = f'Погода в городе {answer_json["region_BY"]}, Сейчас за окном: {answer_json["description_weather"]}, Температура на улице: {answer_json["temp"]}, Температура как-будто: {answer_json["feels_like"]}, Скорость ветра: {answer_json["wind_speed"]}'
     bot.send_message(message.chat.id, answer)

     
    if message.text.strip() == 'Брест':
     answer_json = weather.getWeather('Brest', weatherApiKey)
     answer = f'Погода в городе {answer_json["region_BY"]}, Сейчас за окном: {answer_json["description_weather"]}, Температура на улице: {answer_json["temp"]}, Температура как-будто: {answer_json["feels_like"]}, Скорость ветра: {answer_json["wind_speed"]}'  
     bot.send_message(message.chat.id, answer)

    if message.text.strip() == 'Витебск':
     answer_json = weather.getWeather('Vitebsk', weatherApiKey)
     answer = f'Погода в городе {answer_json["region_BY"]}, Сейчас за окном: {answer_json["description_weather"]}, Температура на улице: {answer_json["temp"]}, Температура как-будто: {answer_json["feels_like"]}, Скорость ветра: {answer_json["wind_speed"]}'  
     bot.send_message(message.chat.id, answer)

    if message.text.strip() == 'Гродно':
     answer_json = weather.getWeather('Grodno', weatherApiKey)
     answer = f'Погода в городе {answer_json["region_BY"]}, Сейчас за окном: {answer_json["description_weather"]}, Температура на улице: {answer_json["temp"]}, Температура как-будто: {answer_json["feels_like"]}, Скорость ветра: {answer_json["wind_speed"]}'   
     bot.send_message(message.chat.id, answer)

    if message.text.strip() == 'Гомель':
     answer_json = weather.getWeather('Gomel', weatherApiKey)
     answer = f'Погода в городе {answer_json["region_BY"]}, Сейчас за окном: {answer_json["description_weather"]}, Температура на улице: {answer_json["temp"]}, Температура как-будто: {answer_json["feels_like"]}, Скорость ветра: {answer_json["wind_speed"]}'   
     bot.send_message(message.chat.id, answer)

    if message.text.strip() == 'Могилев':
     answer_json = weather.getWeather('Mogilev', weatherApiKey)
     answer = f'Погода в городе {answer_json["region_BY"]}, Сейчас за окном: {answer_json["description_weather"]}, Температура на улице: {answer_json["temp"]}, Температура как-будто: {answer_json["feels_like"]}, Скорость ветра: {answer_json["wind_speed"]}'  
     bot.send_message(message.chat.id, answer)

bot.infinity_polling()
