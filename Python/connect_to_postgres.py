import psycopg2

connection = psycopg2.connect(user="demo",
                              password="demo",
                              host="192.168.1.10",
                              port="5432",
                              database="demo")
print("---")
print(connection.get_dsn_parameters(), "\n")