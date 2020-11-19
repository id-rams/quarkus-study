from flask import Flask, request, jsonify

default_data = {2: "член{2}"}
app = Flask(__name__)


@app.route('/received_email', methods=['POST'])
def generate_auth_device_key():
    req_data = request.get_json(silent=True)
    header_id = request.headers['cor_id']
    default_data[header_id] = req_data
    return "ok"


@app.route('/', methods=['GET'])
def app_index():
    str = "<h2><font color=\"green\">emails API.</font></h2> "
    return str


@app.route('/get_all_email')
def get_all_email():
    return  jsonify(default_data)


@app.route('/get_email/<uuid>')
def get_email_by_id(uuid):
    return  default_data.get(uuid)


@app.route('/delete_email')
def delete_emails():
    default_data.clear()
    return jsonify("all emails deleted. ")
