from flask import Flask
from Python.app import blueprints
from Python.logger_sett import logger_set_up


#from app.libs.models_handler import ModelsHandler


def get_app():
    logger_set_up()
    #ModelsHandler()
    app = Flask(__name__)
    for blueprint in blueprints:
        app.register_blueprint(blueprint)

    return app