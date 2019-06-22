import requests
from config import DB_URL
import logging

logger = logging.getLogger("first_logger")


def req_test():
    r = requests.post(DB_URL, json={"id": 42})
    logger.info(r.status_code)
    return r.json()
