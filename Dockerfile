FROM python:3.9.0-slim-buster
#============================================
# COPY
#============================================
COPY ./ /tests

#============================================
# INSTALL REQUIREMENTS
#============================================
WORKDIR /tests/
RUN lc
RUN pip3 install -Ur requirements.txt

#============================================
# SET ENV
#============================================
ENV LD_LIBRARY_PATH=$LD_LIBRARY_PATH:./lib
ENV LC_ALL=C.UTF-8
ENV LANG=C.UTF-8

#============================================
# RUN FLASK_APP
#============================================
#  ---
#============================================
ENV FLASK_RUN_PORT=5000
ENV FLASK_APP=/tests/run_server
CMD ["flask", "run", "--host", "0.0.0.0"]
