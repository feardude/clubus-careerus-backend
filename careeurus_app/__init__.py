from flask import Flask

from .config import Config
from .users.auth import auth_bp
from careeurus_app.users.models import db

db = db

def create_app():
    app = Flask(__name__)
    app.config.from_object(Config)

    db.init_app(app)
    app.register_blueprint(auth_bp, url_prefix='/api')
    return app
