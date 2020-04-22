import redis
import os
from flask import Flask
app = Flask(__name__)
redis = redis.Redis(host=os.environ.get('REDIS_HOST'), port=6379, db=0)

@app.route("/")
def hello():
    return "Hello World!"

@app.route("/counter")
def get_counter():
    counter = redis.get('counter')
    if counter is None:
        redis.set('counter', str(1))
        return str(1)
    else:
        newval = int(counter) + 1
        redis.set('counter', str(newval))
        return str(newval)

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=5000)
