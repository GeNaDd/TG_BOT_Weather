FROM python:3.9.18-slim-bullseye
RUN mkdir -p /app/weather-bot
WORKDIR /app/weather-bot
COPY telegram_GenDev_bots.py weather.py ./

EXPOSE 8181

CMD ["python", "telegram_GenDev_bots.py"]
