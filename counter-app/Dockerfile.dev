FROM python:3.6-alpine
RUN adduser -D myproj
WORKDIR /home/myproj
RUN pip install Flask
RUN pip install redis
COPY api.py ./
RUN chown -R myproj:myproj ./
USER myproj
EXPOSE 5000
CMD ["python","api.py"]
