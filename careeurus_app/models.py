from flask_sqlalchemy import SQLAlchemy
from sqlalchemy import func

db = SQLAlchemy()

class User(db.Model):
    __tablename__ = 'users'

    #id = db.Column(db.Integer)
    email = db.Column(db.String, primary_key=True, nullable=False, unique=True)
    login = db.Column(db.String, nullable=False, unique=True)
    password = db.Column(db.String, nullable=False)
    salt = db.Column(db.String, nullable=False)
    createdAt = db.Column(db.DateTime, server_default=func.now())

