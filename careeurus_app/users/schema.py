from marshmallow import Schema, fields, validate, ValidationError
import re

def validate_login(value):
    if not re.match(r"^[a-zA-Z0-9._-]+$", value):
        raise ValidationError("Login must contain only Latin letters, digits, and the characters - _ .")
    if ' ' in value:
        raise ValidationError("Login must not contain spaces.")

def validate_password(value):
    if len(value) < 8:
        raise ValidationError("Password must be at least 8 characters long.")
    if not re.search(r"[A-Z]", value):
        raise ValidationError("Password must contain at least one uppercase letter.")
    if not re.search(r"[a-z]", value):
        raise ValidationError("Password must contain at least one lowercase letter.")
    if not re.search(r"[0-9]", value):
        raise ValidationError("Password must contain at least one digit.")
    if not re.match(r"^[a-zA-Z0-9]+$", value):
        raise ValidationError("Password must contain only Latin letters and digits.")


class UserSchema(Schema):
    email = fields.Email(required=True, validate=validate.Email())
    login = fields.String(required=True, validate=[validate.Length(min=8, max=50), validate_login])
    password = fields.String(load_only=True, required=True, validate=validate_password)
