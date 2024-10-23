from argon2 import PasswordHasher
from argon2.exceptions import VerifyMismatchError

from flask_sqlalchemy import SQLAlchemy
from sqlalchemy import func

db = SQLAlchemy()
ph = PasswordHasher()

class User(db.Model):
    __tablename__ = 'users'

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    email = db.Column(db.String, nullable=False, unique=True)
    login = db.Column(db.String, nullable=False, unique=True)
    password = db.Column(db.String, nullable=False)
    createdAt = db.Column(db.DateTime, server_default=func.now())

    def hash_password(self, password):
        self.password = ph.hash(password)

    def check_password(self, password):
        try:
            return ph.verify(self.password, password)
        except VerifyMismatchError:
            return False
