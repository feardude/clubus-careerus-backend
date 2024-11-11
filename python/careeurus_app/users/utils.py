import jwt
import os

from datetime import datetime, timedelta


def create_jwt_token(user_id: int):
    expiration = datetime.now() + timedelta(hours=1)
    payload = {
        'sub': user_id,
        'exp': expiration
    }
    token = jwt.encode(payload, os.getenv("SECRET_KEY"), algorithm='HS256')
    return token
