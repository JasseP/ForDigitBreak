from datetime import datetime
import logging


def get_file_name():
    file_name = datetime.now().strftime("%d.%m.%Y%Hh%Mm")
    return 'logs/{}.log'.format(file_name)


def get_format_file():
    f = "%(asctime)s - %(name)s - %(levelname)s - %(message)s"
    df = '[%Y-%m-%d - %H:%M:%S]'
    return logging.Formatter(f, datefmt=df)


def get_format_console():
    f = '[%(levelname)s] %(message)s'
    return logging.Formatter(f)


def logger_set_up():
    logger = logging.getLogger('first_logger')
    log_file = logging.FileHandler(get_file_name())
    log_file.setFormatter(get_format_file())

    log_console = logging.StreamHandler()
    log_console.setFormatter(get_format_console)

    logger.setLevel(logging.DEBUG)
    log_file.setLevel(logging.DEBUG)
    log_console.setLevel(logging.DEBUG)

    logger.addHandler(log_file)
    logger.addHandler(log_console)