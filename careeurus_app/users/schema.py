from marshmallow import Schema, fields, validate

class UserSchema(Schema):
    email = fields.Email(required=True, validate=validate.Email())
    login = fields.String(required=True, validate=validate.Length(min=8, max=50))
    password = fields.String(load_only=True, required=True)
