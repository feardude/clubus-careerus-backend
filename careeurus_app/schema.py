from marshmallow import Schema, fields, validate

class UserSchema(Schema):
    email = fields.Email(required=True)
    login = fields.String(required=True, validate=validate.Length(min=1, max=50))
    password = fields.String(required=True)

