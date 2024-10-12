from flask import Blueprint, request
from ..models import User, Base
from ..schema import UserSchema

auth_bp = Blueprint('auth', __name__)


@auth_bp.route('/users', methods=['POST'])
def register():
    data = request.get_json()
    user_schema = UserSchema()
    user = user_schema.load(data)
    user_instance = User(**data)
    #Base.session.add(user_instance)
    #Base.session.commit()

    return user_schema.dump(user), 201

@auth_bp.route('/login', methods=['POST'])
def login():
    # Логика авторизации
    return "Login endpoint"