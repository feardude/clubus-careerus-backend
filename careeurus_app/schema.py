from marshmallow import Schema, fields, validate

class UserSchema(Schema):
    email = fields.Email(required=True)
    login = fields.String(required=True, validate=validate.Length(min=8, max=50))
    password = fields.String(required=True)
    salt = fields.String(required=False)
    createdAt = fields.DateTime()
