from Python.flask_runner import get_app

app = get_app()

if __name__ == '__main__':
    app.run(debug=True, host='localhost', port=9999)
