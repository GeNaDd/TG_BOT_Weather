FROM python:3.9.18-slim-bullseye
RUN mkdir -p /app/telegram_GenDev_bots
WORKDIR /app/telegram_GenDev_bots
COPY telegram_GenDev_bots.py weather.py ./

EXPOSE 8181

CMD ["python", "telegram_GenDev_bots.py"]
