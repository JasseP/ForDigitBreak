from marshmallow import ValidationError
from traceback import format_exc
from flask import Blueprint, request, jsonify
import logging


blueprints_v1 = Blueprint(__name__, 'blueprints_v1', url_prefix='/v1')
logger = logging.getLogger('first_logger')


@blueprints_v1.route('get_status', methods=['GET'])
def get_status():
    return jsonify({"status": "OK"})


@blueprints_v1.errorhandler(ValidationError)
def validation_json(error: ValidationError):
    return jsonify({'error': error.args[0]}), 400


@blueprints_v1.errorhandler(Exception)
def all_exception(error: Exception):
    logger.error(format_exc())
    return jsonify({'error': error.args}), 520
