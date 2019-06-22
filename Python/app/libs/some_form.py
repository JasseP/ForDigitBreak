from marshmallow import Schema, fields, post_load, ValidationError
import logging

logger = logging.getLogger("first_logger")


class SomeClass:
    def __init__(self, id: int, name: str, mail: str):
        self.id = id
        self.name = name
        self.mail = mail
        self.all = self.get_all()

    def get_all(self):
        return "{}{}{}".format(self.id, self.name, self.mail)


class SomeSchema(Schema):
    id = fields.Int(required=True)
    name = fields.Str(required=True)
    mail = fields.Str(required=True)
    all = fields.Str()

    @post_load
    def make(self, data):
        return SomeClass(**data)


def get_some_json(json):
    try:
        message_parser = SomeSchema().load(json)
        return SomeSchema(only=('id', 'all')).dump(message_parser)
    except ValidationError:
        raise


if __name__ == "__main__":
    print(get_some_json({"id": 42, "mail": "foo@bar.com", "name": "jjj"}))