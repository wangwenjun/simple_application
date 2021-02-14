#!/bin/bash
base_dir=$(cd `dirname $0`;pwd)
parent_dir=$(cd `dirname $base_dir`;pwd)
CLASSPATH=.:$parent_dir/lib/*
MAIN_CLASS=com.wangwenjun.simple.application.SimpleApplication
nohup java -cp $CLASSPATH:$parent_dir/conf/* $MAIN_CLASS &