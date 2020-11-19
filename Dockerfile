FROM python:3.9.0-slim-buster
#============================================
# COPY REQUIREMENTS
#============================================

COPY . /app
WORKDIR /app/
RUN pip install -U Flask
RUN pip install -U uuid
#============================================
# SET ENV
#============================================
ENV LC_ALL=C.UTF-8
ENV LANG=C.UTF-8

#============================================
# RUN FLASK_APP
#============================================
ENV FLASK_RUN_PORT=5000
ENV FLASK_APP=/app/run_server
CMD ["flask", "run", "--host", "0.0.0.0"]
