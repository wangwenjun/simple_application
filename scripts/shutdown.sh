#!/bin/bash
PID=`ps -ef | grep simple_application|grep -v grep | awk {'print $2'}`
if [[ -z "$PID" ]] then
  echo "shutdown the simple application http server."
  kill $PID
fi