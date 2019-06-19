### Запуск Python сервиса
1. Устанавливаем Python 3.6
    * https://www.python.org/downloads/release/python-368/
2. Установить библиотеки для виртуального окружения(*запускать в консоли*):
    * pip install virtualenv
    * pip install virtualenvwrapper-win
3. Создать виртуальное окружение:
    * mkvirtualenv flask_services
4. После установки, в консоли, в скобках, должно появится название - flask_services
    * Если не появилось, введите команду: workon flask_Services
5. Перейдите в папку проекта Python
5. Далее можно устанавливать библиотеки:
    * pip install -r requirements.txt
6. Для запуска сервиса требуется ввести:
    * python wsgi.py
    
После запуска, консоль должна отобразить:
~~~
 * Serving Flask app "Python.flask_runner" (lazy loading)
 * Environment: production
   WARNING: This is a development server. Do not use it in a production deployment.
   Use a production WSGI server instead.
 * Debug mode: on
 * Restarting with stat
 * Debugger is active!
 * Debugger PIN: 228-386-523
 * Running on http://localhost:9999/ (Press CTRL+C to quit)
~~~
    

### REST for TEST

Реализованы три ручки:

#####v1/get_status
* Метод - GET
* Возвращает: ___{"status": "OK"}___


#####v1/data
* Метод - POST
* Ожидает: ___{"id": 42, "mail": "foo@bar.com", "name": "jjj"}___
* Возвращает: ___{"all": "42jjjfoo@bar.com", "id": 42}___


#####v1/test_rest
* Метод - GET
* Описание возвращает JSON который получит с ручки описанной в config.py. На данную ручку будет отправлен POST запрос вида: __{"id": 42}__. В ответ ожидаю JSON, который и будет отправлен ответом на изначальный GET

