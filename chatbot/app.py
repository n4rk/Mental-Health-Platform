from flask import Flask, request, jsonify

from chat import get_response

app = Flask(__name__)


@app.get("/")
def index():
    return jsonify({'msg': 'hello'})


@app.post("/talk")
def talk():
    user_input = request.json['user_input']
    return jsonify({'response': str(get_response(user_input))})


if __name__ == 'main':
    app.run(host='127.0.0.1', port=5000, debug=True)