from flask import Blueprint, request, jsonify
from marshmallow import ValidationError
from typing import Dict, Any

from careeurus_app.users.models import User, db
from careeurus_app.users.schema import UserSchema

auth_bp = Blueprint("auth", __name__)


@auth_bp.route("/users", methods=["POST"])
def register() -> tuple[Any, int]:
    data: Dict[str, Any] = request.get_json()
    user_schema: UserSchema = UserSchema()

    try:
        user_data: Dict[str, Any] = user_schema.load(data)
    except ValidationError as err:
        return jsonify({"errors": err.messages}), 400

    user_instance: User = User(
        email=user_data["email"],
        login=user_data["login"],
    )
    user_instance.hash_password(user_data["password"])

    db.session.add(user_instance)
    db.session.commit()

    return user_schema.dump(user_instance), 201


@auth_bp.route("/login", methods=["POST"])
def login():
    data: Dict[str, Any] = request.get_json()

    if not data.get("email") or not data.get("password"):
        return jsonify({"error": "Email and password are required."}), 400

    user = User.query.filter_by(email=data["email"]).first()

    if not user:
        return jsonify({"error": "Invalid email or password."}), 401

    if not user.check_password(data["password"]):
        return jsonify({"error": "Invalid email or password."}), 401

    return jsonify({"message": "Login successful."}), 200
