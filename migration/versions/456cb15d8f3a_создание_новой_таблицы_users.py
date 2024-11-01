"""Создание новой таблицы users

Revision ID: 456cb15d8f3a
Revises: 4733bebbc1b2
Create Date: 2024-10-17 23:50:52.600584

"""

from typing import Sequence, Union

from alembic import op
import sqlalchemy as sa


# revision identifiers, used by Alembic.
revision: str = "456cb15d8f3a"
down_revision: Union[str, None] = "4733bebbc1b2"
branch_labels: Union[str, Sequence[str], None] = None
depends_on: Union[str, Sequence[str], None] = None


def upgrade():
    op.create_table(
        "users",
        sa.Column("id", sa.Integer(), primary_key=True, autoincrement=True),
        sa.Column("email", sa.String(), nullable=False, unique=True),
        sa.Column("login", sa.String(), nullable=False, unique=True),
        sa.Column("password", sa.String(), nullable=False),
        sa.Column("createdAt", sa.DateTime(), server_default=sa.func.now()),
    )


def downgrade():
    op.drop_table("users")