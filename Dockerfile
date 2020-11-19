FROM ubuntu:latest
#============================================
# INSTALL PYTHON 3
#============================================
# two version of python - 3.6
#============================================
RUN apt-get update \
  && apt-get install -y python3-pip python3-dev libssl1.0.0 libssl-dev libidn11 \
  && cd /usr/local/bin \
  && ln -s /usr/bin/python3 python \
  && pip3 install --upgrade pip

RUN apt-get install -y curl
#============================================
# COPY IoT
#============================================
#  ---
#============================================
COPY ./ /tests

#============================================
# INSTALL REQUIREMENTS
#============================================
#  ---
#============================================
WORKDIR /tests/
RUN pip3 install -Ur requirements.txt

#============================================
# SET ENV
#============================================
#  ---
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
