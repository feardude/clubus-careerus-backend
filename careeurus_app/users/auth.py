from flask import Blueprint, request
from ..models import User, db
from ..schema import UserSchema

auth_bp = Blueprint('auth', __name__)

@auth_bp.route('/users', methods=['POST'])
def register():
    data = request.get_json()
    user_schema = UserSchema()

    user_data = user_schema.load(data)
    user_instance = User(
        email=user_data['email'],
        login=user_data['login'],
        password=user_data['password'],
        salt=user_data['salt']
    )

    db.session.add(user_instance)
    db.session.commit()

    return user_schema.dump(user_instance), 201

@auth_bp.route('/login', methods=['POST'])
def login():
    return "Login endpoint"
