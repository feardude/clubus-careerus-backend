from flask import Flask
from flask_sqlalchemy import SQLAlchemy

from .config import Config
from .users.auth import auth_bp
from .models import db

db = db


def create_app():
    app = Flask(__name__)
    app.config.from_object(Config)

    db.init_app(app)
    app.register_blueprint(auth_bp, url_prefix='/api')
    return app